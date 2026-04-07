package com.paradox543.malankaraorthodoxliturgica.domain.locations.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationsModel(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val `data`: List<Data> = emptyList(),
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: String
) {
    @Serializable
    data class Data(
        @SerialName("address")
        val address: String,
        @SerialName("id")
        val id: Int,
        @SerialName("latitude")
        val latitude: String,
        @SerialName("longitude")
        val longitude: String,
        @SerialName("mapLink")
        val mapLink: String,
        @SerialName("name")
        val name: String,
        @SerialName("phoneNumber")
        val phoneNumber: String
    )
}