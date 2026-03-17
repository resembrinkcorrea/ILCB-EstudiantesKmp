package pe.lecordonbleu.institutoestudiante.domain.repository

import pe.lecordonbleu.institutoestudiante.data.remote.dto.ResponseLoginUser
import pe.lecordonbleu.institutoestudiante.domain.model.UserLoginRequest
import pe.lecordonbleu.institutoestudiante.domain.model.UsuarioCorreoRequest

interface Repository {
    suspend fun getDataUsuario(userLoginRequest: UserLoginRequest): List<ResponseLoginUser>
    suspend fun getDataUsuarioCorreo(userRequest: UsuarioCorreoRequest): List<ResponseLoginUser>
}
