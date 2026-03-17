package pe.lecordonbleu.institutoestudiante.presentation.screens.login

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import org.koin.dsl.module
import org.koin.compose.KoinApplication
import pe.lecordonbleu.institutoestudiante.AndroidSettingsStorage
import pe.lecordonbleu.institutoestudiante.AppTheme
import pe.lecordonbleu.institutoestudiante.SettingsStorage
import pe.lecordonbleu.institutoestudiante.data.remote.dto.ResponseLoginUser
import pe.lecordonbleu.institutoestudiante.domain.model.UserLoginRequest
import pe.lecordonbleu.institutoestudiante.domain.model.UsuarioCorreoRequest
import pe.lecordonbleu.institutoestudiante.domain.repository.Repository

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    val context = LocalContext.current
    KoinApplication(application = {
        modules(module {
            single<Context> { context }
            single<SettingsStorage> { AndroidSettingsStorage(context) }
        })
    }) {
        AppTheme {
            val fakeRepo = object : Repository {
                override suspend fun getDataUsuario(userLoginRequest: UserLoginRequest): List<ResponseLoginUser> = emptyList()
                override suspend fun getDataUsuarioCorreo(userRequest: UsuarioCorreoRequest): List<ResponseLoginUser> = emptyList()
            }
            LoginScreen(viewModel = LoginViewModel(fakeRepo), navigator = rememberNavController())
        }
    }
}
