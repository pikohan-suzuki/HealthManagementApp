package com.amebaownd.pikohan_nwiatori.healthmanagementapp

import android.content.Context
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.HealthDatabase
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.repository.FoodStuffRepository

object ServiceLoader {
    private var database : HealthDatabase? = null
    var repository:FoodStuffRepository? = null

    fun provideFoodStuffRepository(context: Context):FoodStuffRepository{
        synchronized(this) {
            return repository ?: createFoodStuffRepository(context)
        }
    }

    private fun createFoodStuffRepository(context:Context):FoodStuffRepository{
        val database = database ?: createDatabase(context)
        val result = FoodStuffRepository(database.foodStuffDao())
        repository = result
        return result
    }

    private fun createDatabase(context:Context):HealthDatabase{
        val result = HealthDatabase.getDatabase(context)
        database=result
        return result
    }
}