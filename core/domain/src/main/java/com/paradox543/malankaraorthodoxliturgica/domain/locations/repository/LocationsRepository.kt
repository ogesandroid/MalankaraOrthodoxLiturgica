package com.paradox543.malankaraorthodoxliturgica.domain.locations.repository

import com.paradox543.malankaraorthodoxliturgica.domain.locations.model.LocationsModel

interface LocationsRepository {

    suspend fun getLocationsList(searchKey: String): Result<LocationsModel>
}