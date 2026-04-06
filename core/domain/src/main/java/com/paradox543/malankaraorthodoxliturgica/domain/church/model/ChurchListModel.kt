package com.paradox543.malankaraorthodoxliturgica.domain.church.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChurchListModel(
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
        @SerialName("description")
        val description: String,
        @SerialName("id")
        val id: Int,
        @SerialName("image")
        val image: String,
        @SerialName("name")
        val name: String
    )
}