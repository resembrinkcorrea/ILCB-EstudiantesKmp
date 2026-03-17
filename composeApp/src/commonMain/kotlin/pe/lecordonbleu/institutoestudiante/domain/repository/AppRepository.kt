package pe.lecordonbleu.institutoestudiante.domain.repository

import pe.lecordonbleu.institutoestudiante.data.remote.dto.ResponseDataMenu
import pe.lecordonbleu.institutoestudiante.data.remote.dto.ResponseEstadoAulaDemoDocente
import pe.lecordonbleu.institutoestudiante.data.remote.dto.ResponseHabilitarAulaDemo
import pe.lecordonbleu.institutoestudiante.data.remote.dto.ResponseHora
import pe.lecordonbleu.institutoestudiante.domain.model.EstadoAulaDemoRequest
import pe.lecordonbleu.institutoestudiante.domain.model.HabilitarAulaDemoRequest
import pe.lecordonbleu.institutoestudiante.domain.model.UserMenuRequest

interface AppRepository {
    suspend fun getMenuDataUser(userRequest: UserMenuRequest): List<ResponseDataMenu>
    suspend fun getEstadoAulaDemoDocente(request: EstadoAulaDemoRequest): ResponseEstadoAulaDemoDocente
    suspend fun getHabilitarAulaDemo(request: HabilitarAulaDemoRequest): ResponseHabilitarAulaDemo
    suspend fun getHoraServidor(): ResponseHora
}
