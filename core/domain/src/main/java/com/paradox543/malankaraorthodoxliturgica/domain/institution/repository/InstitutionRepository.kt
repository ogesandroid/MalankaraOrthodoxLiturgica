package com.paradox543.malankaraorthodoxliturgica.domain.institution.repository

import com.paradox543.malankaraorthodoxliturgica.domain.institution.model.InstitutionListModel

interface InstitutionRepository {
    suspend fun getInstitutionList(): Result<InstitutionListModel>
}