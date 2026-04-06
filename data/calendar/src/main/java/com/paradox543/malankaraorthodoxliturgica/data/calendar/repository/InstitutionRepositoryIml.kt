package com.paradox543.malankaraorthodoxliturgica.data.calendar.repository

import android.content.Context
import com.paradox543.malankaraorthodoxliturgica.data.calendar.remote.AppService
import com.paradox543.malankaraorthodoxliturgica.domain.institution.model.InstitutionListModel
import com.paradox543.malankaraorthodoxliturgica.domain.institution.repository.InstitutionRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InstitutionRepositoryIml @Inject constructor(
    @ApplicationContext private val context: Context
) : InstitutionRepository {
    override suspend fun getInstitutionList(): Result<InstitutionListModel> =
        runCatching { AppService.getInstitutionInfo() }
}