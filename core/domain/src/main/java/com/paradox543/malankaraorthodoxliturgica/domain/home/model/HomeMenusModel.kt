package com.paradox543.malankaraorthodoxliturgica.domain.home.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeMenusModel(
    @SerialName("bannerImages")
    val bannerImages: List<BannerImage>,
    @SerialName("bibleReadings")
    val bibleReadings: List<BibleReadings>,
    @SerialName("code")
    val code: Int,
    @SerialName("companyName")
    val companyName: String,
    @SerialName("logo")
    val logo: String,
    @SerialName("menu")
    val menu: List<Menu>,
    @SerialName("status")
    val status: String
) {
    @Serializable
    data class BannerImage(
        @SerialName("bannerId")
        val bannerId: Int,
        @SerialName("bannerImage")
        val bannerImage: String,
        @SerialName("bannerImageMime")
        val bannerImageMime: String,
        @SerialName("banner_type")
        val bannerType: Int
    )

    @Serializable
    data class BibleReadings(
        @SerialName("bookNumber")
        val bookNumber: Int,
        @SerialName("verseText")
        val verseText: String,
        @SerialName("ranges")
        val ranges: List<Ranges>
    ) {
        @Serializable
        data class Ranges(
            @SerialName("startChapter")
            val startChapter: Int,
            @SerialName("startVerse")
            val startVerse: Int,
            @SerialName("endChapter")
            val endChapter: Int,
            @SerialName("endVerse")
            val endVerse: Int
        )
    }

    @Serializable
    data class Menu(
        @SerialName("after_login")
        val afterLogin: String? = null,
        @SerialName("file_mime_type")
        val fileMimeType: String,
        @SerialName("file_name")
        val fileName: String,
        @SerialName("icon")
        val icon: String,
        @SerialName("id")
        val id: Int,
        @SerialName("menuCategory")
        val menuCategory: String,
        @SerialName("name")
        val name: String,
        @SerialName("typeName")
        val typeName: String
    )
}