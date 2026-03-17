package pe.lecordonbleu.institutoestudiante.presentation.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DrawerValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dehaze
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.LockPerson
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import communicationapp.GetDeviceInformation
import ilcbintranetkmp.composeapp.generated.resources.Res
import ilcbintranetkmp.composeapp.generated.resources.app_name
import ilcbintranetkmp.composeapp.generated.resources.app_title
import ilcbintranetkmp.composeapp.generated.resources.cerrar_sesion
import ilcbintranetkmp.composeapp.generated.resources.imgdefault
import ilcbintranetkmp.composeapp.generated.resources.menu
import ilcbintranetkmp.composeapp.generated.resources.perfil_login
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import pe.lecordonbleu.institutoestudiante.MicrosoftLoginListener
import pe.lecordonbleu.institutoestudiante.SettingsStorage
import pe.lecordonbleu.institutoestudiante.data.remote.dto.DataMenu
import pe.lecordonbleu.institutoestudiante.data.remote.dto.ListClaseHabilitada
import pe.lecordonbleu.institutoestudiante.data.remote.dto.ListEstadoAulaDemoDocente
import pe.lecordonbleu.institutoestudiante.data.remote.dto.ResponseDataMenu
import pe.lecordonbleu.institutoestudiante.data.remote.dto.ResponseEstadoAulaDemoDocente
import pe.lecordonbleu.institutoestudiante.data.remote.dto.ResponseHabilitarAulaDemo
import pe.lecordonbleu.institutoestudiante.data.remote.dto.ResponseHora
import pe.lecordonbleu.institutoestudiante.domain.model.ColorIcons
import pe.lecordonbleu.institutoestudiante.getColorsTheme
import pe.lecordonbleu.institutoestudiante.getPlatformContext
import pe.lecordonbleu.institutoestudiante.getSettingsStorage
import pe.lecordonbleu.institutoestudiante.logout
import pe.lecordonbleu.institutoestudiante.openMicrosoftMFA
import pe.lecordonbleu.institutoestudiante.openMicrosoftPasswordChange
import pe.lecordonbleu.institutoestudiante.presentation.components.LoadingDialog
import pe.lecordonbleu.institutoestudiante.presentation.screens.home.customcell.AlertDialogAulaDemoError
import pe.lecordonbleu.institutoestudiante.presentation.screens.home.customcell.AsistenciaPanel
import pe.lecordonbleu.institutoestudiante.presentation.screens.home.customcell.AulaDemoDialog
import pe.lecordonbleu.institutoestudiante.presentation.screens.home.customcell.ClaseHabilitadaDialog
import pe.lecordonbleu.institutoestudiante.presentation.vo.ResourceUiState


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    viewmoModel: HomeViewModel,
    navigator: NavController,
    idSistema: Int,
    idPerfil: Int
) {
    val colors = getColorsTheme()
    val settingsStorage: SettingsStorage = getSettingsStorage()
    val platformContext = getPlatformContext()

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    var menus by remember { mutableStateOf<List<DataMenu>>(emptyList()) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoadingAulaDemo by remember { mutableStateOf(false) }

    val uiState by viewmoModel.uiState.collectAsStateWithLifecycle()
    val estadoAulaDemoState by viewmoModel.estadoAulaDemoState.collectAsStateWithLifecycle()
    val habilitarState by viewmoModel.habilitarAulaDemoState.collectAsStateWithLifecycle()
    val uiStateHora by viewmoModel.uiStateHora.collectAsStateWithLifecycle()
    var showAlertDialogDocente by remember { mutableStateOf(false) }
    var showAlertDialogDemoError by remember { mutableStateOf(false) }
    var showClaseHabilitadaDialog by remember { mutableStateOf(false) }
    var isHabilitarButtonEnabled by remember { mutableStateOf(true) }

    var showAsistenciaPanel by remember { mutableStateOf(false) }
    var horaServ by remember { mutableStateOf("") }
    var currentResponseDemo by remember { mutableStateOf<List<ListEstadoAulaDemoDocente>>(emptyList()) }
    var responseClaseHabilitada by remember { mutableStateOf<List<ListClaseHabilitada>>(emptyList()) }
    val idPersDet = settingsStorage.getInt("idPersDet", 0)
    val estUrlPhoto = settingsStorage.getString("estUrlFoto", "").orEmpty()
    val emailUser = settingsStorage.getString("emailUser", "").orEmpty()

    var currentClaseDemo by remember { mutableStateOf<ListEstadoAulaDemoDocente?>(null) }

    LaunchedEffect(idSistema, idPerfil) {
        viewmoModel.setUserMenuRequest(2, idSistema, idPerfil)
    }

    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(Res.string.app_title),
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = stringResource(Res.string.perfil_login) + "\n$emailUser",
                    style = TextStyle(fontSize = 16.sp),
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    ) {
        ModalBottomSheetLayout(
            sheetState = sheetState,
            sheetContent = {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(Res.string.menu),
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Normal),
                        modifier = Modifier.padding(start = 10.dp, top = 10.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                val flagLogueo = settingsStorage.getInt("flagLogueo", -1)
                                if (flagLogueo == 1 || flagLogueo == 0) {
                                    navigator.navigate("/login") {
                                        popUpTo("/homeScreen?id_sistema=$idSistema&id_perfil=$idPerfil") {
                                            inclusive = true
                                        }
                                        launchSingleTop = true
                                    }
                                } else if (flagLogueo == 3) {
                                    logout(platformContext, object : MicrosoftLoginListener {
                                        override suspend fun onSuccess(
                                            emailM: String,
                                            displayNameM: String,
                                            jobTitleM: String,
                                            officeLocationM: String,
                                            mobilePhoneM: String,
                                            photoBytesM: ByteArray?,
                                            lastPasswordChangeDateM: String
                                        ) {
                                            settingsStorage.clearAll()

                                            viewmoModel.resetEstadoAulaDemoState()
                                            viewmoModel.resetHabilitarAulaDemoState()
                                            viewmoModel.resetHoraState()
                                            navigator.navigate("/login") {
                                                popUpTo("/homeScreen?id_sistema=$idSistema&id_perfil=$idPerfil") {
                                                    inclusive = true
                                                }
                                                launchSingleTop = true
                                            }
                                        }

                                        override fun onError(errorMessage: String) {
                                            println("Error al cerrar sesión: $errorMessage")
                                        }
                                    })
                                }
                            }
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.LockPerson,
                            contentDescription = stringResource(Res.string.cerrar_sesion)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = stringResource(Res.string.cerrar_sesion))
                    }
                }
            }
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        elevation = 1.dp,
                        title = {
                            Text(
                                text = stringResource(Res.string.app_name),
                                fontSize = 20.sp,
                                color = colors.textColor
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    if (drawerState.isClosed) drawerState.open()
                                    else drawerState.close()
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Dehaze,
                                    tint = colors.textColor,
                                    contentDescription = stringResource(Res.string.menu)
                                )
                            }
                        },
                        actions = {
                            Icon(
                                imageVector = Icons.Default.ExpandMore,
                                tint = colors.textColor,
                                contentDescription = "More options",
                                modifier = Modifier
                                    .padding(end = 16.dp)
                                    .size(40.dp)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null
                                    ) {
                                        scope.launch { sheetState.show() }
                                    }
                            )
                        },
                        backgroundColor = colors.backGroundColor
                    )
                },
                backgroundColor = colors.backGroundColor
            ) { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(minSize = 80.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 300.dp)
                                .padding(horizontal = 8.dp)
                        ) {
                            items(menus.size) { index ->
                                val menu = menus[index]
                                MenuItemCard(menu, index, navigator, estUrlPhoto, idPersDet)
                            }
                        }

                        BoxWithConstraints(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Row(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .offset(x = -30.dp, y = -90.dp)
                                    .zIndex(2f),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AnimatedVisibility(
                                    visible = showAsistenciaPanel,
                                    enter = slideInHorizontally { it },
                                    exit = slideOutHorizontally { it }
                                ) {
                                    AsistenciaPanel(
                                        isVisible = showAsistenciaPanel,
                                        onToggle = { showAsistenciaPanel = !showAsistenciaPanel },
                                        data = responseClaseHabilitada
                                    )
                                }
                            }
                        }

                        androidx.compose.material.Button(
                            onClick = {
                                val idDocente = settingsStorage.getInt("idDocente", 0)
                                viewmoModel.setRequestAulaDemo(id_docente = idDocente, id_uneg = 2)
                                isHabilitarButtonEnabled = false
                            },
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(30.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = colors.secondary,
                                contentColor = MaterialTheme.colors.onPrimary
                            ),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text("Habilitar Asistencia", fontSize = 20.sp)
                        }
                    }
                }
            }
        }
    }


    when (uiState) {
        is ResourceUiState.Success -> {
            val responseData =
                (uiState as ResourceUiState.Success<List<ResponseDataMenu>>).data
            menus = responseData.flatMap { it.dataMenu }
            errorMessage = null
            isLoadingAulaDemo = false
        }
        is ResourceUiState.Error -> {
            menus = emptyList()
            errorMessage = (uiState as ResourceUiState.Error).message
            isLoadingAulaDemo = false
        }
        is ResourceUiState.Loading -> {
            isLoadingAulaDemo = true
        }
        ResourceUiState.Empty -> {
            menus = emptyList()
            errorMessage = null
            isLoadingAulaDemo = false
        }
    }

    when (estadoAulaDemoState) {
        is ResourceUiState.Success -> {
            isLoadingAulaDemo = false
            val data = (estadoAulaDemoState as ResourceUiState.Success<ResponseEstadoAulaDemoDocente>).data
            val clasesFlagDemo = data.ListEstadoAulaDemoDocente.filter { it.flag_demo == 1 }
            if (clasesFlagDemo.isNotEmpty()) {
                clasesFlagDemo.forEach { clase ->
                    val rangoHora = isHoraDentroRango(
                        horaActual = clase.fec_date_actual,
                        horaInicio = clase.hor_inicio,
                        horaFin = clase.hor_fin,
                        fecAutDemofin = clase.fec_aut_demo_fin
                    )
                    when (rangoHora) {
                        1, 3 -> {
                            currentResponseDemo = listOf(clase)
                            showAlertDialogDocente = true
                        }
                        2 -> {
                            currentClaseDemo = clase
                            showAlertDialogDemoError = true
                        }
                        else -> {
                            println("⛔ Clase fuera de rango o inválida.")
                        }
                    }
                }
            }
        }
        is ResourceUiState.Error -> {
            isLoadingAulaDemo = false
            val msg = (estadoAulaDemoState as ResourceUiState.Error).message
            println("❌ Error Aula Demo: $msg")
        }
        is ResourceUiState.Loading -> {
            isLoadingAulaDemo = true
        }
        ResourceUiState.Empty -> {
            println("⚠️ Estado vacío (Aula Demo)")
            isLoadingAulaDemo = false
        }
    }

    when (habilitarState) {
        is ResourceUiState.Loading -> {
            isLoadingAulaDemo = true
        }
        is ResourceUiState.Empty -> println("⚠️ Sin datos en habilitar aula demo")
        is ResourceUiState.Success -> {
            isLoadingAulaDemo = false
            val resp = (habilitarState as ResourceUiState.Success<ResponseHabilitarAulaDemo>).data
            if (resp.ListClaseHabilitada.isNotEmpty()) {
                responseClaseHabilitada = resp.ListClaseHabilitada
                showClaseHabilitadaDialog = true
            }
            println("✅ Habilitado: ${resp.ListClaseHabilitada}")
        }
        is ResourceUiState.Error -> {
            isLoadingAulaDemo = false
            println("❌ Error: ${(habilitarState as ResourceUiState.Error).message}")
        }
    }

    when (uiStateHora) {
        is ResourceUiState.Loading -> {}
        is ResourceUiState.Empty -> {}
        is ResourceUiState.Success -> {
            val response = (uiStateHora as ResourceUiState.Success<ResponseHora>).data
            val datee = response.listHoraServer.firstOrNull()?.datee?.toString().orEmpty()
            val horaServidor = datee.split('T')[1]
            val idDocente = settingsStorage.getInt("idDocente", 0)
            viewmoModel.setRequestAulaDemo(id_docente = idDocente, id_uneg = 2)
            isHabilitarButtonEnabled = false
            horaServ = horaServidor
        }
        is ResourceUiState.Error -> {
            val error = (uiStateHora as ResourceUiState.Error).message
            Text("Error: $error", color = Color.Red)
        }
    }

    if (isLoadingAulaDemo) {
        LoadingDialog()
    }

    if (showAlertDialogDocente) {
        AulaDemoDialog(
            data = currentResponseDemo,
            onDismiss = {
                showAlertDialogDocente = false
                viewmoModel.resetEstadoAulaDemoState()
            },
            onHabilitarMarcacion = { clase ->
                println("👉 Habilitar marcación para ${clase.asign_det_nombre}")
                val idUsuario = settingsStorage.getInt("idUsuario", 0)
                val idHorAsis = clase.id_hor_asis
                showAlertDialogDocente = false
                viewmoModel.setRequestHabilitarAulaDemo(
                    id_sistema = idSistema,
                    id_usuario = idUsuario,
                    id_hor_asis = idHorAsis
                )
                viewmoModel.resetEstadoAulaDemoState()
            }
        )
    }

    if (showAlertDialogDemoError && currentClaseDemo != null) {
        AlertDialogAulaDemoError(
            clase = currentClaseDemo!!,
            onDismiss = {
                showAlertDialogDemoError = false
                currentClaseDemo = null
                viewmoModel.resetEstadoAulaDemoState()
            }
        )
    }

    if (showClaseHabilitadaDialog) {
        ClaseHabilitadaDialog(
            data = responseClaseHabilitada,
            onDismiss = {
                showClaseHabilitadaDialog = false
                viewmoModel.resetHabilitarAulaDemoState()
                showAsistenciaPanel = true
            }
        )
    }

    errorMessage?.let { msg ->
        Text(
            text = "❌ Error: $msg",
            color = colors.textColor
        )
    }
}

