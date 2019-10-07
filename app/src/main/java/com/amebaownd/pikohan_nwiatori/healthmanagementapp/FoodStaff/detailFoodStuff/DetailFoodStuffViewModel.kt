package com.amebaownd.pikohan_nwiatori.healthmanagementapp.foodstaff.detailFoodStuff

import androidx.lifecycle.*
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.Event
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.FoodStuff
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.repository.FoodStuffRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailFoodStuffViewModel(private val repository: FoodStuffRepository) : ViewModel() {
    private val _foodStuff=MutableLiveData<FoodStuff>()
    var foodStuff =_foodStuff

    private var _isEditStarted: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val isEditStarted: LiveData<Event<Boolean>> = _isEditStarted

    fun start(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
                _foodStuff.postValue(repository.loadFoodStuff(id))
            }
    }

    fun editFoodStuff() {
        _isEditStarted.value = Event(true)
    }
}