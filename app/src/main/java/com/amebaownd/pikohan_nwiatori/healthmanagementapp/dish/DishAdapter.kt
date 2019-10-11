package com.amebaownd.pikohan_nwiatori.healthmanagementapp.dish

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.Dish
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.databinding.DishesItemBinding

class DishAdapter (private val viewModel: DishesViewModel):
    ListAdapter<Dish, DishAdapter.ViewHolder>(FoodStuffDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=getItem(position)
        holder.bind(viewModel,item)
    }

    class ViewHolder(private val binding:DishesItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(viewModel: DishesViewModel, item:Dish){
            binding.viewModel = viewModel
            binding.dish = item
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent:ViewGroup) : ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DishesItemBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
    }
}

class FoodStuffDiffCallback:DiffUtil.ItemCallback<Dish>(){
    override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean {
        return oldItem == newItem
    }

}