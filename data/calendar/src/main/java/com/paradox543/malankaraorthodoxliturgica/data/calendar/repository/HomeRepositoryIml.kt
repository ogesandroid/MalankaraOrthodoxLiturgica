package com.paradox543.malankaraorthodoxliturgica.data.calendar.repository

import com.paradox543.malankaraorthodoxliturgica.data.calendar.remote.AppService
import com.paradox543.malankaraorthodoxliturgica.domain.home.model.HomeMenusModel
import com.paradox543.malankaraorthodoxliturgica.domain.home.repository.HomeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryIml @Inject constructor(): HomeRepository{
    override suspend fun getHomeMenuList(): Result<HomeMenusModel> =
        runCatching { AppService.getHomeMenuList() }
}