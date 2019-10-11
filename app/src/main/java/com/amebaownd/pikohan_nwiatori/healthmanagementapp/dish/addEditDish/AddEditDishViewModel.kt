package com.amebaownd.pikohan_nwiatori.healthmanagementapp.dish.addEditDish

import android.opengl.Visibility
import android.view.View
import androidx.lifecycle.*
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.R
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.*
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.repository.DishRepository
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.util.Event
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.util.MyContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddEditDishViewModel (private val dishRepository: DishRepository): ViewModel(){

    private val _dish = MutableLiveData<Dish>()
    val dish : LiveData<Dish> = _dish

     val name = MutableLiveData<String>()

    val dishGroup =MutableLiveData<String>("Choose Dish Category")

     val totalCalorie = MutableLiveData<Float>(0f)

    val totalProtein = MutableLiveData<Float>(0f)

   val totalCarbohydrate = MutableLiveData<Float>(0f)

    val totalFat = MutableLiveData<Float>(0f)

//    val gram = MutableLiveData<Float>(100f)
//    val num = MutableLiveData<Float>(MyContext.getStringArray(R.array.spinner_values)[0].toFloat())

    private val _dialogOpenEvent = MutableLiveData<Event<Boolean>>(Event(false))
    val dialogOpenEvent :LiveData<Event<Boolean>> = _dialogOpenEvent

    private val _isEditMode = MutableLiveData<Boolean>(false)
    val isEditMode :LiveData<Boolean> = _isEditMode

    private val _addFoodStuffEvent = MutableLiveData<Event<Boolean>>(Event(false))
    val addFoodStuffEvent :LiveData<Event<Boolean>> = _addFoodStuffEvent

    private val _addedFoodStuffs = MutableLiveData<List<AddedFoodStuff>>(listOf(AddedFoodStuff(id = "a",name = "aaaaa", weight = 100, food_group = 1, kcal_per = 100f, carbohydrate_per = 100f, protein_per = 100f, fat_per = 100f, is_gram = true, gram_amount = null, one_amount = null)))
    val addedFoodStuff : LiveData<List<AddedFoodStuff>> = _addedFoodStuffs

    val hintTextVisibility:LiveData<Boolean> = Transformations.map(_addedFoodStuffs){ it.isNullOrEmpty() }

    private val _addedFoodStuffEvent = MutableLiveData<Event<Boolean>>(Event(false))
    val addedFoodStuffEvent :LiveData<Event<Boolean>> = _addedFoodStuffEvent


    fun start(id:String){
        _isEditMode.value = true
        viewModelScope.launch(Dispatchers.IO){
           val searchedDish = dishRepository.loadItem(id)
            searchedDish.let{
                _dish.value = it
                name.value=it.name
                dishGroup.value =it.calorieForList
                totalCalorie.value=it.totalCalorie
                totalProtein.value = it.totalProtein
                totalCarbohydrate.value = it.totalCarbohydrate
                totalFat.value = it.totalFat
            }
        }
    }

    fun onChangeDishGroup(){
        _dialogOpenEvent.value = Event(true)
    }

    fun onFoodGroupSelected(str:String){
        dishGroup.value = str
    }

    fun onDialogCanceledOrDismissed(){
        _dialogOpenEvent.value = Event(false)
    }

    fun saveDish(){
        _isEditMode.value?.let{

        }
    }

    fun addFoodStuff(){
        _addFoodStuffEvent.value = Event(true)
    }

    fun selectedFoodStuff(foodStuff: FoodStuff){
        val before = _addedFoodStuffs.value
        val added = before?.plus(listOf(AddedFoodStuff(id = foodStuff.id,name = foodStuff.name, weight = foodStuff.weight, food_group = foodStuff.food_group, kcal_per = foodStuff.kcal_per, carbohydrate_per = foodStuff.carbohydrate_per, protein_per = foodStuff.protein_per, fat_per = foodStuff.fat_per, is_gram = true, gram_amount = null, one_amount = null))) ?: listOf(AddedFoodStuff(id = foodStuff.id,name = foodStuff.name, weight = foodStuff.weight, food_group = foodStuff.food_group, kcal_per = foodStuff.kcal_per, carbohydrate_per = foodStuff.carbohydrate_per, protein_per = foodStuff.protein_per, fat_per = foodStuff.fat_per, is_gram = true, gram_amount = null, one_amount = null))
        _addedFoodStuffs.value = added
        _addedFoodStuffEvent.value = Event(true)
    }

}