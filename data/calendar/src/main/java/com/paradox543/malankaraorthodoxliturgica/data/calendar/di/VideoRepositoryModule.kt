package com.paradox543.malankaraorthodoxliturgica.data.calendar.di

import com.paradox543.malankaraorthodoxliturgica.data.calendar.repository.VideoRepositoryIml
import com.paradox543.malankaraorthodoxliturgica.domain.video.repository.VideoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class VideoRepositoryModule {

    @Binds
    abstract fun bindVideoRepository(iml: VideoRepositoryIml): VideoRepository
}