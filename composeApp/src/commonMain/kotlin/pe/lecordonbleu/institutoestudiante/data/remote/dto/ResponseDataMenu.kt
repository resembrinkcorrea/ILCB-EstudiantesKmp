package pe.lecordonbleu.institutoestudiante.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDataMenu(
    @SerialName("data_menu") val dataMenu: List<DataMenu>
)
