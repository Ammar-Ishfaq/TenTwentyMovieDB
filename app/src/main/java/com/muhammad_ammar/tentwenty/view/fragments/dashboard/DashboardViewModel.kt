package com.muhammad_ammar.tentwenty.view.fragments.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.muhammad_ammar.tentwenty.api.SharedWebService
import com.muhammad_ammar.tentwenty.extensions.wrapWithEvent
import com.muhammad_ammar.tentwenty.models.responseModels.DatumListResponse
import com.muhammad_ammar.tentwenty.util.Event
import com.muhammad_ammar.tentwenty.util.AppUtils
import com.muhammad_ammar.tentwenty.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class DashboardViewModel constructor(
    private val sharedWebService: SharedWebService,
) : BaseViewModel() {


}