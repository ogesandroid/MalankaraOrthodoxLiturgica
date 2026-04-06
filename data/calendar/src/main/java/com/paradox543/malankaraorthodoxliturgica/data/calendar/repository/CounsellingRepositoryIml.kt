package com.paradox543.malankaraorthodoxliturgica.data.calendar.repository

import com.paradox543.malankaraorthodoxliturgica.data.calendar.remote.AppService
import com.paradox543.malankaraorthodoxliturgica.domain.counsellingRequests.model.CounsellingRequestsModel
import com.paradox543.malankaraorthodoxliturgica.domain.counsellingRequests.repository.CounsellingRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CounsellingRepositoryIml @Inject constructor(): CounsellingRepository{
    override suspend fun getCounsellingData(): Result<CounsellingRequestsModel> =
        runCatching { AppService.getCounsellingData() }
}