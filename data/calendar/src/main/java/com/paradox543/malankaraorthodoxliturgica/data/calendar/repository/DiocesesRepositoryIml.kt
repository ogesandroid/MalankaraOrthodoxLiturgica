package com.paradox543.malankaraorthodoxliturgica.data.calendar.repository

import android.content.Context
import com.paradox543.malankaraorthodoxliturgica.data.calendar.remote.AppService
import com.paradox543.malankaraorthodoxliturgica.domain.dioceses.model.DiocesesListModel
import com.paradox543.malankaraorthodoxliturgica.domain.dioceses.repository.DiocesesRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DiocesesRepositoryIml @Inject constructor(
    @ApplicationContext private val context: Context
): DiocesesRepository{

    override suspend fun getDiocesesList(): Result<DiocesesListModel> =
        runCatching { AppService.getDiocesesList() }
}