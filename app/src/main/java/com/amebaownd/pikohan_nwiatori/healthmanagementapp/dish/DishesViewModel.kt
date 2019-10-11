package com.amebaownd.pikohan_nwiatori.healthmanagementapp.dish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.Dish
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.repository.DishRepository
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DishesViewModel (private val dishRepository: DishRepository):ViewModel(){

    private val _allDishes = MutableLiveData<List<Dish>>()
    val allDishes :LiveData<List<Dish>> =_allDishes

    private val _isDetailOpenEvent  = MutableLiveData<Event<String>>(Event(""))
    val isDetailOpenEvent:LiveData<Event<String>> = _isDetailOpenEvent

    private val _isAddDishEvent = MutableLiveData<Event<Boolean>>(Event(false))
    val isAddDishEvent :LiveData<Event<Boolean>> =_isAddDishEvent


    fun openDetail(id:String){
        _isDetailOpenEvent.value = Event(id)
    }

    fun filterItem(key:String){
        viewModelScope.launch(Dispatchers.IO) {
            dishRepository.filterItem(key)
        }
    }



    fun addDish(){
        _isAddDishEvent.value = Event(true)
    }
}