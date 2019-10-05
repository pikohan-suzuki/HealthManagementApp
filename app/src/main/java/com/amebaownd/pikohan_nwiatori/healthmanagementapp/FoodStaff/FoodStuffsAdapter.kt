package com.amebaownd.pikohan_nwiatori.healthmanagementapp.foodstaff

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.FoodStuff
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.databinding.FoodstuffItemBinding
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.foodstaff.FoodStuffsAdapter.ViewHolder

class FoodStuffsAdapter (private val viewModel: FoodStuffViewModel):
        ListAdapter<FoodStuff, ViewHolder>(FoodStuffDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=getItem(position)
        holder.bind(viewModel,item)
    }

    class ViewHolder(private val binding:FoodstuffItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(viewModel: FoodStuffViewModel,item:FoodStuff){
            binding.viewModel = viewModel
            binding.foodstuff = item
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent:ViewGroup) : ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FoodstuffItemBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
    }
}

class FoodStuffDiffCallback:DiffUtil.ItemCallback<FoodStuff>(){
    override fun areItemsTheSame(oldItem: FoodStuff, newItem: FoodStuff): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FoodStuff, newItem: FoodStuff): Boolean {
        return oldItem == newItem
    }

}