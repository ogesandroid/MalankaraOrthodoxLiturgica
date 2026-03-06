package com.paradox543.malankaraorthodoxliturgica.data.calendar.repository

import com.paradox543.malankaraorthodoxliturgica.data.calendar.remote.AppService
import com.paradox543.malankaraorthodoxliturgica.domain.home.model.HomeMenusModel
import com.paradox543.malankaraorthodoxliturgica.domain.home.repository.HomeRepository
import com.paradox543.malankaraorthodoxliturgica.domain.settings.model.AppLanguage
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryIml @Inject constructor(): HomeRepository{
    override suspend fun getHomeMenuList(language: AppLanguage): Result<HomeMenusModel> =
        runCatching { AppService.getHomeMenuList(language) }
}