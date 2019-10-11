package com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "stump_table")
data class Stump(
    @PrimaryKey
    @ColumnInfo(name = "stump_id")
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "date")
    val date: Long,
    @ColumnInfo(name = "stump_category")
    val category: Int
)