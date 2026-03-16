package pe.lecordonbleu.institutoestudiante.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UsuarioCorreoRequest(
    val usuario: String,
    val id_uneg: Int,
    val tipo_conexion: String,
    val ip_conexion: String
)
