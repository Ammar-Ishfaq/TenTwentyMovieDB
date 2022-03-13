package com.muhammad_ammar.tentwenty.view.fragments.watch_movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.muhammad_ammar.tentwenty.api.SharedWebService
import com.muhammad_ammar.tentwenty.extensions.wrapWithEvent
import com.muhammad_ammar.tentwenty.models.upcomingModelResponse.UpcomingMoviesModelResponse
import com.muhammad_ammar.tentwenty.util.AppUtils
import com.muhammad_ammar.tentwenty.util.Event
import com.muhammad_ammar.tentwenty.view.fragments.base.BaseViewModel
import kotlinx.coroutines.launch

class WatchFragmentViewModel constructor(
    private val sharedWebService: SharedWebService,
) : BaseViewModel() {


    var upComingMovie: MutableLiveData<Event<UpcomingMoviesModelResponse>> = MutableLiveData()

    /**
     * hit the api
     */
    fun getAllUpcomingMovies() {
        viewModelScope.launch {

            _showHideProgressDialog.value = true.wrapWithEvent()

            sharedWebService.getUpcomingMovieList().run {

                onSuccess {
                    _showHideProgressDialog.value = false.wrapWithEvent()
                    upComingMovie.value = it.wrapWithEvent()

                }

                onFailure {
                    _showHideProgressDialog.value = false.wrapWithEvent()
                    showSnackbarMessage(AppUtils.INTERNET_CONNECTION_ERROR_MESSAGE)
                }

            }
        }
    }

}