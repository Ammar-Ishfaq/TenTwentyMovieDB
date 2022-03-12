package com.muhammad_ammar.tentwenty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.muhammad_ammar.tentwenty.api.SharedWebService
import com.muhammad_ammar.tentwenty.extensions.wrapWithEvent
import com.muhammad_ammar.tentwenty.models.responseModels.ParticularUserResponse
import com.muhammad_ammar.tentwenty.util.AppUtils
import kotlinx.coroutines.launch

class ParticularUserViewModel constructor(
    private val sharedWebService: SharedWebService,
) : BaseViewModel() {

    companion object {
        private const val VALID_STATUS_CODE = 200
    }

    var mParticularUser: MutableLiveData<ParticularUserResponse> = MutableLiveData()


    /**
     * hit the api
     */
    fun getParticularUserById(id: Int) {
        viewModelScope.launch {

            _showHideProgressDialog.value = true.wrapWithEvent()

            sharedWebService.getParticularUserById(id).run {

                onSuccess {

                    _showHideProgressDialog.value = false.wrapWithEvent()

                    if (it.Status == VALID_STATUS_CODE) {

                        mParticularUser.value = it

                    } else if (it.Status == 500) {
                        showSnackbarMessage(AppUtils.SERVER_NOT_RESPONDING_MESSAGE)
                    } else {
                        mParticularUser.value = it
                    }
                }

                onFailure {
                    _showHideProgressDialog.value = false.wrapWithEvent()
//                    it.message?.let { it1 -> showSnackbarMessage(it1) }
                    showSnackbarMessage(AppUtils.INTERNET_CONNECTION_ERROR_MESSAGE)
                }

            }
        }
    }

}