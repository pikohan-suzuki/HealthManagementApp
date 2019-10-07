package com.amebaownd.pikohan_nwiatori.healthmanagementapp.foodstaff.addEditFoodStuff

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.databinding.FragmentAddEditFoodstuffBinding
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.util.getViewModelFactory

class AddEditFoodStuffFragment: Fragment() {

    private  val foodStuffViewModel: AddEditFoodStuffViewModel by viewModels<AddEditFoodStuffViewModel> { getViewModelFactory() }

    lateinit var viewDataBinding : FragmentAddEditFoodstuffBinding

    private val args: AddEditFoodStuffFragmentArgs by navArgs()

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
    }


    private fun navigateToFoodStuffsFragment(){
        val action=
            AddEditFoodStuffFragmentDirections.actionAddEditFoodStuffToFoodStuffsFragment()
        findNavController().navigate(action)
    }


}