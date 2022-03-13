package com.muhammad_ammar.tentwenty.models.upcomingModelResponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dates(
    @SerialName("maximum")
    val maximum: String,
    @SerialName("minimum")
    val minimum: String
) : java.io.Serializable