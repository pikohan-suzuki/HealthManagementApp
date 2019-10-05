package com.amebaownd.pikohan_nwiatori.healthmanagementapp.foodstaff

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.R
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.FoodStuff
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.databinding.FragmentAddEditFoodstuffBinding
import kotlinx.android.synthetic.main.fragment_foodstuffs.*

class AddEditFoodStuff: Fragment() {

    private  val foodStuffViewModel: AddEditFoodStuffViewModel by lazy { ViewModelProviders.of(this).get(AddEditFoodStuffViewModel::class.java)}

    lateinit var viewDataBinding : FragmentAddEditFoodstuffBinding

    private val args:AddEditFoodStuffArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentAddEditFoodstuffBinding.inflate(inflater,container,false)
        viewDataBinding.lifecycleOwner=viewLifecycleOwner
        viewDataBinding.viewModel = foodStuffViewModel
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.foodstuffId?.let {
            foodStuffViewModel.start(it)
        }

        foodStuffViewModel.name.observe(this, Observer {
            Toast.makeText(this.context,"aaaaaaaaaaaa",Toast.LENGTH_LONG).show()
        })
    }


    private fun navigateToFoodStuffsFragment(){
        val action=
            AddEditFoodStuffDirections
                .actionAddEditFoodStuffToFoodStuffs()
        findNavController().navigate(action)
    }


}