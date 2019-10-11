package com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "schedule_table")
data class Schedule(
    @PrimaryKey
    @ColumnInfo(name = "schedule_id")
    val schedule_id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name="training_id")
    val training_id:String,
    @ColumnInfo(name="is_repeat")
    val is_repeat:Boolean,
    @ColumnInfo(name="start_date")
    val start_day:Long,
    @ColumnInfo(name="end_date")
    val end_day:Long?
)