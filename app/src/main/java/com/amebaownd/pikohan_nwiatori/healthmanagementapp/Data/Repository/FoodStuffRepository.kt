package com.amebaownd.pikohan_nwiatori.healthmanagementapp.Data.Repository

import androidx.lifecycle.LiveData
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.Data.Dao.FoodStuffDao
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.Data.Model.FoodStuff

class FoodStuffRepository (private val foodStuffDao: FoodStuffDao){
    val allFoodStuffs: LiveData<List<FoodStuff>> = foodStuffDao.getAllFoodStuffs()

    suspend fun insert(foodStuff: FoodStuff){
        foodStuffDao.insert(foodStuff)
    }
}