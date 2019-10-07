package com.amebaownd.pikohan_nwiatori.healthmanagementapp.foodstaff

import android.app.Application
import androidx.lifecycle.*
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.Event
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.HealthDatabase
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.FoodStuff
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.repository.FoodStuffRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FoodStuffViewModel (private val repository: FoodStuffRepository):ViewModel(){
    val allFoodStuffs : LiveData<List<FoodStuff>> = repository.allFoodStuffs

    private val _openDetailEvent = MutableLiveData<Event<String>>()
    val openDetailEvent:LiveData<Event<String>> = _openDetailEvent

    init{
        _openDetailEvent.value=Event("")
    }
    fun openDetail(taskId:String){
        _openDetailEvent.value = Event(taskId)
    }

    fun insert(foodstuff:FoodStuff) = viewModelScope.launch {
        repository.insert(foodstuff)
    }

}