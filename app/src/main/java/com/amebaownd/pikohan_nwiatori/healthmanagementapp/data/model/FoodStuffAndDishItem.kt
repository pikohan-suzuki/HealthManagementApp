package com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model

import androidx.room.Embedded
import androidx.room.Relation

class FoodStuffAndDishItem {
    @Embedded
    lateinit var foodStuff: FoodStuff

    @Relation(parentColumn = "id",entityColumn = "dish_id")
    lateinit var dishItem:List<DishItem>
}