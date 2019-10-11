package com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.FoodStuff

@Dao
interface FoodStuffDao {
    @Query("SELECT * FROM foodstuff_table ORDER BY foodstuff_id ASC")
    fun getAllFoodStuffs():LiveData<List<FoodStuff>>

    @Query("SELECT * FROM foodstuff_table ORDER BY food_name ASC")
    fun getAll():List<FoodStuff>

    @Query("SELECT * FROM foodstuff_table WHERE foodstuff_id =(:id)")
    fun getById(id:String):FoodStuff

    @Query("SELECT * FROM foodstuff_table WHERE foodstuff_id =(:id)")
    fun getById2(id:String):LiveData<List<FoodStuff>>

    @Query("SELECT * FROM foodstuff_table WHERE food_name LIKE (:regex) ORDER BY food_name ASC")
    fun getByRegex(regex:String):List<FoodStuff>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(foodStuff: FoodStuff)

    @Update
    suspend fun update(foodStuff: FoodStuff)

    @Query("DELETE FROM foodstuff_table")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(foodStuff:FoodStuff)
}