package com.paradox543.malankaraorthodoxliturgica.domain.video.repository

import com.paradox543.malankaraorthodoxliturgica.domain.video.model.VideoListingModel

interface VideoRepository {
    suspend fun getVideoList(): Result<VideoListingModel>
}