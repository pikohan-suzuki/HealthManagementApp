package com.amebaownd.pikohan_nwiatori.healthmanagementapp.foodstaff.addEditFoodStuff

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.*
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.Event
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.HealthDatabase
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.FoodStuff
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.repository.FoodStuffRepository
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.util.HealthManagementApp
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.util.MyContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.R
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class AddEditFoodStuffViewModel( private val repository: FoodStuffRepository) : ViewModel() {

    private val _foodStuff = MutableLiveData<FoodStuff>()
    var foodStuff = _foodStuff

    val name = MutableLiveData<String>()
    val category= MutableLiveData<String>("Category")
    val weight = MutableLiveData<String>()
    val kcalPer = MutableLiveData<String>()
    val proteinPer = MutableLiveData<String>()
    val carbohydratePer = MutableLiveData<String>()
    val fatPer = MutableLiveData<String>()

    val nutrients = MutableLiveData<String>("Nutrients/100g")

    private var _isEditMode: MutableLiveData<Boolean> = MutableLiveData(true)
    val isEditMode: LiveData<Boolean> = _isEditMode

    private var _dialogOpenEvent = MutableLiveData<Event<Boolean>>(Event(false))
    val dialogOpenEvent : LiveData<Event<Boolean>> = _dialogOpenEvent

    private var isGramSaveMode:MutableLiveData<Boolean> = MutableLiveData(true)

    fun start(id: String?) {
        if (id != null) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.loadFoodStuff(id).let { foodStuff ->
                    _foodStuff.postValue(foodStuff)
                    name.postValue(foodStuff.name)
                    weight.postValue(String.format("%d",foodStuff.weight))
                    category.postValue(foodStuff.foodGroupForList)
                    kcalPer.postValue(String.format("%.1f",foodStuff.kcal_per))
                    proteinPer.postValue(String.format("%.1f",foodStuff.protein_per))
                    carbohydratePer.postValue(String.format("%.1f",foodStuff.carbohydrate_per))
                    fatPer.postValue(String.format("%.1f",foodStuff.fat_per))
                }
            }
        } else {
            _isEditMode.value =false
        }
    }

    fun onCheckStateChanged(isChecked:Boolean){
        if(isChecked){
            nutrients.value="Nutrients/One"
            isGramSaveMode.value=false
        }else{
            nutrients.value="Nutrients/100g"
            isGramSaveMode.value=false
        }
    }

    fun showDialog(){
        _dialogOpenEvent.value=Event(true)
    }

    fun onFoodGroupSelected(foodGroup: String){
        category.value=foodGroup
    }
    fun onDialogCanceledOrDismissed(){
        _dialogOpenEvent.value=Event(false)
    }

    private fun insert(foodStuff: FoodStuff) = viewModelScope.launch {
        repository.insert(foodStuff)
    }

    private fun update(foodStuff: FoodStuff) = viewModelScope.launch {
        repository.update(foodStuff)

    }

    fun saveFoodStuff() {
        var isAbleToSave = true
        var errorMsg = "エラー："
        if (name.value.isNullOrEmpty() || name.value.isNullOrBlank()) {
            isAbleToSave = false
            errorMsg += "\n\tInput Name."
        }
        if(category.value=="Category"){
            isAbleToSave=false
            errorMsg+="\n\tChoose Category"
        }
        if(weight.value.isNullOrEmpty() || weight.value.isNullOrBlank()){
            isAbleToSave=false
            errorMsg+="\n\tInput Weight of One"
        }
        if (kcalPer.value.isNullOrEmpty() || kcalPer.value.isNullOrBlank()) {
            isAbleToSave = false
            errorMsg += "\n\tInput kcal/per."
        }
        if (proteinPer.value.isNullOrEmpty() || proteinPer.value.isNullOrBlank()) {
            isAbleToSave = false
            errorMsg += "\n\tInput protain/per."
        }
        if (carbohydratePer.value.isNullOrEmpty() || carbohydratePer.value.isNullOrBlank()) {
            isAbleToSave = false
            errorMsg += "\n\tInput carbohydrate/per."
        }
        if (fatPer.value.isNullOrEmpty() || fatPer.value.isNullOrBlank()) {
            isAbleToSave = false
            errorMsg += "\n\tInput fatPer."
        }
        if (isAbleToSave) {
            isEditMode.value?.let {
                if(it) {
                    foodStuff.value?.copy(
                        name = this.name.value!!,
                        kcal_per = this.kcalPer.value!!.toFloat(),
                        protein_per = this.proteinPer.value!!.toFloat(),
                        carbohydrate_per = this.carbohydratePer.value!!.toFloat(),
                        fat_per = this.fatPer.value!!.toFloat(),
                        weight = this.weight.value!!.toInt(),
                        food_group = when(this.category.value){
                            MyContext.getString(R.string.food_group1)->1
                            MyContext.getString(R.string.food_group2)->2
                            MyContext.getString(R.string.food_group3)->3
                            MyContext.getString(R.string.food_group4)->4
                            MyContext.getString(R.string.food_group5)->5
                            MyContext.getString(R.string.food_group6)->6
                            else ->7
                        }
                    )?.let {currentFoodStuff ->
                        update(currentFoodStuff)
                    }
                }else{
                    val currentFoodStuff = FoodStuff(
                        name = this.name.value!!,
                        kcal_per = this.kcalPer.value!!.toFloat(),
                        protein_per = this.proteinPer.value!!.toFloat(),
                        carbohydrate_per = this.carbohydratePer.value!!.toFloat(),
                        fat_per = this.fatPer.value!!.toFloat(),
                        weight = this.weight.value!!.toInt(),
                        food_group = when(this.category.value){
                            MyContext.getString(R.string.food_group1)->1
                            MyContext.getString(R.string.food_group2)->2
                            MyContext.getString(R.string.food_group3)->3
                            MyContext.getString(R.string.food_group4)->4
                            MyContext.getString(R.string.food_group5)->5
                            MyContext.getString(R.string.food_group6)->6
                            else ->7
                        }
                    )
                    insert(currentFoodStuff)
                }
            }
        }else{
            Log.d("FoodStuff Add Error",errorMsg)
        }
    }
}