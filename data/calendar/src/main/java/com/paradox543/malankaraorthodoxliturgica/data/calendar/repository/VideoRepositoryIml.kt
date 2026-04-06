package com.paradox543.malankaraorthodoxliturgica.data.calendar.repository

import android.content.Context
import com.paradox543.malankaraorthodoxliturgica.data.calendar.remote.AppService
import com.paradox543.malankaraorthodoxliturgica.domain.video.model.VideoListingModel
import com.paradox543.malankaraorthodoxliturgica.domain.video.repository.VideoRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VideoRepositoryIml @Inject constructor(
    @ApplicationContext private val context: Context
) : VideoRepository {
    override suspend fun getVideoList(): Result<VideoListingModel> =
        runCatching { AppService.getVideoListData() }
}