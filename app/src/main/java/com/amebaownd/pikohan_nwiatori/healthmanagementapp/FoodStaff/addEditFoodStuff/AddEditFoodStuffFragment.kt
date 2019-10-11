package com.amebaownd.pikohan_nwiatori.healthmanagementapp.foodstaff.addEditFoodStuff

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.util.EventObserver
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.R
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.databinding.FragmentAddEditFoodstuffBinding
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.util.getViewModelFactory

class AddEditFoodStuffFragment : Fragment() {

    private val foodStuffViewModel: AddEditFoodStuffViewModel by viewModels<AddEditFoodStuffViewModel> { getViewModelFactory() }

    lateinit var viewDataBinding: FragmentAddEditFoodstuffBinding

    private val args: AddEditFoodStuffFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentAddEditFoodstuffBinding.inflate(inflater, container, false)
        viewDataBinding.lifecycleOwner = viewLifecycleOwner
        viewDataBinding.viewModel = foodStuffViewModel
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        foodStuffViewModel.start(args.foodstuffId)
        setupSwitch()
        showDialog()
    }

    private fun setupSwitch() {
        viewDataBinding.addEditNutrientsSwitch.setOnCheckedChangeListener { compoundButton, isChecked ->
            foodStuffViewModel.onCheckStateChanged(isChecked)
        }
    }

    private fun showDialog() {
        foodStuffViewModel.dialogOpenEvent.observe(this,
            EventObserver {
                if (it) {
                    val alertDialog = AlertDialog.Builder(this.context)
                        .setItems(
                            resources.getStringArray(R.array.food_group),
                            dialogItemClickListener()
                        )
                        .setOnCancelListener(dialogCancelListener())
                        .setOnDismissListener(dialogDismissListener())
                    alertDialog.show()
                }
            })

    }
    private fun dialogItemClickListener() =
        DialogInterface.OnClickListener { _, num ->
            foodStuffViewModel.onFoodGroupSelected(resources.getStringArray(R.array.food_group)[num])
        }
    private fun dialogCancelListener()=
        DialogInterface.OnCancelListener {
            foodStuffViewModel.onDialogCanceledOrDismissed()
        }
    private fun dialogDismissListener()=
        DialogInterface.OnDismissListener {
            foodStuffViewModel.onDialogCanceledOrDismissed()
        }
}