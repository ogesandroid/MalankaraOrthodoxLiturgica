package com.paradox543.malankaraorthodoxliturgica.domain.dioceses.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiocesesListModel(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: List<Data>,
    @SerialName("status")
    val status: String
) {
    @Serializable
    data class Data(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("description")
        val description: String,
        @SerialName("image")
        val image: String
    )
}
