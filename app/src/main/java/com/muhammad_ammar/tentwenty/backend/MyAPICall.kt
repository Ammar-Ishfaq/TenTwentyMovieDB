package com.muhammad_ammar.tentwenty.backend

import com.muhammad_ammar.tentwenty.models.upcomingModelResponse.UpcomingMoviesModelResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MyAPICall {

    @GET("upcoming")
    suspend fun getUpcomingMovieList(): UpcomingMoviesModelResponse

    @GET("{ID}")
    suspend fun getMovieDetail(@Path("ID") ID: Int): UpcomingMoviesModelResponse

}