package com.muhammad_ammar.tentwenty.models.genere

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val id: Int,
    val name: String
) : java.io.Serializable