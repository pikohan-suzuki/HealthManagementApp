package com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model

import androidx.room.*

@Entity(tableName = "dish_item_table",
    primaryKeys = ["dish_id","foodstuff_id"],
    foreignKeys = [ForeignKey(entity = Dish::class,
        parentColumns = ["dish_id"],
        childColumns = ["dish_id"],
        onDelete =ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE),
    ForeignKey(entity = FoodStuff::class,
        parentColumns = ["foodstuff_id"],
        childColumns = ["foodstuff_id"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE)])
data class DishItem(
    @ColumnInfo(name = "dish_id")
    val dish_id:String,
    @ColumnInfo(name="foodstuff_id")
    val foodstuff_id:String,
    @ColumnInfo(name="is_gram")
    val is_gram:Boolean,
    @ColumnInfo(name="amount")
    val amount:Float
)