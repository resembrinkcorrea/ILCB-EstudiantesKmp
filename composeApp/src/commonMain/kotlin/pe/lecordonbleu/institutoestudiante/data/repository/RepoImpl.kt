package pe.lecordonbleu.institutoestudiante.data.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import pe.lecordonbleu.institutoestudiante.data.remote.dto.ResponseLoginUser
import pe.lecordonbleu.institutoestudiante.domain.model.UserLoginRequest
import pe.lecordonbleu.institutoestudiante.domain.model.UsuarioCorreoRequest
import pe.lecordonbleu.institutoestudiante.core.config.Constantes
import pe.lecordonbleu.institutoestudiante.domain.repository.Repository

class RepoImpl(private val httpClient: HttpClient) : Repository {

    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun getDataUsuario(userLoginRequest: UserLoginRequest): List<ResponseLoginUser> {
        return try {
            val response = httpClient.post("${Constantes.BASE_ROOT_INTRANET}${Constantes.URL_BASE_GENERAL}login/logueoGeneral") {
                contentType(ContentType.Application.Json)
                setBody(userLoginRequest)
            }
            val responseBody = response.body<String>()
            println("JSON recibido: $responseBody")
            listOf(json.decodeFromString<ResponseLoginUser>(responseBody))
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getDataUsuarioCorreo(userRequest: UsuarioCorreoRequest): List<ResponseLoginUser> {
        return try {
            val response = httpClient.post("${Constantes.BASE_ROOT_INTRANET}${Constantes.URL_BASE_GENERAL}logueoCorreoColaborador") {
                contentType(ContentType.Application.Json)
                setBody(userRequest)
            }
            val responseBody = response.body<String>()
            listOf(json.decodeFromString<ResponseLoginUser>(responseBody))
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
