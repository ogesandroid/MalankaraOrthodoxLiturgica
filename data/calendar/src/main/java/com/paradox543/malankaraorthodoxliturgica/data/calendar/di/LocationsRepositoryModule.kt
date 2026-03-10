package com.paradox543.malankaraorthodoxliturgica.data.calendar.di

import com.paradox543.malankaraorthodoxliturgica.data.calendar.repository.LocationRepositoryIml
import com.paradox543.malankaraorthodoxliturgica.domain.locations.LocationsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationsRepositoryModule {

    @Binds
    abstract fun bindLocationRepository(impl: LocationRepositoryIml): LocationsRepository
}