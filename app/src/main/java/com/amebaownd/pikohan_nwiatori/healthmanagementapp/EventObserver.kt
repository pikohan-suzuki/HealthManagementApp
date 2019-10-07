package com.amebaownd.pikohan_nwiatori.healthmanagementapp

import android.content.Context
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import java.util.*

class Event<out T>(private val context:T){
    var hasHandled = false
        private set
    fun getContextIfNotHandled():T?{
        return if(hasHandled){
            null
        }else{
            hasHandled=true
            context
        }
    }


}

class EventObserver<T>(private val onEventUnhandledContext:(T)->Unit) : Observer<Event<T>>{
    override fun onChanged(event:Event<T>?){
        event?.getContextIfNotHandled()?.let{
            onEventUnhandledContext(it)
        }
    }

}