package com.paradox543.malankaraorthodoxliturgica.data.calendar.repository

import android.content.Context
import com.paradox543.malankaraorthodoxliturgica.data.calendar.remote.AppService
import com.paradox543.malankaraorthodoxliturgica.domain.locations.model.LocationsModel
import com.paradox543.malankaraorthodoxliturgica.domain.locations.repository.LocationsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationRepositoryIml @Inject constructor(
    @ApplicationContext private val context: Context
) : LocationsRepository {
    override suspend fun getLocationsList(searchKey: String): Result<LocationsModel> =
        runCatching { AppService.getLocationsList(searchKey) }
}