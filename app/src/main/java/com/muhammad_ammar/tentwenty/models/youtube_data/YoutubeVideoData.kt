package com.muhammad_ammar.tentwenty.models.youtube_data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class YoutubeVideoData(
    val context: context,
    val videoId: String?
)

@Serializable
data class context(
    val client: client
)

@Serializable
data class client(
    val hl: String,
    val gl: String,
    val clientName: String,
    val clientVersion: String
)

@Serializable
data class formats(
    @SerialName("itag") val itag: Int,
    @SerialName("url") val url: String,
    @SerialName("mimeType") val mimeType: String
)

@Serializable
data class YoutubeVideoResponse(
    @SerialName("streamingData")
    val streamingData: streamingData
)

@Serializable
data class streamingData(
    @SerialName("formats") val formats: List<formats>
)
