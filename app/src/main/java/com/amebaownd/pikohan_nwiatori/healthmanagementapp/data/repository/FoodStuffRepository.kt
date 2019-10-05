package com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.repository

import androidx.lifecycle.LiveData
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.dao.FoodStuffDao
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.FoodStuff

class FoodStuffRepository (private val foodStuffDao: FoodStuffDao){
    val allFoodStuffs: LiveData<List<FoodStuff>> = foodStuffDao.getAllFoodStuffs()

    suspend fun insert(foodStuff: FoodStuff) {
        foodStuffDao.insert(foodStuff)
    }

    fun loadFoodStuff(id:String):FoodStuff?{
       return foodStuffDao.getById(id).value
    }
}