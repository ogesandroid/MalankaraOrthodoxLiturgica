package com.paradox543.malankaraorthodoxliturgica.domain.church.repository

import com.paradox543.malankaraorthodoxliturgica.domain.church.model.ChurchListModel

interface ChurchRepository {

    suspend fun getChurchInfo(): Result<ChurchListModel>
}