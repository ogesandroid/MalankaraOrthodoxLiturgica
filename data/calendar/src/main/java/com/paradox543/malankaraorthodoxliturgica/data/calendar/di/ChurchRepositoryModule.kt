package com.paradox543.malankaraorthodoxliturgica.data.calendar.di

import com.paradox543.malankaraorthodoxliturgica.data.calendar.repository.ChurchRepositoryImpl
import com.paradox543.malankaraorthodoxliturgica.domain.church.repository.ChurchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ChurchRepositoryModule {
    @Binds
    abstract fun bindChurchRepository(impl: ChurchRepositoryImpl): ChurchRepository
}