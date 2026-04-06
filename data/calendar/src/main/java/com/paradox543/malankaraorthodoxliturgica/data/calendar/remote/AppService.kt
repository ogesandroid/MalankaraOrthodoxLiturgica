package com.paradox543.malankaraorthodoxliturgica.data.calendar.remote

import com.paradox543.malankaraorthodoxliturgica.domain.church.model.ChurchListModel
import com.paradox543.malankaraorthodoxliturgica.domain.dioceses.model.DiocesesListModel
import com.paradox543.malankaraorthodoxliturgica.domain.home.model.HomeMenusModel
import com.paradox543.malankaraorthodoxliturgica.domain.settings.model.AppLanguage
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

object AppService {

    private val client = KtorClient.httpClient
    suspend fun getHomeMenuList(language: AppLanguage, appVersion: String?): HomeMenusModel =
        client.post("settings") { // Api call endpoint with post method.
            setBody(buildJsonObject { // request body as json.
                put("lang", language.code)
                put("deviceType", Const.DEVICE_TYPE)
                put("device_apk_version", "1.1")
            })
        }.body()


    suspend fun getDiocesesList(): DiocesesListModel =
        client.post("dioceses-info") {
        }.body()

    suspend fun getChurchInfo(): ChurchListModel =
        client.post("church-info") {
        }.body()
}