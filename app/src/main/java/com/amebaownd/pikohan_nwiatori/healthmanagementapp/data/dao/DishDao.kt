package com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.dao

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.*
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.Dish

@Dao
interface DishDao {

    @Query("SELECT * FROM dish_table ORDER BY dish_name ASC")
    fun getAll():LiveData<List<Dish>>

    @Query("SELECT * FROM dish_table WHERE dish_id = (:id)  ")
    fun getById(id:String):Dish

    @Query("SELECT * FROM dish_table WHERE dish_name LIKE (:key)")
    fun getByKey(key:String):List<Dish>



    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dish:Dish)

    @Delete
    suspend fun delete(dish:Dish)

    @Update
    suspend fun update(dish:Dish)
}