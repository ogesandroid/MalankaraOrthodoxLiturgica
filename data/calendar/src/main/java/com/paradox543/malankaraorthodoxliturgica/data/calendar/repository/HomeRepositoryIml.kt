package com.paradox543.malankaraorthodoxliturgica.data.calendar.repository

import android.content.Context
import com.paradox543.malankaraorthodoxliturgica.data.calendar.remote.AppService
import com.paradox543.malankaraorthodoxliturgica.domain.home.model.HomeMenusModel
import com.paradox543.malankaraorthodoxliturgica.domain.home.repository.HomeRepository
import com.paradox543.malankaraorthodoxliturgica.domain.settings.model.AppLanguage
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryIml @Inject constructor(
    @ApplicationContext private val context: Context
) : HomeRepository {
    private val versionName = context.packageManager
        .getPackageInfo(context.packageName, 0).versionName

    override suspend fun getHomeMenuList(language: AppLanguage): Result<HomeMenusModel> =
        runCatching { AppService.getHomeMenuList(language, versionName) }
}