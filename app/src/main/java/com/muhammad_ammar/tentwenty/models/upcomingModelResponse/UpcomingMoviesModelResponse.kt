package com.muhammad_ammar.tentwenty.models.upcomingModelResponse

data class UpcomingMoviesModelResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)