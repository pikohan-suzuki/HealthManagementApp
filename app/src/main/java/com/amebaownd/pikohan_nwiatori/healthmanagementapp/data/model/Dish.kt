package com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "dish_table")
data class Dish(
    @PrimaryKey
    @ColumnInfo(name = "dish_id")
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "dish_name")
    val name: String,
    @ColumnInfo(name = "dish_group")
    val category: Int,
    @ColumnInfo(name = "total_calorie")
    val totalCalorie: Float,
    @ColumnInfo(name = "total_protein")
    val totalProtein: Float,
    @ColumnInfo(name = "total_carbohydrate")
    val totalCarbohydrate: Float,
    @ColumnInfo(name = "total_fat")
    val totalFat: Float
) {
    val calorieForList:String
        get() = String.format("%.1f",totalCalorie)+"kcal"
    val proteinForList:String
        get()=String.format("%.1f",totalProtein)+"g"
    val carbohydrateForList:String
        get()=String.format("%.1f",totalCarbohydrate)+"g"
    val fatForList:String
        get()=String.format("%.1f",totalFat)+"g"
}
