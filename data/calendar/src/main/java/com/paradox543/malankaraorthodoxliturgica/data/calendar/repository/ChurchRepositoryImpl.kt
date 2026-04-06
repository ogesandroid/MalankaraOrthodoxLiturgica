package com.paradox543.malankaraorthodoxliturgica.data.calendar.repository

import android.content.Context
import com.paradox543.malankaraorthodoxliturgica.data.calendar.remote.AppService
import com.paradox543.malankaraorthodoxliturgica.domain.church.model.ChurchListModel
import com.paradox543.malankaraorthodoxliturgica.domain.church.repository.ChurchRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChurchRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
): ChurchRepository {

    override suspend fun getChurchInfo(): Result<ChurchListModel> =
        runCatching { AppService.getChurchInfo() }
}