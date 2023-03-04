package me.pgloaguen.data.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import me.pgloaguen.data.api.dto.DataDTO

class SmartHomeApi(
    private val baseUrl: String,
) {
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json()
        }
    }

    internal suspend fun fetchData(): DataDTO {
        return httpClient.get("${baseUrl}/data.json").body()
    }
}