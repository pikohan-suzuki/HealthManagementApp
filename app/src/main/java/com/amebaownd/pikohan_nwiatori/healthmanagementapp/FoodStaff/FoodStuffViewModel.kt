package com.amebaownd.pikohan_nwiatori.healthmanagementapp.foodstaff

import androidx.lifecycle.*
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.util.Event
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.FoodStuff
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.repository.FoodStuffRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodStuffViewModel (private val repository: FoodStuffRepository):ViewModel(){
    private var _allFoodStuffs = MutableLiveData<List<FoodStuff>>()
    val allFoodStuffs : LiveData<List<FoodStuff>> = _allFoodStuffs

    private val _openDetailEvent = MutableLiveData<Event<String>>()
    val openDetailEvent:LiveData<Event<String>> = _openDetailEvent

    private val _isSelectMode = MutableLiveData<Boolean>(false)
    val isSelectMode :LiveData<Boolean> = _isSelectMode

    private val _selectFoodStuffEvent = MutableLiveData<Event<FoodStuff>>(null)
    val selectFoodStuffEvent :LiveData<Event<FoodStuff>> = _selectFoodStuffEvent

    init{
        _openDetailEvent.value= Event("")
    }

    fun start(isSelectMode:Boolean){
        if(isSelectMode)
            this._isSelectMode.value=true

        viewModelScope.launch(Dispatchers.IO){
            _allFoodStuffs.postValue(repository.loadAllFoodStuffs())
        }
    }

    fun filterItems(key:String){
        viewModelScope.launch(Dispatchers.IO){
            _allFoodStuffs.postValue(repository.loadFiltered(key))
        }
    }

    fun onClick(foodStuff: FoodStuff){
        if(isSelectMode.value!!)
            _selectFoodStuffEvent.value=Event(foodStuff)
        else
            _openDetailEvent.value = Event(foodStuff.id)
    }

}