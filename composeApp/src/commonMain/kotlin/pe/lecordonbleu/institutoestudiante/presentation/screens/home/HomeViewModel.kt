package pe.lecordonbleu.institutoestudiante.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pe.lecordonbleu.institutoestudiante.data.remote.dto.ResponseDataMenu
import pe.lecordonbleu.institutoestudiante.data.remote.dto.ResponseEstadoAulaDemoDocente
import pe.lecordonbleu.institutoestudiante.data.remote.dto.ResponseHabilitarAulaDemo
import pe.lecordonbleu.institutoestudiante.data.remote.dto.ResponseHora
import pe.lecordonbleu.institutoestudiante.domain.model.EstadoAulaDemoRequest
import pe.lecordonbleu.institutoestudiante.domain.model.HabilitarAulaDemoRequest
import pe.lecordonbleu.institutoestudiante.domain.model.UserMenuRequest
import pe.lecordonbleu.institutoestudiante.domain.repository.AppRepository
import pe.lecordonbleu.institutoestudiante.presentation.vo.ResourceUiState


class HomeViewModel(private val repo: AppRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<ResourceUiState<List<ResponseDataMenu>>>(ResourceUiState.Loading)
    private val _uiStateHora = MutableStateFlow<ResourceUiState<ResponseHora>>(ResourceUiState.Empty)
    private val _estadoAulaDemoState =
        MutableStateFlow<ResourceUiState<ResponseEstadoAulaDemoDocente>>(ResourceUiState.Empty)
    private val _habilitarAulaDemoState =
        MutableStateFlow<ResourceUiState<ResponseHabilitarAulaDemo>>(ResourceUiState.Empty)

    val uiState = _uiState.asStateFlow()
    val uiStateHora = _uiStateHora.asStateFlow()
    val habilitarAulaDemoState = _habilitarAulaDemoState.asStateFlow()
    val estadoAulaDemoState = _estadoAulaDemoState.asStateFlow()

    private lateinit var request: EstadoAulaDemoRequest
    private lateinit var userMenuRequest: UserMenuRequest
    private lateinit var habilitarAulaRequest: HabilitarAulaDemoRequest

    fun setUserMenuRequest(id_uneg: Int, id_sistema: Int, id_perfil: Int) {
        userMenuRequest =
            UserMenuRequest(id_uneg = id_uneg, id_sistema = id_sistema, id_perfil = id_perfil)
        getUserMenuData()
    }

    fun setRequestAulaDemo(id_docente: Int, id_uneg: Int) {
        request = EstadoAulaDemoRequest(
            id_docente = id_docente,
            id_uneg = id_uneg
        )
        getEstadoAulaDemo()
    }

    fun setRequestHabilitarAulaDemo(
        id_sistema: Int,
        id_usuario: Int,
        id_hor_asis: String
    ) {
        habilitarAulaRequest = HabilitarAulaDemoRequest(
            id_sistema = id_sistema,
            id_usuario = id_usuario,
            id_hor_asis = id_hor_asis
        )
        getHabilitarAulaDemo()
    }


    fun getUserMenuData() {
        viewModelScope.launch {
            try {
                val users = repo.getMenuDataUser(userMenuRequest)
                if (users.isEmpty()) {
                    _uiState.value = ResourceUiState.Error("Menu incorrecto")
                } else {
                    _uiState.value = ResourceUiState.Success(users)
                }
            } catch (e: Exception) {
                _uiState.value = ResourceUiState.Error(e.message ?: "Ocurrió un error")
            }
        }
    }

    private fun getEstadoAulaDemo() {
        viewModelScope.launch {
            _estadoAulaDemoState.value = ResourceUiState.Loading
            try {
                val response = repo.getEstadoAulaDemoDocente(request)
                if (response.ListEstadoAulaDemoDocente.isEmpty()) {
                    _estadoAulaDemoState.value = ResourceUiState.Error("No hay información de aula demo")
                } else {
                    _estadoAulaDemoState.value = ResourceUiState.Success(response)
                }
            } catch (e: Exception) {
                _estadoAulaDemoState.value = ResourceUiState.Error(e.message ?: "Error al cargar estado del aula demo")
            }
        }
    }

    private fun getHabilitarAulaDemo() {
        viewModelScope.launch {
            _habilitarAulaDemoState.value = ResourceUiState.Loading
            try {
                val response = repo.getHabilitarAulaDemo(habilitarAulaRequest)
                if (response.ListClaseHabilitada.isEmpty()) {
                    _habilitarAulaDemoState.value = ResourceUiState.Empty
                } else {
                    _habilitarAulaDemoState.value = ResourceUiState.Success(response)
                }
            } catch (e: Exception) {
                _habilitarAulaDemoState.value =
                    ResourceUiState.Error(e.message ?: "Error al habilitar aula demo")
            }
        }
    }

    fun getHoraServidor() {
        viewModelScope.launch {
            _uiStateHora.value = ResourceUiState.Loading
            try {
                val response = repo.getHoraServidor()
                if (response.listHoraServer.isEmpty()) {
                    _uiStateHora.value = ResourceUiState.Error("No se pudo obtener la hora del servidor")
                } else {
                    _uiStateHora.value = ResourceUiState.Success(response)
                }
            } catch (e: Exception) {
                _uiStateHora.value = ResourceUiState.Error(e.message ?: "Error al obtener hora del servidor")
            }
        }
    }


    fun resetEstadoAulaDemoState() {
        _estadoAulaDemoState.value = ResourceUiState.Empty
    }

    fun resetHabilitarAulaDemoState() {
        _habilitarAulaDemoState.value = ResourceUiState.Empty
    }

    fun resetHoraState() {
        _uiStateHora.value = ResourceUiState.Empty
    }
}
