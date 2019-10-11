package com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "hard_table",
    primaryKeys = ["aerobic_id","hard_name"],
    foreignKeys = [ForeignKey(
        entity = Aerobic::class,
        parentColumns = ["aerobic_id"],
        childColumns = ["aerobic_id"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)])
data class AerobicHard(
    @ColumnInfo(name = "aerobic_id")
    val id :String,
    @ColumnInfo(name="hard_name")
    val name:String,
    @ColumnInfo(name="mets")
    val mets:Float
)