package com.paradox543.malankaraorthodoxliturgica.domain.home.repository

import com.paradox543.malankaraorthodoxliturgica.domain.home.model.HomeMenusModel

interface HomeRepository {
    suspend fun getHomeMenuList(): Result<HomeMenusModel>
}