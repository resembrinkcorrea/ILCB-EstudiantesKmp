package pe.lecordonbleu.institutoestudiante.presentation.screens.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import ilcbintranetkmp.composeapp.generated.resources.Res
import ilcbintranetkmp.composeapp.generated.resources.contrasenha
import ilcbintranetkmp.composeapp.generated.resources.email_hint
import ilcbintranetkmp.composeapp.generated.resources.ingresar
import ilcbintranetkmp.composeapp.generated.resources.logo_365
import ilcbintranetkmp.composeapp.generated.resources.recordarme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import pe.lecordonbleu.institutoestudiante.SettingsStorage
import pe.lecordonbleu.institutoestudiante.getPlatformContext
import pe.lecordonbleu.institutoestudiante.getSettingsStorage
import pe.lecordonbleu.institutoestudiante.core.utils.NetworkUtils
import pe.lecordonbleu.institutoestudiante.LoadingIndicator
import pe.lecordonbleu.institutoestudiante.getColorsTheme
import pe.lecordonbleu.institutoestudiante.NotificationManagerPermission
import pe.lecordonbleu.institutoestudiante.getPlatform
import pe.lecordonbleu.institutoestudiante.showAlertDialog
import pe.lecordonbleu.institutoestudiante.core.extensions.LoginTextureOverlay
import pe.lecordonbleu.institutoestudiante.core.extensions.drawFoodAppBackground
import pe.lecordonbleu.institutoestudiante.presentation.components.CarruselLogos
import pe.lecordonbleu.institutoestudiante.data.remote.dto.ResponseLoginUser
import pe.lecordonbleu.institutoestudiante.domain.model.UtilsIcons
import pe.lecordonbleu.institutoestudiante.presentation.components.ButtonComponent
import pe.lecordonbleu.institutoestudiante.presentation.components.CheckboxComponent
import pe.lecordonbleu.institutoestudiante.presentation.components.MyTextFieldComponent
import pe.lecordonbleu.institutoestudiante.presentation.components.PasswordTextFieldComponent
import pe.lecordonbleu.institutoestudiante.presentation.vo.ResourceUiState

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navigator: NavController
) {

    val settingsStorage: SettingsStorage = getSettingsStorage()
    val platformContext = getPlatformContext()

    var usuarioState by remember { mutableStateOf(settingsStorage.getString("Email", "") ?: "") }
    var contrasenaState by remember { mutableStateOf(settingsStorage.getString("Contrasenha", "") ?: "") }

    var rememberMeState by remember { mutableStateOf(true) }
    var notificacionesActivas by remember { mutableStateOf(false) }
    val notificationManager = remember {
        NotificationManagerPermission(platformContext)
    }


    var showDialog by remember { mutableStateOf(false) }
    var showLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    var rstaLoginMicrosoft by remember { mutableStateOf(0) }
    var tipoLogin by remember { mutableStateOf(0) }
    var sistema = 11
    var flagLogueo by remember { mutableStateOf(0) }
    var idUneg by remember { mutableStateOf(2) }
    var tipoConexion by remember { mutableStateOf("APP ESTUDIANTE") }
    var ipConexion by remember { mutableStateOf("") }
    var lastPwd by remember { mutableStateOf("") }

    var mostrarFormularioInstitucional by remember { mutableStateOf(false) }

    val colors = getColorsTheme()
    val platform = getPlatform()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val backgroundRatio = 0.45f




    LaunchedEffect(Unit) {
        viewModel.resetUiState()
        ipConexion = NetworkUtils.getPublicIPAddress() ?: "10.0.0.0"
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    )
    {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawFoodAppBackground(
                    topColor = colors.colorBlueDarkToLightGray,
                    bottomColor = colors.backGroundColor,
                    blueHeightRatio = backgroundRatio
                )
        ) {
            LoginTextureOverlay(
                imageUrl = "https://mercadeo.blob.core.windows.net/applcb/login_background.png",
                tintColor = colors.backGroundColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1f - backgroundRatio)
                    .align(Alignment.BottomCenter)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(backgroundRatio),
                contentAlignment = Alignment.Center
            ) {
                CarruselLogos(modifier = Modifier.height(150.dp))
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
                    .padding(top = 140.dp), // empuja los controles hacia bajo el logo
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (!mostrarFormularioInstitucional) {

                    Surface(
                        shape = RoundedCornerShape(24.dp),
                        color = colors.colorExpenseItem,
                        border = BorderStroke(
                            width = 2.dp,
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFFC5A059),
                                    colors.colorExpenseItem,
                                    colors.colorMixPrimary
                                )
                            )
                        ),
                        shadowElevation = 20.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 32.dp, vertical = 40.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = "PORTAL INSTITUCIONAL",
                                color = Color(0xFF9B865C),
                                letterSpacing = 2.sp,
                                fontSize = 11.sp
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "BIENVENIDO",
                                color = colors.textColor,
                                fontSize = 28.sp,
                                fontWeight = FontWeight.ExtraBold
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "Seleccione su método de ingreso.",
                                color = colors.textColor.copy(alpha = 0.6f),
                                textAlign = TextAlign.Center,
                                fontSize = 14.sp
                            )

                            Spacer(modifier = Modifier.height(24.dp))

                            HorizontalDivider(
                                modifier = Modifier.fillMaxWidth(0.3f),
                                color = Color(0xFF9B865C).copy(alpha = 0.4f),
                                thickness = 2.dp
                            )

                            Spacer(modifier = Modifier.height(32.dp))

                            Button(
                                onClick = {
                                    showLoading = true
                                    navigator.navigate("/loginMicrosoftView")
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp),
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = colors.colorMixPrimary
                                )
                            ) {

                                Icon(
                                    painter = painterResource(Res.drawable.logo_365),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp),
                                    tint = Color.Unspecified
                                )

                                Spacer(modifier = Modifier.width(12.dp))

                                Text(
                                    "Ingresar con Office 365",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            OutlinedButton(
                                onClick = { mostrarFormularioInstitucional = true },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp),
                                shape = RoundedCornerShape(16.dp),
                                border = BorderStroke(
                                    1.dp,
                                    colors.textColor.copy(alpha = 0.15f)
                                ),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = colors.textColor
                                )
                            ) {

                                Text(
                                    "Ingreso con correo",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
                if (mostrarFormularioInstitucional) {

                    Surface(
                        shape = RoundedCornerShape(24.dp),
                        color = colors.colorExpenseItem,
                        border = BorderStroke(
                            width = 2.dp,
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFFC5A059),
                                    colors.colorExpenseItem,
                                    colors.colorMixPrimary
                                )
                            )
                        ),
                        shadowElevation = 20.dp,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 28.dp, vertical = 32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Text(
                                    text = "←",
                                    fontSize = 20.sp,
                                    color = colors.primary,
                                    modifier = Modifier
                                        .clickable { mostrarFormularioInstitucional = false }
                                )

                                Spacer(modifier = Modifier.width(10.dp))

                                Text(
                                    text = "LOGIN",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = colors.textColor
                                )
                            }

                            Spacer(modifier = Modifier.height(30.dp))

                            MyTextFieldComponent(
                                labelValue = stringResource(Res.string.email_hint),
                                painterResource = UtilsIcons.MESSAGE.icon,
                                onTextChanged = { usuarioState = it },
                                initialValue = usuarioState
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            PasswordTextFieldComponent(
                                labelValue = stringResource(Res.string.contrasenha),
                                painterResource = UtilsIcons.PASSWORD.icon,
                                onTextSelected = { contrasenaState = it },
                                initialValue = contrasenaState
                            )

                            Spacer(modifier = Modifier.height(5.dp))

                            CheckboxComponent(
                                value = stringResource(Res.string.recordarme),
                                onTextSelected = {},
                                onCheckedChange = { rememberMeState = it }
                            )

                            Spacer(modifier = Modifier.height(5.dp))

                            CheckboxComponent(
                                value = "Recibir notificaciones",
                                onTextSelected = {},
                                initialValue = false,
                                onCheckedChange = {
                                    notificacionesActivas = it
                                    notificationManager.requestPermission(it)
                                }
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            ButtonComponent(
                                value = stringResource(Res.string.ingresar),
                                onButtonClicked = {
                                    if (usuarioState.isNotEmpty()) {
                                        showLoading = true
                                        flagLogueo = 1

                                        viewModel.setUsuarioRequest(
                                            tipoLogin,
                                            rstaLoginMicrosoft,
                                            usuarioState,
                                            contrasenaState,
                                            sistema,
                                            idUneg,
                                            tipoConexion,
                                            ipConexion,
                                            lastPwd
                                        )
                                    }
                                },
                                textSize = 12,
                                contentColor = Color.White,
                                backgroundColor = colors.primary
                            )
                        }
                    }
                }

            }
        }
    }

    when (uiState) {

        is ResourceUiState.Success -> {
            showLoading = false

            val firstUser =
                (uiState as ResourceUiState.Success<List<ResponseLoginUser>>).data.firstOrNull()
                    ?.data_usuario?.firstOrNull()

            val idSistema = firstUser?.id_sistema ?: 0
            val idPerfil = firstUser?.idPerfil ?: 0
            val estUrlFoto = firstUser?.est_url_foto ?: ""
            val idPersDet = firstUser?.id_pers_det ?: 0
            val emailUser = firstUser?.usua_usuario ?: ""
            val idUsuario = firstUser?.idUsuario ?: 0
            val idDocente = firstUser?.id_docente ?: 0
            val estUrlPhoto = firstUser?.est_url_foto ?: ""

            settingsStorage.putInt("idSistema", idSistema)
            settingsStorage.putInt("idPerfil", idPerfil)
            settingsStorage.putString("estUrlFoto", estUrlFoto)
            settingsStorage.putInt("idPersDet", idPersDet)
            settingsStorage.putString("emailUser", emailUser)
            settingsStorage.putInt("flagLogueo", flagLogueo)
            settingsStorage.putInt("idUsuario", idUsuario)
            settingsStorage.putInt("idDocente", idDocente)
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
            showDialog = true
            errorMessage = (uiState as ResourceUiState.Error).message
        }

        else -> {}
    }

    if (showDialog) {
        showAlertDialog(
            title = "Error",
            message = errorMessage,
            positiveButtonText = "Aceptar",
            onPositiveAction = {
                viewModel.resetUiState()
                showDialog = false
            }
        )
    }
    if (showLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LoadingIndicator(modifier = Modifier.size(50.dp))
        }
    }
}
