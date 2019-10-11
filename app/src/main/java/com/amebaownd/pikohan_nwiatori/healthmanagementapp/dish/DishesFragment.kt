package com.amebaownd.pikohan_nwiatori.healthmanagementapp.dish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.R
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.databinding.FragmentDishesBinding
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.util.EventObserver
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.util.getViewModelFactory

class DishesFragment: Fragment(){

    private val viewModel :DishesViewModel by viewModels{ getViewModelFactory()}
    lateinit var viewModelDataBinding:FragmentDishesBinding
    lateinit var adapter :DishAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModelDataBinding = FragmentDishesBinding.inflate(inflater,container,false)
        viewModelDataBinding.viewModel=viewModel
        viewModelDataBinding.lifecycleOwner=this.viewLifecycleOwner
        setHasOptionsMenu(true)
        return viewModelDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupList()
        onSearchTextChanged()
        openDetail()
        addDish()
    }

    private fun setupList(){
        adapter = DishAdapter(viewModel)
        viewModelDataBinding.dishesList.adapter=adapter
        viewModelDataBinding.dishesList.addItemDecoration(
            DividerItemDecoration(
                this.context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun onSearchTextChanged(){
        viewModelDataBinding.dishesSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {viewModel.filterItem(it)  }
                return true
            }
        })
    }

    private fun openDetail(){
        viewModel.isDetailOpenEvent.observe(this,EventObserver{
            if(it.isNotBlank() && it.isNotEmpty())
                navigateToDetailDish(it)
        })
    }

    private fun addDish(){
        viewModel.isAddDishEvent.observe(this,EventObserver{
            if(it)
                navigateToAddDish()
        })
    }
    private fun navigateToAddDish(){
        val action = DishesFragmentDirections
            .actionDishesFragmentToAddEditDishFragment(
                null,
                resources.getString(R.string.edit_dish_title),
                foodstuff = null
            )
        findNavController().navigate(action)
    }

    private fun navigateToDetailDish(id:String){
        val action=DishesFragmentDirections
            .actionDishesFragmentToDetailDishFragment(id)
        findNavController().navigate(action)
    }
}