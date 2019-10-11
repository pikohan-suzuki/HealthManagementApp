package com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "aerobic_table")
data class Aerobic(
 @PrimaryKey
 @ColumnInfo(name = "aerobic_id")
 val id :String=UUID.randomUUID().toString(),
 @ColumnInfo(name="added_weight")
 val added_weight:Float
)