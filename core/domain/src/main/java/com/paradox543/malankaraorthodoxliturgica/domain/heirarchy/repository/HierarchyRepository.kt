package com.paradox543.malankaraorthodoxliturgica.domain.heirarchy.repository

import com.paradox543.malankaraorthodoxliturgica.domain.heirarchy.model.HierarchyListModel

interface HierarchyRepository {

    suspend fun getHierarchyList(): Result<HierarchyListModel>
}