package com.amebaownd.pikohan_nwiatori.healthmanagementapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.repository.FoodStuffRepository
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.foodstaff.FoodStuffViewModel
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.foodstaff.addEditFoodStuff.AddEditFoodStuffViewModel
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.foodstaff.detailFoodStuff.DetailFoodStuffViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory (
    private val foodStuffRepository: FoodStuffRepository
):ViewModelProvider.NewInstanceFactory(){

    override fun <T: ViewModel> create(modelClass: Class<T>)=
        with(modelClass){
            when{
                isAssignableFrom(FoodStuffViewModel::class.java)->
                    FoodStuffViewModel(foodStuffRepository)
                isAssignableFrom(AddEditFoodStuffViewModel::class.java)->
                    AddEditFoodStuffViewModel(foodStuffRepository)
                isAssignableFrom(DetailFoodStuffViewModel::class.java)->
                    DetailFoodStuffViewModel(foodStuffRepository)
                else->
                    throw IllegalArgumentException("Unknown ViewModelclass ${modelClass}")
            }
        }  as T
}