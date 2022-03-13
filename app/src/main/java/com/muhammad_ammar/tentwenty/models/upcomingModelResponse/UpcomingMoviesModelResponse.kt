package com.muhammad_ammar.tentwenty.models.upcomingModelResponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpcomingMoviesModelResponse(
    @SerialName("dates")
    val dates: Dates,
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<Result>,
    @SerialName("total_pages")
    val total_pages: Int,
    @SerialName("total_results")
    val total_results: Int
) : java.io.Serializable