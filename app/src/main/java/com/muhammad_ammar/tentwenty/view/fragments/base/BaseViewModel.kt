package com.muhammad_ammar.tentwenty.view.fragments.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muhammad_ammar.tentwenty.util.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

open class BaseViewModel : ViewModel() {
    companion object {
        internal const val VALID_STATUS_CODE = 200
    }

    val dispatcher = Dispatchers.Default

    var _showHideProgressDialog = MutableLiveData<Event<Boolean>>()
    val showHideProgressDialog: LiveData<Event<Boolean>> get() = _showHideProgressDialog

    val snackbarMessage = MutableLiveData<Event<String>>()
    protected fun showSnackbarMessage(message: String) {
        snackbarMessage.value = Event(message)
    }

    suspend fun customDelay(millis: Long) = withContext(dispatcher) {
        delay(millis)
    }

}