package com.paradox543.malankaraorthodoxliturgica.domain.home.repository

import com.paradox543.malankaraorthodoxliturgica.domain.home.model.HomeMenusModel
import com.paradox543.malankaraorthodoxliturgica.domain.settings.model.AppLanguage

interface HomeRepository {
    suspend fun getHomeMenuList(language: AppLanguage): Result<HomeMenusModel>
}