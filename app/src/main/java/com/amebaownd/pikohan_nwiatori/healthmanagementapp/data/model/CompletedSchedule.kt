package com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "completed_schedule_table")
data class CompletedSchedule(
    @PrimaryKey
    @ColumnInfo(name = "muscle_id")
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "date")
    val name: String,
    @ColumnInfo(name = "dish_group")
    val category: Int
)