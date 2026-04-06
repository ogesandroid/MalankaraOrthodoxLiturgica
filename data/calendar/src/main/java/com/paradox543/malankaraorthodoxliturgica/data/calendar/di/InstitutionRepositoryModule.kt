package com.paradox543.malankaraorthodoxliturgica.data.calendar.di

import com.paradox543.malankaraorthodoxliturgica.data.calendar.repository.InstitutionRepositoryIml
import com.paradox543.malankaraorthodoxliturgica.domain.institution.repository.InstitutionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class InstitutionRepositoryModule {
    @Binds
    abstract fun bindInstitutionRepository(impl: InstitutionRepositoryIml): InstitutionRepository
}