package com.amebaownd.pikohan_nwiatori.healthmanagementapp.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.AddedFoodStuff
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.Dish
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.FoodStuff
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.dish.DishAdapter
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.dish.addEditDish.AddEditDishAdapter
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.foodstaff.FoodStuffsAdapter

@BindingAdapter("app:foodstuff_items")
fun setFoodStuffItems(listView: RecyclerView, items:List<FoodStuff>?){
    if(items != null)
        (listView.adapter as FoodStuffsAdapter).submitList(items)
}

@BindingAdapter("app:dish_items")
fun setDishItems(listView: RecyclerView,items:List<Dish>?){
    if(items!=null)
        (listView.adapter as DishAdapter).submitList(items)
}

@BindingAdapter("app:added_foodstuff_items")
fun setAddEditItems(listView: RecyclerView,items:List<AddedFoodStuff>?){
    if(items!=null)
        (listView.adapter as AddEditDishAdapter).submitList(items)
}