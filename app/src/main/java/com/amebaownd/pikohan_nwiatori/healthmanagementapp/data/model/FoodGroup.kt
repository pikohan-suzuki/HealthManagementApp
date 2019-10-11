package com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model

import com.amebaownd.pikohan_nwiatori.healthmanagementapp.R
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.util.MyContext

enum class FoodGroup(val id:Int,val str:String){
    CATEGORY1(1,MyContext.getString(R.string.food_group1)),
    CATEGORY2(2,MyContext.getString(R.string.food_group2)),
    CATEGORY3(3,MyContext.getString(R.string.food_group3)),
    CATEGORY4(4,MyContext.getString(R.string.food_group4)),
    CATEGORY5(5,MyContext.getString(R.string.food_group5)),
    CATEGORY6(6,MyContext.getString(R.string.food_group6)),
    OTHERS(7,MyContext.getString(R.string.food_group_others)),
}