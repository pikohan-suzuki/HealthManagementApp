package com.amebaownd.pikohan_nwiatori.healthmanagementapp.Data.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "foodstuff_table")
data class FoodStuff(
    @PrimaryKey
    @ColumnInfo(name = "foodstuff_id")
    val id:String = UUID.randomUUID().toString(),
    @ColumnInfo(name="food_name")
    val name:String,
    @ColumnInfo(name="food_group")
    val food_group:Int,
    @ColumnInfo(name="weight_of_one")
    val weight:Int,
    @ColumnInfo(name="kcal")
    val kcal_per:Float,
    @ColumnInfo(name="protein")
    val protein_per:Float,
    @ColumnInfo(name="carbohydrate")
    val carbohydrate_per:Float,
    @ColumnInfo(name="total_fat")
    val fat_per:Float
)