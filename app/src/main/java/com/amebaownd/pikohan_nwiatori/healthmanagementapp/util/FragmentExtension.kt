package com.amebaownd.pikohan_nwiatori.healthmanagementapp.util

import androidx.fragment.app.Fragment
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.ServiceLoader
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.ViewModelFactory

fun Fragment.getViewModelFactory(): ViewModelFactory {
//    val app = requireContext().applicationContext
//    val health = app as HealthManagementApp
//    val repository = health.repository
    val repository =
        ServiceLoader.provideFoodStuffRepository(
            requireContext()
        )
    return ViewModelFactory((repository))
}