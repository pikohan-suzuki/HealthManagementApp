package com.amebaownd.pikohan_nwiatori.healthmanagementapp.foodstaff

import android.app.Application
import androidx.lifecycle.*
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.HealthDatabase
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.FoodStuff
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.repository.FoodStuffRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class AddEditFoodStuffViewModel(application: Application) : AndroidViewModel(application) {
    private val foodStuffRepository: FoodStuffRepository

    val name = MutableLiveData<String>()
    val kcalPer = MutableLiveData<String>()
    val proteinPer = MutableLiveData<String>()
    val carbohydratePer = MutableLiveData<String>()
    val fatPer = MutableLiveData<String>()

    //private val args :AddEditFoodStuffArgs by navArgs

    private val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)


    init {
        val foodStuffDao = HealthDatabase.getDatabase(application, scope).foodStuffDao()
        foodStuffRepository = FoodStuffRepository(foodStuffDao)
    }

    fun start(id: String?) {
        if(id!=null)
            foodStuffRepository.loadFoodStuff(id)?.let{
                name.value = it.name
                kcalPer.value=it.kcalPerForList
                proteinPer.value=it.proteinPerForList
                carbohydratePer.value = it.carbohydratePerForList
                fatPer.value=it.fatPerForList
            }
    }

    fun insert(foodStuff: FoodStuff) = viewModelScope.launch {
        foodStuffRepository.insert(foodStuff)
    }

    fun saveFoodStuff() {
        val currentFoodStuff = FoodStuff(
//            name = this.name.value!!,
//            kcal_per = this.kcalPer.value!!.toFloat(),
//            protein_per = this.proteinPer.value!!.toFloat(),
//            carbohydrate_per = this.carbohydratePer.value!!.toFloat(),
//            fat_per = this.fatPer.value!!.toFloat(),
//            weight = 199,
//            food_group = 1
            name = this.name.value!!,
            kcal_per = this.kcalPer.value!!.toFloat(),
            protein_per = this.proteinPer.value!!.toFloat(),
            carbohydrate_per = this.carbohydratePer.value!!.toFloat(),
            fat_per = this.fatPer.value!!.toFloat(),
            weight = 199,
            food_group = 1
        )
        if (currentFoodStuff != null)
            insert(currentFoodStuff)
    }
}