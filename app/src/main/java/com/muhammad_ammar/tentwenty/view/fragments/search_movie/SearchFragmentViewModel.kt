package com.muhammad_ammar.tentwenty.view.fragments.search_movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.muhammad_ammar.tentwenty.api.SharedWebService
import com.muhammad_ammar.tentwenty.extensions.wrapWithEvent
import com.muhammad_ammar.tentwenty.models.genere.GenereResponse
import com.muhammad_ammar.tentwenty.models.genere.Genre
import com.muhammad_ammar.tentwenty.models.upcomingModelResponse.Result
import com.muhammad_ammar.tentwenty.util.AppUtils
import com.muhammad_ammar.tentwenty.util.Event
import com.muhammad_ammar.tentwenty.view.fragments.base.BaseViewModel
import kotlinx.coroutines.launch

class SearchFragmentViewModel constructor(
    private val sharedWebService: SharedWebService,
) : BaseViewModel() {


    var genereResponse: MutableLiveData<Event<GenereResponse>> = MutableLiveData()
    var genereSubResponse: MutableLiveData<Event<Result>> = MutableLiveData()

    fun getGenere() {
        viewModelScope.launch {
            _showHideProgressDialog.value = true.wrapWithEvent()
            sharedWebService.getGenere().run {
                onSuccess {
                    _showHideProgressDialog.value = false.wrapWithEvent()
                    genereResponse.value = it.wrapWithEvent()
                }
                onFailure {
                    _showHideProgressDialog.value = false.wrapWithEvent()
                    showSnackbarMessage(AppUtils.INTERNET_CONNECTION_ERROR_MESSAGE)
                }

            }
        }
    }

    fun getFilteredData(genreResponse: GenereResponse, result: List<Result>): List<Result> {
        val tempList = ArrayList<Result>()
        genreResponse.genres.forEach { it1 ->
            val genres = result.filter { it.genre_ids.contains(it1.id) }
            if (genres.isNotEmpty()) {
                genres[0].selectedForGenreName = it1.name
                genres[0].selectedForGenreRefId = it1.id
                tempList.add(genres[0])
            }
        }
        return tempList.distinctBy { it.selectedForGenreName }//tempList.distinctBy { it.selectedForGenereName }

    }

    fun getGenreMerged(genreResponse: List<Genre>, result: List<Result>): ArrayList<Result> {
        val tempList = ArrayList<Result>()
        genreResponse.forEach { it1 ->
            val genres = result.filter { it.genre_ids.contains(it1.id) }
            if (genres.isNotEmpty()) {
                genres[0].selectedForGenreName = it1.name
                genres[0].selectedForGenreRefId = it1.id
                tempList.add(genres[0])
            }
        }
        return tempList
    }

}