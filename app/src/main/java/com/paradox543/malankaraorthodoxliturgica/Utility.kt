package com.paradox543.malankaraorthodoxliturgica

object Utility {

    fun getYoutubeThumbnail(url: String): String {
        val videoId = when {
            url.contains("v=") -> url.substringAfter("v=").substringBefore("&")
            url.contains("youtu.be/") -> url.substringAfter("youtu.be/").substringBefore("?")
            url.contains("embed/") -> url.substringAfter("embed/").substringBefore("?")
            else -> ""
        }
        return "https://img.youtube.com/vi/$videoId/hqdefault.jpg"
    }
}