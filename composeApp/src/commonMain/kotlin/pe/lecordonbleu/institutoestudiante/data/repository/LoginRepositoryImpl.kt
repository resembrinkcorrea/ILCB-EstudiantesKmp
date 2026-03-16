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
import pe.lecordonbleu.institutoestudiante.domain.repository.LoginRepository

const val BASE_URL = "http://sslcbpopen.eastus2.cloudapp.azure.com:8080/saa-rest/webresources"
const val ID_UNEG = 2

class LoginRepositoryImpl(private val httpClient: HttpClient) : LoginRepository {

    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun getDataUsuario(request: UserLoginRequest): List<ResponseLoginUser> {
        return try {
            val response = httpClient.post("$BASE_URL/login/logueoGeneral") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }
            val responseBody = response.body<String>()
            println("JSON recibido: $responseBody")
            listOf(json.decodeFromString<ResponseLoginUser>(responseBody))
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getDataUsuarioCorreo(request: UsuarioCorreoRequest): List<ResponseLoginUser> {
        return try {
            val response = httpClient.post("$BASE_URL/logueoCorreoColaborador") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }
            val responseBody = response.body<String>()
            listOf(json.decodeFromString<ResponseLoginUser>(responseBody))
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
