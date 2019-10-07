package com.amebaownd.pikohan_nwiatori.healthmanagementapp.foodstaff.addEditFoodStuff

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.HealthDatabase
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.FoodStuff
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.repository.FoodStuffRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class AddEditFoodStuffViewModel(private val repository: FoodStuffRepository) : ViewModel() {

    val name = MutableLiveData<String>()
    val kcalPer = MutableLiveData<String>()
    val proteinPer = MutableLiveData<String>()
    val carbohydratePer = MutableLiveData<String>()
    val fatPer = MutableLiveData<String>()

    fun start(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.loadFoodStuff(id).let { foodStuff ->
                name.postValue(foodStuff.name)
                kcalPer.postValue(foodStuff.kcalPerForList)
                proteinPer.postValue(foodStuff.proteinPerForList)
                carbohydratePer.postValue(foodStuff.carbohydratePerForList)
                fatPer.postValue(foodStuff.fatPerForList)
            }

        }
    }

    private fun insert(foodStuff: FoodStuff) = viewModelScope.launch {
        repository.insert(foodStuff)
    }

    fun saveFoodStuff() {
        var isAbleToSave = true
        var errorMsg = "エラー："
        if(name.value.isNullOrEmpty() || name.value.isNullOrBlank()){
            isAbleToSave=false
            errorMsg+="\n\tInput Name."
        }
        if(kcalPer.value.isNullOrEmpty() || kcalPer.value.isNullOrBlank()){
            isAbleToSave=false
            errorMsg+="\n\tInput kcal/per."
        }
        if(proteinPer.value.isNullOrEmpty() || proteinPer.value.isNullOrBlank()){
            isAbleToSave=false
            errorMsg+="\n\tInput protain/per."
        }
        if(carbohydratePer.value.isNullOrEmpty() || carbohydratePer.value.isNullOrBlank()){
            isAbleToSave=false
            errorMsg+="\n\tInput carbohydrate/per."
        }
        if(fatPer.value.isNullOrEmpty() || fatPer.value.isNullOrBlank()){
            isAbleToSave=false
            errorMsg+="\n\tInput fatPer."
        }
        if(isAbleToSave) {
            val currentFoodStuff = FoodStuff(
                name = this.name.value!!,
                kcal_per = this.kcalPer.value!!.toFloat(),
                protein_per = this.proteinPer.value!!.toFloat(),
                carbohydrate_per = this.carbohydratePer.value!!.toFloat(),
                fat_per = this.fatPer.value!!.toFloat(),
                weight = 199,
                food_group = 1
            )
            insert(currentFoodStuff)
        }
    }
}