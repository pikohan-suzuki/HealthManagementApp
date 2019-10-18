package com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.Muscle

@Dao
interface MuscleDao {

    @Query("SELECT * FROM muscle_table ORDER BY muscle_id ASC")
    fun getAll():LiveData<List<Muscle>>

    @Query("SELECT * FROM muscle_table WHERE muscle_id = (:id)")
    fun getById(id:String):LiveData<Muscle>

    @Insert
    fun insert(muscle: Muscle)

    @Update
    fun update(muscle: Muscle)

    @Query("DELETE FROM muscle_table")
    fun deleteAll()

    @Delete
    fun delte(muscle: Muscle)
}