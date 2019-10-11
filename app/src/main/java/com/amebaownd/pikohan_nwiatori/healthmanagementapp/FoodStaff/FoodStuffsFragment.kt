package com.amebaownd.pikohan_nwiatori.healthmanagementapp.foodstaff

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.util.EventObserver
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.R
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.model.FoodStuff
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.databinding.FragmentFoodstuffsBinding
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.util.getViewModelFactory
import kotlinx.android.synthetic.main.fragment_foodstuffs.*

class FoodStuffsFragment : Fragment(){

    private val foodStuffViewModel: FoodStuffViewModel by viewModels<FoodStuffViewModel> { getViewModelFactory() }
    private val args:FoodStuffsFragmentArgs by navArgs()

    private lateinit var viewDataBinding: FragmentFoodstuffsBinding
    private lateinit var listAdapter: FoodStuffsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentFoodstuffsBinding.inflate(inflater, container, false).apply {
            viewModel = foodStuffViewModel
        }
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setHasOptionsMenu(true)
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        setupFub()
        foodStuffViewModel.start(args.isAddEditDish)
        openDetail()
        selectFoodStuff()
        onSearchTextChanged()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sort ->{

                true
            }
            R.id.filter_food_group1->{

                true
            }
            R.id.filter_food_group2->{

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openFilterPopUpMenu() {
//        val view = activity?.findViewById<View>(R.id.action_filter)?:return
//        PopupMenu(requireContext(),view).run {
//            menuInflater.inflate(R.menu.sort_menu,menu)
//            setOnMenuItemClickListener {
//
//            }
//        }
    }

    private fun setupFub() {
        add_foodstuff_fab.setOnClickListener { view ->
            //            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            navigateToAddFoodstuffFragment()
        }
    }
    
    private fun onSearchTextChanged(){
        viewDataBinding.foodstuffsSearch.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {foodStuffViewModel.filterItems(it)  }
                return true
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list, menu)
    }

    private fun navigateToAddFoodstuffFragment() {
        val action =
            FoodStuffsFragmentDirections
                .actionFoodStuffsToAddEditFoodStuffFragment(
                    null,
                    "Add Foodstuff"
                )
        findNavController().navigate(action)
    }

    private fun navigateToAddEditDishFragment(foodStuff: FoodStuff){
        val action=
            FoodStuffsFragmentDirections
                .actionFoodStuffsFragmentToAddEditDishFragment(
                    dishId = null,
                    title = resources.getString(R.string.add_dish_title),
                    foodstuff = foodStuff
                )
        findNavController().navigate(action)
    }

    private fun openDetail() {
        foodStuffViewModel.openDetailEvent.observe(this,
            EventObserver {
                if (it.isNotBlank() && it.isNotEmpty()) {
                    val action =
                        FoodStuffsFragmentDirections
                            .actionFoodStuffsToDetailFoodStuffFragment(it)
                    findNavController().navigate(action)
                }
            })
    }
    private fun selectFoodStuff(){
        foodStuffViewModel.selectFoodStuffEvent.observe(this,EventObserver{
            it.let{
                navigateToAddEditDishFragment(it)
            }
        })
    }

    private fun setupRecyclerView() {
        val viewModel = viewDataBinding.viewModel
        if (viewModel != null) {
            listAdapter = FoodStuffsAdapter(foodStuffViewModel)
            viewDataBinding.foodstuffsList.adapter = listAdapter
            viewDataBinding.foodstuffsList.addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

}