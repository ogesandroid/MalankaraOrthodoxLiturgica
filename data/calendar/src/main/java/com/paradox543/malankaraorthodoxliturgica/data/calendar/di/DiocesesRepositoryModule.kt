package com.paradox543.malankaraorthodoxliturgica.data.calendar.di

import com.paradox543.malankaraorthodoxliturgica.data.calendar.repository.DiocesesRepositoryIml
import com.paradox543.malankaraorthodoxliturgica.domain.dioceses.repository.DiocesesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DiocesesRepositoryModule {
    @Binds
    abstract fun bindDiocesesRepository(impl: DiocesesRepositoryIml): DiocesesRepository
}