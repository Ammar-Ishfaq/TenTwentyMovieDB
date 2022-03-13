package com.muhammad_ammar.tentwenty.models.genere

import kotlinx.serialization.Serializable

@Serializable
data class GenereResponse(
    val genres: List<Genre>
) : java.io.Serializable