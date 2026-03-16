package pe.lecordonbleu.institutoestudiante.domain.repository

import pe.lecordonbleu.institutoestudiante.data.remote.dto.ResponseLoginUser
import pe.lecordonbleu.institutoestudiante.domain.model.UserLoginRequest
import pe.lecordonbleu.institutoestudiante.domain.model.UsuarioCorreoRequest

interface LoginRepository {
    suspend fun getDataUsuario(request: UserLoginRequest): List<ResponseLoginUser>
    suspend fun getDataUsuarioCorreo(request: UsuarioCorreoRequest): List<ResponseLoginUser>
}
