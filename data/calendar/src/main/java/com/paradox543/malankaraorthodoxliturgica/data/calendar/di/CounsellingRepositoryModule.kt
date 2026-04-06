package com.paradox543.malankaraorthodoxliturgica.data.calendar.di

import com.paradox543.malankaraorthodoxliturgica.data.calendar.repository.CounsellingRepositoryIml
import com.paradox543.malankaraorthodoxliturgica.domain.counsellingRequests.repository.CounsellingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CounsellingRepositoryModule {

    @Binds
    abstract fun bindCounsellingRepository(iml: CounsellingRepositoryIml): CounsellingRepository
}