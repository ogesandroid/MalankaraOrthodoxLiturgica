package com.paradox543.malankaraorthodoxliturgica.data.calendar.repository

import android.content.Context
import com.paradox543.malankaraorthodoxliturgica.data.calendar.remote.AppService
import com.paradox543.malankaraorthodoxliturgica.domain.heirarchy.model.HierarchyListModel
import com.paradox543.malankaraorthodoxliturgica.domain.heirarchy.repository.HierarchyRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HierarchyRepositoryIml @Inject constructor(
    @ApplicationContext private val context: Context
) : HierarchyRepository {
    override suspend fun getHierarchyList(): Result<HierarchyListModel> =
        runCatching { AppService.getHierarchyList() }
}