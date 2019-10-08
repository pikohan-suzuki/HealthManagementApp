package com.amebaownd.pikohan_nwiatori.healthmanagementapp.foodstaff.detailFoodStuff

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.EventObserver
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.R
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
        setHasOptionsMenu(true)
        return databinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setObserver()
        setSwitch()
        detailFoodStuffViewModel.start(args.foodstuffId)
    }

    private fun setObserver(){
        detailFoodStuffViewModel.isEditStarted.observe(this, EventObserver {
                navigateToEditFragment()
        })
        detailFoodStuffViewModel.isDeleted.observe(this,EventObserver{
            if(it) findNavController().popBackStack()
        })
    }

    private fun setSwitch(){
        databinding.weightSwitch.setOnCheckedChangeListener { compoundButton, isCkecked ->
            detailFoodStuffViewModel.onSwitchStateChanged(isCkecked)
        }
    }

    private fun navigateToEditFragment(){
        val action = DetailFoodStuffFragmentDirections
            .actionDetailFoodStuffFragmentToAddEditFoodStuffFragment(
                detailFoodStuffViewModel.foodStuff.value?.id,
                "Edit Fragment"
            )
        findNavController().navigate(action)
    }

    private fun navigateToFoodStuffsFragment(){
        val action = DetailFoodStuffFragmentDirections
            .actionDetailFoodStuffFragmentToFoodStuffsFragment()
        findNavController().navigate(action)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_detail,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.delete_foodstuff) {
            detailFoodStuffViewModel.delete()
            return true
        }
        return false
    }
}