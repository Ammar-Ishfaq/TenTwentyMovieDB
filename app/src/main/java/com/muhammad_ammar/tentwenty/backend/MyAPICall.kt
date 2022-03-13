package com.muhammad_ammar.tentwenty.backend

import com.muhammad_ammar.tentwenty.models.upcomingModelResponse.UpcomingMoviesModelResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MyAPICall {

    @GET("upcoming")
    suspend fun getUpcomingMovieList(@Query("api_key") apiKey: String): UpcomingMoviesModelResponse

    @GET("{ID}")
    suspend fun getMovieDetail(
        @Path("ID") ID: Int,
        @Query("api_key") apiKey: String
    ): UpcomingMoviesModelResponse

}