package com.paradox543.malankaraorthodoxliturgica.domain.counsellingRequests.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CounsellingRequestsModel(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: Data,
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
        @SerialName("phoneNo")
        val phoneNo: String
    )
}