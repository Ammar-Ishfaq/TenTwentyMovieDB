package com.muhammad_ammar.tentwenty.view.fragments.moview_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.muhammad_ammar.tentwenty.api.SharedWebService
import com.muhammad_ammar.tentwenty.extensions.wrapWithEvent
import com.muhammad_ammar.tentwenty.models.movie_details.MovieDetailResponse
import com.muhammad_ammar.tentwenty.models.youtube_data.YoutubeVideoData
import com.muhammad_ammar.tentwenty.util.AppUtils
import com.muhammad_ammar.tentwenty.util.Event
import com.muhammad_ammar.tentwenty.view.fragments.base.BaseViewModel
import kotlinx.coroutines.launch

class MovieDetailFragmentViewModel constructor(
    private val sharedWebService: SharedWebService,
) : BaseViewModel() {


    var movieDetailResponse: MutableLiveData<Event<MovieDetailResponse>> = MutableLiveData()
    var videoUrl: MutableLiveData<Event<String>> = MutableLiveData()

    /**
     * hit the api
     */
    fun getMovieDetail(id: Int) {
        viewModelScope.launch {

            _showHideProgressDialog.value = true.wrapWithEvent()

            sharedWebService.getMovieDetail(id).run {

                onSuccess {
                    _showHideProgressDialog.value = false.wrapWithEvent()
                    movieDetailResponse.value = it.wrapWithEvent()

                }

                onFailure {
                    _showHideProgressDialog.value = false.wrapWithEvent()
                    showSnackbarMessage(AppUtils.INTERNET_CONNECTION_ERROR_MESSAGE)
                }

            }
        }
    }

    fun getMovieUrlAndPlay(body: YoutubeVideoData) {
        viewModelScope.launch {

            _showHideProgressDialog.value = true.wrapWithEvent()

            sharedWebService.getYoutubeVideoData(body).run {

                onSuccess {
                    _showHideProgressDialog.value = false.wrapWithEvent()
                    var i = 0
                    for (isMinTypeMP4 in it.streamingData.formats) {
                        if (isMinTypeMP4.mimeType.contains("video/mp4")) {
//                            videoUrl.value = it.streamingData?.formats[i].url
                            break
                        }
                        i++;
                    }

                }

                onFailure {
                    _showHideProgressDialog.value = false.wrapWithEvent()
                    showSnackbarMessage(AppUtils.INTERNET_CONNECTION_ERROR_MESSAGE)
                }

            }
        }
    }

}