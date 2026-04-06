package com.paradox543.malankaraorthodoxliturgica.domain.counsellingRequests.repository

import com.paradox543.malankaraorthodoxliturgica.domain.counsellingRequests.model.CounsellingRequestsModel

interface CounsellingRepository {

    suspend fun getCounsellingData(): Result<CounsellingRequestsModel>
}