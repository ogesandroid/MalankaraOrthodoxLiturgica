package com.paradox543.malankaraorthodoxliturgica.data.calendar.di

import com.paradox543.malankaraorthodoxliturgica.data.calendar.repository.HierarchyRepositoryIml
import com.paradox543.malankaraorthodoxliturgica.domain.heirarchy.repository.HierarchyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class HierarchyRepositoryModule {

    @Binds
    abstract fun bindHierarchyRepository(iml: HierarchyRepositoryIml): HierarchyRepository
}