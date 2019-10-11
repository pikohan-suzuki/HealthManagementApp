package com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model

import androidx.room.ColumnInfo
import java.util.*

data class AddedFoodStuff(
    val id: String,
    val name: String,
    val food_group: Int,
    val weight: Int,
    val kcal_per: Float,
    val protein_per: Float,
    val carbohydrate_per: Float,
    val fat_per: Float,
    val is_gram:Boolean,
    val gram_amount:Float?,
    val one_amount:String?
){
    val gramAmountForList
        get()=gram_amount.toString()
    val one_amountForCalc
        get()=one_amount?.toFloat()
}