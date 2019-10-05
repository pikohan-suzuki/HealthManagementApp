package com.amebaownd.pikohan_nwiatori.healthmanagementapp.foodstaff

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.FoodStuff

@BindingAdapter("app:items")
fun setItems(listView: RecyclerView, items:List<FoodStuff>?){
    if(items != null)
        (listView.adapter as FoodStuffsAdapter).submitList(items)
}