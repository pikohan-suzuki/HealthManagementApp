package com.amebaownd.pikohan_nwiatori.healthmanagementapp.dish.addEditDish

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.R
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.*
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.databinding.AddEditDishItemBinding
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.databinding.DishesItemBinding
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.util.MyContext

class AddEditDishAdapter (private val viewModel: AddEditDishViewModel):
    ListAdapter<AddedFoodStuff, AddEditDishAdapter.ViewHolder>(AddEditDishItemCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=getItem(position)
        holder.bind(viewModel,item)
    }

    class ViewHolder(private val binding:AddEditDishItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(viewModel: AddEditDishViewModel, item:AddedFoodStuff){
            binding.viewModel = viewModel
            binding.addEditItemAmountSpinner.setSelection(MyContext.getContext().resources.getStringArray(R.array.spinner_values).indexOf(item.one_amount) ?: 0)
            binding.addedFoodStuff = item
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent:ViewGroup) : ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AddEditDishItemBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
    }
}

class AddEditDishItemCallback:DiffUtil.ItemCallback<AddedFoodStuff>(){
    override fun areItemsTheSame(oldItem: AddedFoodStuff, newItem: AddedFoodStuff): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: AddedFoodStuff, newItem: AddedFoodStuff): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

}