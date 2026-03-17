package pe.lecordonbleu.institutoestudiante.presentation.screens.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import pe.lecordonbleu.institutoestudiante.MicrosoftLogin
import pe.lecordonbleu.institutoestudiante.MicrosoftLoginListener
import pe.lecordonbleu.institutoestudiante.SettingsStorage
import pe.lecordonbleu.institutoestudiante.getColorsTheme
import pe.lecordonbleu.institutoestudiante.getPlatformContext
import pe.lecordonbleu.institutoestudiante.getSettingsStorage
import pe.lecordonbleu.institutoestudiante.logout
import pe.lecordonbleu.institutoestudiante.presentation.components.dialogs.CustomDialogBasic
import pe.lecordonbleu.institutoestudiante.presentation.components.LoadingDialog
import pe.lecordonbleu.institutoestudiante.presentation.vo.ResourceUiState

@Composable
fun LoginScreenMicrosoftView(viewModel: LoginMicrosoftViewModel, navigator: NavController) {

    var isLogged by remember { mutableStateOf(false) }
    var loading by remember { mutableStateOf(false) }

    val settingsStorage: SettingsStorage = getSettingsStorage()
    val primerLogueo = settingsStorage.getInt("primerlogueo", 0)


    var displayName by remember { mutableStateOf("") }
    var jobTitle by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var officeLocation by remember { mutableStateOf("") }
    var mobilePhone by remember { mutableStateOf("") }
    var lastPasswordChangeDate by remember { mutableStateOf("") }

    var photoBytes by remember { mutableStateOf<ByteArray?>(null) }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var showDialog by remember { mutableStateOf(false) }
    var showLoading by remember { mutableStateOf(false) }
    var rememberMeState by remember { mutableStateOf(true) }
    var usuarioState by remember { mutableStateOf(settingsStorage.getString("Email", "") ?: "") }
    var contrasenaState by remember {
        mutableStateOf(
            settingsStorage.getString("Contrasenha", "") ?: ""
        )
    }
    var errorMessage by remember { mutableStateOf("") }
    var rstaLoginMicrosoft by remember { mutableStateOf(1) }
    var tipoLogin by remember { mutableStateOf(1) }
    var sistema = 10
    var flagLogueo by remember { mutableStateOf(1) }
    var idUneg by remember { mutableStateOf(2) }
    var tipoConexion by remember { mutableStateOf("ANDROID Estudiante") }
    var ipConexion by remember { mutableStateOf("") }

    var loginChecked by remember { mutableStateOf(false) }

    var passwordScreenShown by remember { mutableStateOf(false) }

    var showDialogError by remember { mutableStateOf(false) }
    var errorTitle by remember { mutableStateOf("") }


    val platformContext = getPlatformContext()
    val colors = getColorsTheme()

    LaunchedEffect(primerLogueo) {

        if (primerLogueo == 1 && !loginChecked) {

            loginChecked = true
            loading = true

            MicrosoftLogin(
                platformContext,
                object : MicrosoftLoginListener {

                    override suspend fun onSuccess(
                        emailR: String,
                        displayNameM: String,
                        jobTitleM: String,
                        officeLocationM: String,
                        mobilePhoneM: String,
                        photoBytesM: ByteArray?,
                        lastPasswordChangeDateM: String
                    ) {

                        email = emailR
                        displayName = displayNameM
                        jobTitle = jobTitleM
                        officeLocation = officeLocationM
                        mobilePhone = mobilePhoneM
                        photoBytes = photoBytesM
                        lastPasswordChangeDate = lastPasswordChangeDateM
                        isLogged = true
                        loading = false
                    }

                    override fun onError(errorMessage: String) {
                        loading = false
                    }
                })
        }
    }

    Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {

        TopAppBar(
            elevation = 1.dp,
            title = {
                Text(
                    text = "Login Microsoft",
                    fontSize = 20.sp,
                    color = colors.textColor
                )
            },
            navigationIcon = {
                Icon(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            navigator.popBackStack()
                        },
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Atrás",
                    tint = colors.textColor
                )
            },
            backgroundColor = colors.backGroundColor
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {

            Spacer(modifier = Modifier.height(120.dp))

            Text("ILCB Sesión Microsoft")

            Spacer(modifier = Modifier.height(60.dp))

            if (loading) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(30.dp))
            }

            if (isLogged) {

                photoBytes?.let { bytes ->
                    AsyncImage(
                        model = bytes,
                        contentDescription = null,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(displayName)
                Text(jobTitle)
                Text(email)
                Text("Oficina: $officeLocation")
                Text("Teléfono: $mobilePhone")

                Spacer(modifier = Modifier.height(40.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colors.primary,
                            contentColor = colors.white
                        ),
                        onClick = {
                            loading = true
                            logout(platformContext, object : MicrosoftLoginListener {
                                override suspend fun onSuccess(
                                    email: String,
                                    displayNameM: String,
                                    jobTitleM: String,
                                    officeLocationM: String,
                                    mobilePhoneM: String,
                                    photoBytesM: ByteArray?,
                                    lastPasswordChangeDateM: String
                                ) {
                                    loading = false
                                    isLogged = false
                                    photoBytes = null
                                    settingsStorage.putInt("primerlogueo", 0)
                                }

                                override fun onError(errorMessage: String) {
                                    loading = false
                                }
                            })
                        }) {
                        Text("Cerrar Sesión", color = Color.White)
                    }

                    Spacer(modifier = Modifier.width(20.dp))

                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colors.primary,
                            contentColor = colors.white
                        ),
                        onClick = {
                            if (email.isNotEmpty()) {
                                showLoading = true
                                flagLogueo = 3
                                viewModel.setUsuarioRequest(
                                    tipoLogin,
                                    rstaLoginMicrosoft,
                                    email,
                                    "",
                                    sistema,
                                    idUneg,
                                    tipoConexion,
                                    ipConexion,
                                    lastPasswordChangeDate
                                )
                            }
                        }) {
                        Text("Ingresar", color = Color.White)
                    }
                }

            } else {


                Button(
                    colors = ButtonDefaults.buttonColors
                        (
                        containerColor = colors.primary,
                        contentColor = colors.white
                    ), onClick = {
                        loading = true

                        MicrosoftLogin(platformContext, object : MicrosoftLoginListener {

                            override suspend fun onSuccess(
                                emailR: String,
                                displayNameM: String,
                                jobTitleM: String,
                                officeLocationM: String,
                                mobilePhoneM: String,
                                photoBytesM: ByteArray?,
                                lastPasswordChangeDateM: String
                            ) {

                                email = emailR
                                displayName = displayNameM
                                jobTitle = jobTitleM
                                officeLocation = officeLocationM
                                mobilePhone = mobilePhoneM
                                photoBytes = photoBytesM
                                lastPasswordChangeDate = lastPasswordChangeDateM
                                settingsStorage.putInt("primerlogueo", 1)
                                isLogged = true
                                loading = false
                            }

                            override fun onError(errorMesg: String) {
                                showLoading = false
                                errorTitle = "Error de Autenticación"
                                errorMessage = errorMesg
                                showDialogError = true
                            }
                        })
                    }) {
                    Text("Siguiente", color = Color.White)
                }
            }
        }
    }

    when (uiState) {
        is ResourceUiState.Success -> {
            showLoading = false
            val firstUser =
                (uiState as ResourceUiState.Success<List<pe.lecordonbleu.institutoestudiante.data.remote.dto.ResponseLoginUser>>).data.firstOrNull()?.data_usuario?.firstOrNull()

            val idSistema = firstUser?.id_sistema ?: 0
            val idPerfil = firstUser?.idPerfil ?: 0
            val estUrlFoto = firstUser?.est_url_foto ?: ""
            val idPersDet = firstUser?.id_pers_det ?: 0
            val emailUser = firstUser?.usua_usuario ?: ""
            val idUsuario = firstUser?.idUsuario ?: 0
            val idEstud = firstUser?.id_estud ?: 0
            val estUrlPhoto = firstUser?.est_url_foto ?: ""

            val response = (uiState as ResourceUiState.Success<List<pe.lecordonbleu.institutoestudiante.data.remote.dto.ResponseLoginUser>>)
                .data
                .firstOrNull()

            val data1 = response?.jsonGeneral?.data1?.firstOrNull()

            val checkValida: Int =
                data1?.check_valida ?: -1

            settingsStorage.putInt("idSistema", idSistema)
            settingsStorage.putInt("idPerfil", idPerfil)
            settingsStorage.putString("estUrlFoto", estUrlFoto)
            settingsStorage.putInt("idPersDet", idPersDet)
            settingsStorage.putString("emailUser", emailUser)
            settingsStorage.putInt("flagLogueo", flagLogueo)
            settingsStorage.putInt("idUsuario", idUsuario)
            settingsStorage.putInt("idEstud", idEstud)
            settingsStorage.putString("estUrlPhoto", estUrlPhoto)

            if (rememberMeState) {
                settingsStorage.putString("Email", emailUser)
                settingsStorage.putString("Contrasenha", contrasenaState)
                settingsStorage.putInt("Session", 1)
            } else {
                settingsStorage.removeKey("Email")
                settingsStorage.removeKey("Contrasenha")
                settingsStorage.putInt("Session", -1)
            }
            navigator.navigate("/home/$idSistema/$idPerfil") {
                popUpTo("/login") { inclusive = true }
                launchSingleTop = true
            }
        }

        is ResourceUiState.Error -> {
            showLoading = false
            errorTitle = "Error de autenticación"
            errorMessage = (uiState as ResourceUiState.Error).message
            showDialogError = true
        }

        else -> {
            println("Estado Loading o vacío: $uiState")
            showDialog = false
        }
    }
    if (showLoading) {
        LoadingDialog()
    }
    if (showDialogError) {
        CustomDialogBasic(
            visible = true,
            titulo = errorTitle,
            mensaje = errorMessage,
            flag_val = 0,
            confirmado = false,
            aceptarSelected = 0,
            dismissOnOutsideClick = true,
            onDismiss = {
                showDialogError = false
                viewModel.resetUiState()
            }
        )
    }
}
