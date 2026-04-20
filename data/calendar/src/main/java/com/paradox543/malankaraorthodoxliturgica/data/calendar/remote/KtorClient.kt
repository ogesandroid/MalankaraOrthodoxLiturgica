package com.paradox543.malankaraorthodoxliturgica.data.calendar.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClient {
    val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true // don't crash if API sends extra fields
                isLenient = true // accepts malformed JSON
            })
        }
        install(Logging) {
            logger = Logger.ANDROID // prints to Android Logcat
            level = LogLevel.ALL // Changed from NONE to ALL to see logs

        }
        install(HttpTimeout) {
            requestTimeoutMillis = 60_000
            connectTimeoutMillis = 60_000
            socketTimeoutMillis = 60_000
        }
        defaultRequest {
            url(Const.BASE_URL)
            header("Content-Type", "application/json")
            header("Accept", "application/json")
        }
        expectSuccess = false
    }
}
