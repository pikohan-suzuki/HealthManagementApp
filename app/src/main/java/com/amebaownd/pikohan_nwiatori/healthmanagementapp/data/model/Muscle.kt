package com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "muscle_table")
data class Muscle(
    @PrimaryKey
    @ColumnInfo(name = "muscle_id")
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "parts_category")
    val parts_category: Int,
    @ColumnInfo(name = "training_category")
    val training_category: Int
)