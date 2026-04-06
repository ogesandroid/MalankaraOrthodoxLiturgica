package com.paradox543.malankaraorthodoxliturgica.domain.video.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VideoListingModel(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: List<Data>,
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: String
) {
    @Serializable
    data class Data(
        @SerialName("link")
        val link: String,
        @SerialName("title")
        val title: String,
        @SerialName("date")
        val date: String,
        @SerialName("videoId")
        val videoId: Int
    )
}