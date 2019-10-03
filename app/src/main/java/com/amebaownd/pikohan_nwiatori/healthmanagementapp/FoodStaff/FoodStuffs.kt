package com.amebaownd.pikohan_nwiatori.healthmanagementapp.FoodStaff

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_foodstuffs.*

class FoodStuffs: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_foodstuffs,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupFub()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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
            FoodStuffsDirections
                .actionFoodStuffsToAddEditFoodStuff(
                null,
                    "Add Foodstuff"
                )
       findNavController().navigate(action)

    }
}