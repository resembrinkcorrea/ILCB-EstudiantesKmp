package pe.lecordonbleu.institutoestudiante.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ResponseLoginUser(
    val data_menu: List<DataMenu> = emptyList(),
    val flag_val: Int,
    val data_usuario: List<DataUsuario> = emptyList(),
    val jsonGeneral: JsonGeneral = JsonGeneral()
)

@Serializable
data class JsonGeneral(
    val data2: List<Data2> = emptyList()
)

@Serializable
data class Data2(
    val check_valida: Int = -1,
    val flag_debe_cambiar_clave: Int = -1,
)

@Serializable
data class DataMenu(
    val icono: String,
    val ver: Int,
    val flag_etiqueta: Int,
    val flag: Int,
    val btn_dscto_adm: Int,
    val ruta: String,
    val idMenu: Int,
    val imagen: String,
    val texto_etiqueta: String? = null,
    val descargar: Int? = null,
    val crear: Int,
    val id_sistema: Int,
    val textoMenuAbrev: String,
    val eliminar: Int,
    val contador: Int,
    val textoMenu: String,
    val editar: Int,
    val orden: Int,
    val nivel: Int,
    val idMenuPadre: Int,
    val color_etiqueta: String? = null
)

@Serializable
data class DataUsuario(
    val num_docu_iden_pd: String,
    val idUNEG: Int,
    val personaNombre: String,
    val tiempo_inactividad: Int,
    val flag_dashboard: Int,
    val idUsuario: Int,
    val est_url_foto: String,
    val genero_abrev: String,
    val id_docente: Int,
    val id_atribp: Int,
    val usua_usuario: String,
    val pers_apellido_mat: String,
    val pers_apellido_pat: String,
    val flag_inactividad: Int,
    val flag_modif_contraseña: Int,
    val flag_consulta_producto: Int,
    val contador: Int,
    val idPerfil: Int,
    val genero: String,
    val flag_presup: Int,
    val flag_minedu: Int,
    val id_uneg: Int,
    val flag_boton_info: Int,
    val id_vendedor: Int,
    val idSistema: Int,
    val id_perfil: Int,
    val personaPaterno: String,
    val id_pers_det: Int,
    val id_entrevistador: Int,
    val pers_nombre: String,
    val flag_actFlujo: Int,
    val id_sistema: Int,
    val perf_nombre: String,
    val id_tipo_usuario: Int,
    val nombreUNEG: String,
    val personaMaterno: String
)
