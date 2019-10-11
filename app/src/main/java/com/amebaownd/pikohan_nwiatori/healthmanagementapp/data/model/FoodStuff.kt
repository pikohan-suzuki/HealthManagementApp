package com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "foodstuff_table")

data class FoodStuff(
    @PrimaryKey
    @ColumnInfo(name = "foodstuff_id")
    val id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "food_name")
    val name: String,
    @ColumnInfo(name = "food_group")
    val food_group: Int,
    @ColumnInfo(name = "weight_of_one")
    val weight: Int,
    @ColumnInfo(name = "kcal")
    val kcal_per: Float,
    @ColumnInfo(name = "protein")
    val protein_per: Float,
    @ColumnInfo(name = "carbohydrate")
    val carbohydrate_per: Float,
    @ColumnInfo(name = "total_fat")
    val fat_per: Float
) :Serializable{
    val weightForList
        get() = weight.toString() + "g/one"
    val foodGroupForList
        get() = when (food_group) {
            1 -> FoodGroup.CATEGORY1
            2 -> FoodGroup.CATEGORY2
            3 -> FoodGroup.CATEGORY3
            4 -> FoodGroup.CATEGORY4
            5 -> FoodGroup.CATEGORY5
            6 -> FoodGroup.CATEGORY6
            else -> FoodGroup.OTHERS
        }
    val kcalPerForList
        get() = kcal_per.toString() + "kcal"
    val proteinPerForList
        get() = protein_per.toString() + "g"
    val carbohydratePerForList
        get() = carbohydrate_per.toString() + "g"
    val fatPerForList
        get() = fat_per.toString() + "g"
}