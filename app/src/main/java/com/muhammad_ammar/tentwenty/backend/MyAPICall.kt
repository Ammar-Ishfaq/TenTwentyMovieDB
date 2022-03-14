package com.muhammad_ammar.tentwenty.backend

import com.muhammad_ammar.tentwenty.models.genere.GenereResponse
import com.muhammad_ammar.tentwenty.models.movie_details.MovieDetailResponse
import com.muhammad_ammar.tentwenty.models.upcomingModelResponse.UpcomingMoviesModelResponse
import com.muhammad_ammar.tentwenty.models.youtube_data.YoutubeVideoData
import com.muhammad_ammar.tentwenty.models.youtube_data.YoutubeVideoResponse
import com.muhammad_ammar.tentwenty.util.safeApiCall
import kotlinx.coroutines.withContext
import retrofit2.http.*

interface MyAPICall {

    @GET("movie/upcoming")
    suspend fun getUpcomingMovieList(@Query("api_key") apiKey: String): UpcomingMoviesModelResponse

    @GET("genre/movie/list")
    suspend fun getGenere(@Query("api_key") apiKey: String): GenereResponse

    @GET("movie/{ID}")
    suspend fun getMovieDetail(
        @Path("ID") ID: Int,
        @Query("api_key") apiKey: String
    ): MovieDetailResponse


    @POST
    suspend fun getYoutubeVideoData(
        @Body body: YoutubeVideoData,
        @Url url: String
    ): YoutubeVideoResponse

}