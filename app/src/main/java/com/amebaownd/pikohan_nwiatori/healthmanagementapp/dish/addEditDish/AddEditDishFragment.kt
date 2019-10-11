package com.amebaownd.pikohan_nwiatori.healthmanagementapp.dish.addEditDish

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.R
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.databinding.FragmentAddEditDishBinding
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.dish.DishAdapter
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.util.EventObserver
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.util.getViewModelFactory

class AddEditDishFragment : Fragment(){

    private val viewModel: AddEditDishViewModel by viewModels<AddEditDishViewModel> { getViewModelFactory() }
    private lateinit var  dataBinding:FragmentAddEditDishBinding

    private val args:AddEditDishFragmentArgs by navArgs()
    private lateinit var adapter :AddEditDishAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = FragmentAddEditDishBinding.inflate(inflater, container, false)
        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = this.viewLifecycleOwner
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(args.dishId != null)
            viewModel.start(args.dishId!!)
        args.foodstuff?.let {
            viewModel.selectedFoodStuff(it)
        }
        setupList()
        showDialog()
        addFoodStuff()
        addedFoodStuff()
    }

    private fun showDialog() {
        viewModel.dialogOpenEvent.observe(this,
            EventObserver {
                if (it) {
                    val alertDialog = AlertDialog.Builder(this.context)
                        .setItems(
                            resources.getStringArray(R.array.dish_group),
                            dialogItemClickListener()
                        )
                        .setOnCancelListener(dialogCancelListener())
                        .setOnDismissListener(dialogDismissListener())
                    alertDialog.show()
                }
            })

    }

    private fun setupList(){
        adapter = AddEditDishAdapter(viewModel)
        dataBinding.addEditDishList.adapter=adapter
        dataBinding.addEditDishList.addItemDecoration(
            DividerItemDecoration(
                this.context,
                DividerItemDecoration.VERTICAL
            )
        )
    }
    private fun dialogItemClickListener() =
        DialogInterface.OnClickListener { _, num ->
            viewModel.onFoodGroupSelected(resources.getStringArray(R.array.dish_group)[num])
        }
    private fun dialogCancelListener()=
        DialogInterface.OnCancelListener {
            viewModel.onDialogCanceledOrDismissed()
        }
    private fun dialogDismissListener()=
        DialogInterface.OnDismissListener {
            viewModel.onDialogCanceledOrDismissed()
        }
    private fun addFoodStuff(){
        viewModel.addFoodStuffEvent.observe(this,EventObserver{
            if(it)
                navigateToFoodStuffs()
        })

    }

    private fun addedFoodStuff(){
        viewModel.addedFoodStuffEvent.observe(this,EventObserver{
            if(it)
                adapter.notifyDataSetChanged()
        })
    }

    private fun navigateToFoodStuffs(){
        val action = AddEditDishFragmentDirections
            .actionAddEditDishFragmentToFoodStuffsFragment(
                isAddEditDish = true
            )
        findNavController().navigate(action)
    }
}