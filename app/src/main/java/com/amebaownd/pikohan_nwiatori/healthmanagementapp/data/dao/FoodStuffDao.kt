package com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.FoodStuff

@Dao
interface FoodStuffDao {
    @Query("SELECT * FROM foodstuff_table ORDER BY foodstuff_id ASC")
    fun getAllFoodStuffs():LiveData<List<FoodStuff>>

    @Query("SELECT * FROM foodstuff_table WHERE foodstuff_id =(:id) LIMIT 1")
    fun getById(id:String):LiveData<FoodStuff>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(foodStuff: FoodStuff)

    @Query("DELETE FROM foodstuff_table")
    suspend fun deleteAll()
}