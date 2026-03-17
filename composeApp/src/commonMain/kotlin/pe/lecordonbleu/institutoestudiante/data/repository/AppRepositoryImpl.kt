package pe.lecordonbleu.institutoestudiante.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import pe.lecordonbleu.institutoestudiante.data.remote.dto.ResponseDataMenu
import pe.lecordonbleu.institutoestudiante.data.remote.dto.ResponseEstadoAulaDemoDocente
import pe.lecordonbleu.institutoestudiante.data.remote.dto.ResponseHabilitarAulaDemo
import pe.lecordonbleu.institutoestudiante.data.remote.dto.ResponseHora
import pe.lecordonbleu.institutoestudiante.domain.model.EstadoAulaDemoRequest
import pe.lecordonbleu.institutoestudiante.domain.model.HabilitarAulaDemoRequest
import pe.lecordonbleu.institutoestudiante.domain.model.UserMenuRequest
import pe.lecordonbleu.institutoestudiante.core.config.Constantes
import pe.lecordonbleu.institutoestudiante.domain.repository.AppRepository

class AppRepositoryImpl(private val httpClient: HttpClient) : AppRepository {

    override suspend fun getMenuDataUser(userMenuRequest: UserMenuRequest): List<ResponseDataMenu> {
        val response = httpClient.post("${Constantes.BASE_ROOT_INTRANET}${Constantes.URL_BASE_GENERAL}login/menu") {
            contentType(ContentType.Application.Json)
            setBody(userMenuRequest)
        }
        return try {
            val responseBody = response.body<String>()
            val networkResponse = Json.decodeFromString<ResponseDataMenu>(responseBody)
            listOf(networkResponse)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getEstadoAulaDemoDocente(request: EstadoAulaDemoRequest): ResponseEstadoAulaDemoDocente {
        val response = httpClient.post("${Constantes.BASE_ROOT_INTRANET}${Constantes.URL_BASE_INTRANET}estadoAulaDemoDocente") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        return try {
            val responseBody = response.body<String>()
            val json = Json { ignoreUnknownKeys = true }
            json.decodeFromString<ResponseEstadoAulaDemoDocente>(responseBody)
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseEstadoAulaDemoDocente(flag_val = 0, ListEstadoAulaDemoDocente = emptyList())
        }
    }

    override suspend fun getHabilitarAulaDemo(request: HabilitarAulaDemoRequest): ResponseHabilitarAulaDemo {
        val response = httpClient.post("${Constantes.BASE_ROOT_INTRANET}${Constantes.URL_BASE_INTRANET}habilitarAulaDemo") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }
        return try {
            val responseBody = response.body<String>()
            val json = Json { ignoreUnknownKeys = true }
            json.decodeFromString<ResponseHabilitarAulaDemo>(responseBody)
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseHabilitarAulaDemo(flag_val = 0, ListClaseHabilitada = emptyList())
        }
    }

    override suspend fun getHoraServidor(): ResponseHora {
        val response = httpClient.post("${Constantes.BASE_ROOT_INTRANET}${Constantes.URL_BASE_INTRANET}horaServidor") {
            contentType(ContentType.Application.Json)
        }
        return try {
            val responseBody = response.body<String>()
            val json = Json { ignoreUnknownKeys = true }
            json.decodeFromString<ResponseHora>(responseBody)
        } catch (e: Exception) {
            e.printStackTrace()
            ResponseHora(flag_val = 0, listHoraServer = emptyList())
        }
    }
}
