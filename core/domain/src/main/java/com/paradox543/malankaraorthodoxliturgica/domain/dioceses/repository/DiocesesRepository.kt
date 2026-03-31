package com.paradox543.malankaraorthodoxliturgica.domain.dioceses.repository

import com.paradox543.malankaraorthodoxliturgica.domain.dioceses.model.DiocesesListModel

interface DiocesesRepository {
    suspend fun getDiocesesList(): Result<DiocesesListModel>
}