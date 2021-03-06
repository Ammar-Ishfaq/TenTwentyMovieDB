package com.muhammad_ammar.tentwenty.api

import com.muhammad_ammar.tentwenty.backend.MyAPICall
import com.muhammad_ammar.tentwenty.backend.MyCustomApp
import com.muhammad_ammar.tentwenty.models.youtube_data.YoutubeVideoData
import com.muhammad_ammar.tentwenty.util.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SharedWebService(
    private val apiServices: MyAPICall,
    private val app: MyCustomApp
) {

    /**
     * Newly implementation of the new architecture ie here
     */
    private val dispatcher = Dispatchers.IO

    suspend fun getUpcomingMovieList() = withContext(dispatcher) {
        safeApiCall {
            Result.success(apiServices.getUpcomingMovieList(app.getApiKey()))
        }
    }

    suspend fun getGenere() = withContext(dispatcher) {
        safeApiCall {
            Result.success(apiServices.getGenere(app.getApiKey()))
        }
    }

    suspend fun getMovieDetail(id: Int) = withContext(dispatcher) {
        safeApiCall {
            Result.success(apiServices.getMovieDetail(id, app.getApiKey()))
        }
    }

    suspend fun getYoutubeVideoData(body: YoutubeVideoData) = withContext(dispatcher) {
        safeApiCall {
            Result.success(
                apiServices.getYoutubeVideoData(
                    body,
                    "https://youtubei.googleapis.com/youtubei/v1/player?key="
                )
            )
        }
    }
}