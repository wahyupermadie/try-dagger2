package com.wepe.trydagger.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wepe.trydagger.utils.ErrorHandler
import com.wepe.trydagger.utils.Event

abstract class BaseViewModel : ViewModel(){

    // FOR ERROR HANDLER
    private val _errorHandler = MutableLiveData<Event<ErrorHandler>>()
    val errorHandler: LiveData<Event<ErrorHandler>> get() = _errorHandler

}