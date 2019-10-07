package com.amebaownd.pikohan_nwiatori.healthmanagementapp.foodstaff.detailFoodStuff

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.EventObserver
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.databinding.FragmentDetailFoodstuffBinding
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.util.getViewModelFactory

class DetailFoodStuffFragment: Fragment() {

    private val detailFoodStuffViewModel : DetailFoodStuffViewModel by viewModels<DetailFoodStuffViewModel> { getViewModelFactory() }
    private val args : DetailFoodStuffFragmentArgs by navArgs()
    private lateinit var databinding :FragmentDetailFoodstuffBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        databinding = FragmentDetailFoodstuffBinding.inflate(inflater,container,false)
        databinding.viewModel = detailFoodStuffViewModel
        databinding.lifecycleOwner=viewLifecycleOwner
        return databinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        startEdit()
        detailFoodStuffViewModel.start(args.foodstuffId)
    }

    private fun startEdit(){
        detailFoodStuffViewModel.isEditStarted.observe(this, EventObserver {
                navigateToEditFragment()
        })
    }

    private fun navigateToEditFragment(){
        val action = DetailFoodStuffFragmentDirections
            .actionDetailFoodStuffFragmentToAddEditFoodStuffFragment(
                detailFoodStuffViewModel.foodStuff.value?.id,
                "Edit Fragment"
            )
        findNavController().navigate(action)
    }
}