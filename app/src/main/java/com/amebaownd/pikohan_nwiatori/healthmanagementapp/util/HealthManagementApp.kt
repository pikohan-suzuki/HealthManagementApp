package com.amebaownd.pikohan_nwiatori.healthmanagementapp.util


import android.app.Application
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.ServiceLoader
import com.amebaownd.pikohan_nwiatori.healthmanagementapp.data.repository.FoodStuffRepository

class HealthManagementApp : Application() {
    val repository: FoodStuffRepository
        get() = ServiceLoader.provideFoodStuffRepository(
            this
        )

    override fun onCreate() {
        super.onCreate()
    }
}