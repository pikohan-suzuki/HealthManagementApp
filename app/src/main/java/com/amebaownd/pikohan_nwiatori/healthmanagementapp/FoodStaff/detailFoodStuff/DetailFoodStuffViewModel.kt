package com.amebaownd.pikohan_nwiatori.healthmanagementapp.foodstaff.detailFoodStuff

import androidx.lifecycle.*
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.util.Event
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.FoodStuff
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.repository.FoodStuffRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailFoodStuffViewModel(private val repository: FoodStuffRepository) : ViewModel() {
    private val _foodStuff = MutableLiveData<FoodStuff>()
    var foodStuff = _foodStuff

    private val _name = MutableLiveData<String>()
    var name: LiveData<String> = _name

    private val _category = MutableLiveData<String>()
    var category: LiveData<String> = _category

    private val _weightOfOne = MutableLiveData<String>()
    var weightOfOne: LiveData<String> = _weightOfOne

    private val _calories = MutableLiveData<String>()
    var calories: LiveData<String> = _calories

    private val _protein = MutableLiveData<String>()
    var protein: LiveData<String> = _protein

    private val _carbohydrate = MutableLiveData<String>()
    var carbohydrate: LiveData<String> = _carbohydrate

    private val _fat = MutableLiveData<String>()
    var fat: LiveData<String> = _fat

    private val _nutrientsTitle = MutableLiveData<String>("Nutrients/100g")
    var nutrientsTitle: LiveData<String> = _nutrientsTitle

    private var _isEditStarted: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val isEditStarted: LiveData<Event<Boolean>> = _isEditStarted

    val isDeleted: MutableLiveData<Event<Boolean>> = MutableLiveData(
        Event(false)
    )

    fun start(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val foodStuff = repository.loadFoodStuff(id)
            _foodStuff.postValue(foodStuff)
            foodStuff.let {
                _name.postValue(it.name)
                _category.postValue(it.foodGroupForList.str)
                _weightOfOne.postValue(it.weightForList)
                _calories.postValue(String.format("%.1f",it.kcal_per) + "kcal")
                _protein.postValue(String.format("%.1f",it.protein_per) + "g")
                _carbohydrate.postValue(String.format("%.1f",it.carbohydrate_per) + "g")
                _fat.postValue(String.format("%.1f",it.fat_per) + "g")
            }

        }
    }

    fun onSwitchStateChanged(idChecked: Boolean) {
        if (idChecked) {
            _nutrientsTitle.value = "Nutrients/one"
            _foodStuff.value?.let {
                _calories.value = (String.format("%.1f",it.kcal_per * it.weight/100))+"kcal"
                _protein.value = String.format("%.1f",it.protein_per * it.weight/100)+"g"
                _carbohydrate.value = String.format("%.1f",it.carbohydrate_per *  it.weight/100)+"g"
                _fat.value = String.format("%.1f",it.fat_per *  it.weight/100)+"g"
            }
        } else {
            _nutrientsTitle.value = "Nutrients/100g"
            _foodStuff.value?.let {
                _calories.value = String.format("%.1f",it.kcal_per)+"kcal"
                _protein.value = String.format("%.1f",it.protein_per)+"g"
                _carbohydrate.value = String.format("%.1f",it.carbohydrate_per)+"g"
                _fat.value = String.format("%.1f",it.fat_per)+"g"
            }
        }
    }

    fun editFoodStuff() {
        _isEditStarted.value = Event(true)
    }

    fun delete() {
        _foodStuff.value?.let {
            viewModelScope.launch(Dispatchers.IO) {
                repository.delete(it)
                isDeleted.postValue(
                    Event(
                        true
                    )
                )
            }
        }

    }
}