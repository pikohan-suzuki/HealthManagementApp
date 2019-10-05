package com.amebaownd.pikohan_nwiatori.healthmanagementapp.foodstaff

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.HealthDatabase
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.FoodStuff
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.repository.FoodStuffRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FoodStuffViewModel (application:Application):AndroidViewModel(application){
    private val repository:FoodStuffRepository
    private val coroutineContext:CoroutineContext
        get() = Job()+Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

//    private val _allFoodStuffs = MutableLiveData<List<FoodStuff>>()
//    val allFoodstuffs:LiveData<List<FoodStuff>> = _allFoodStuffs
    val allFoodStuffs : LiveData<List<FoodStuff>>

    private val _openDetailEvent = MutableLiveData<String>()
    val openDetailEvent:LiveData<String> = _openDetailEvent

    init{
        val foodStuffDao = HealthDatabase.getDatabase(application,scope).foodStuffDao()
        repository = FoodStuffRepository(foodStuffDao)
        allFoodStuffs = repository.allFoodStuffs
    }

    fun openDetail(taskId:String){
        _openDetailEvent.value = taskId
    }

    fun insert(foodstuff:FoodStuff) = viewModelScope.launch {
        repository.insert(foodstuff)
    }
}