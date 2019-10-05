package com.amebaownd.pikohan_nwiatori.healthmanagementapp.foodstaff

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.R
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.databinding.FragmentFoodstuffsBinding
import kotlinx.android.synthetic.main.fragment_foodstuffs.*

class FoodStuffsFragment: Fragment() {

    private  val foodStuffViewModel: FoodStuffViewModel by lazy { ViewModelProviders.of(this).get(FoodStuffViewModel::class.java)}

    private lateinit var viewDataBinding:FragmentFoodstuffsBinding
    private lateinit var listAdapter:FoodStuffsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentFoodstuffsBinding.inflate(inflater,container,false).apply {
            viewModel = foodStuffViewModel
        }
        setHasOptionsMenu(true)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



//        foodStuffViewModel = ViewModelProviders.of(this).get(FoodStuffViewModel::class.java)



    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupRecyclerView()
        setupFub()
        openDetail()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openFilterPopUpMenu(){

    }

    private fun setupFub(){
        add_foodstuff_fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            navigateToAddFoodstuffFragment()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu,inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list, menu)
    }

    private fun navigateToAddFoodstuffFragment(){
        val action=
            FoodStuffsFragmentDirections
                .actionFoodStuffsToAddEditFoodStuff(
                null,
                    "Add Foodstuff"
                )
       findNavController().navigate(action)
    }

    private fun openDetail(){
        foodStuffViewModel.openDetailEvent.observe(this, Observer {
            val action =
                FoodStuffsFragmentDirections
                .actionFoodStuffsToDetailFoodStuffFragment(it)
            findNavController().navigate(action)
        })
    }

    private fun setupRecyclerView(){
        val viewModel = viewDataBinding.viewModel
        if(viewModel != null){
            listAdapter = FoodStuffsAdapter(foodStuffViewModel)
            viewDataBinding.foodstuffsList.adapter=listAdapter
            viewDataBinding.foodstuffsList.addItemDecoration(DividerItemDecoration(this.context,DividerItemDecoration.VERTICAL))
        }
    }
}