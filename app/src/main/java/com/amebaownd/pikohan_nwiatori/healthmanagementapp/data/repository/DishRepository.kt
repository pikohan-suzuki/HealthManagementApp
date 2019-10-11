package com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.repository

import androidx.lifecycle.LiveData
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.dao.DishDao
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.Dish

class DishRepository(private val dishDao:DishDao){
    val allDishes :LiveData<List<Dish>> =dishDao.getAll()

    suspend fun insert(dish:Dish){
        dishDao.insert(dish)
    }

    suspend fun delete(dish:Dish){
        dishDao.delete(dish)
    }

    suspend fun update(dish:Dish){
        dishDao.update(dish)
    }

    fun filterItem(regex:String):List<Dish>{
        val key = regex+"%"
        return dishDao.getByKey(regex)
    }

    fun loadItem(id:String):Dish{
        return dishDao.getById(id)
    }
}