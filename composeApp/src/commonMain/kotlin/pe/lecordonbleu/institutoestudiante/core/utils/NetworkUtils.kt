package pe.lecordonbleu.institutoestudiante.core.utils

expect object NetworkUtils {
    suspend fun getPublicIPAddress(): String?
}