@Composable
fun MenuItemCard(
    menu: DataMenu,
    index: Int,
    navigator: NavController,
    photoUrl: String,
    idPersDet: Int
) {
    val colors = ColorIcons.colors()
    val fondo = colors[index % colors.size]
    val platformContext = getPlatformContext()
    val responseInfo = GetDeviceInformation().getDeviceInfo

    Column(
        modifier = Modifier
            .padding(8.dp)
            .width(70.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                when (menu.textoMenuAbrev) {
                    "QR" -> navigator.navigate("/qrScreen/$idPersDet?photoUrl=$photoUrl")
                    "VER MARCACION" -> navigator.navigate("/verMarcacionView")
                    "MARC. ASISTENCIA" -> navigator.navigate("/guardarMarcacionView")
                    "ASIST. ESTUDIANTE" -> navigator.navigate("/asistenciaEstudianteView")
                    "HORARIO" -> navigator.navigate("/horarioDocenteView")
                    "ARCH. COMPARTIDOS" -> navigator.navigate("/archivosCompartidos")
                    "CAMBIO DE CONTRASEÑA" -> openMicrosoftPasswordChange(platformContext)
                    "MFA" -> openMicrosoftMFA(platformContext)
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(fondo)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = menu.imagen,
                contentDescription = menu.textoMenu,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                error = painterResource(Res.drawable.imgdefault)
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Box(
            modifier = Modifier.height(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = menu.textoMenu,
                fontSize = 8.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                textAlign = TextAlign.Center,
                lineHeight = 10.sp,
                modifier = Modifier.padding(horizontal = 3.dp)
            )
        }
    }
}

fun isHoraDentroRango(
    horaActual: String,
    horaInicio: String,
    horaFin: String,
    fecAutDemofin: String
): Int {
    return try {
        val fecAutDemoHorfin = fecAutDemofin.split(" ")
        val fecAutDemoFin = fecAutDemoHorfin[1].split(":")

        val horAutHor = fecAutDemoFin[0].toInt() * 3600
        val minAutHor = fecAutDemoFin[1].toInt() * 60
        val segAutHor = fecAutDemoFin[2].toDouble().toInt()

        val fecDateActual = horaActual.split(" ")
        val horActual = fecDateActual[1].split(":")

        val horHor = horActual[0].toInt() * 3600
        val minHor = horActual[1].toInt() * 60
        val segHor = horActual[2].toDouble().toInt()

        val totalHorActual = horHor + minHor + segHor

        val horaIni = horaInicio.split(":")
        val horIni = horaIni[0].toInt() * 3600
        val minIni = horaIni[1].toInt() * 60
        val segIni = 0

        val totalHorInicio = horIni + minIni + segIni

        val horaFinArr = horaFin.split(":")
        val horFin = horaFinArr[0].toInt() * 3600
        val minFin = horaFinArr[1].toInt() * 60
        val segFin = 0

        val totalHorFin = horFin + minFin + segFin

        if (fecAutDemofin == "1900-01-01 00:00:00.0") {
            return if (totalHorActual in totalHorInicio..totalHorFin) 1 else 0
        } else {
            val totalHorDemoFin = horAutHor + minAutHor + segAutHor

            return if (
                totalHorActual in totalHorInicio..totalHorFin &&
                totalHorActual <= totalHorDemoFin
            ) {
                2
            } else if (totalHorActual in totalHorInicio..totalHorFin) {
                3
            } else {
                0
            }
        }

    } catch (e: Exception) {
        0
    }
}
