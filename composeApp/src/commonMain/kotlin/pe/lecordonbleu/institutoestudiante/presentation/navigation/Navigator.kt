package pe.lecordonbleu.institutoestudiante.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.ktor.client.HttpClient
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import pe.lecordonbleu.institutoestudiante.data.repository.RepoImpl
import pe.lecordonbleu.institutoestudiante.presentation.screens.home.HomeScreen
import pe.lecordonbleu.institutoestudiante.presentation.screens.home.HomeViewModel
import pe.lecordonbleu.institutoestudiante.presentation.screens.login.LoginMicrosoftViewModel
import pe.lecordonbleu.institutoestudiante.presentation.screens.login.LoginScreen
import pe.lecordonbleu.institutoestudiante.presentation.screens.login.LoginScreenMicrosoftView
import pe.lecordonbleu.institutoestudiante.presentation.screens.login.LoginViewModel
import pe.lecordonbleu.institutoestudiante.presentation.screens.onboarding.OnBoardingScreen

@Composable
fun Navigation(
    navController: NavController = rememberNavController()
) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = "/onboarding"
    ) {

        composable("/onboarding") {
            OnBoardingScreen(navController)
        }

        composable("/login") {
            val httpClient = koinInject<HttpClient>()
            val vm: LoginViewModel = viewModel { LoginViewModel(RepoImpl(httpClient)) }
            LoginScreen(vm, navController)
        }

        composable("/loginMicrosoftView") {
            val httpClient = koinInject<HttpClient>()
            val vm: LoginMicrosoftViewModel = viewModel { LoginMicrosoftViewModel(RepoImpl(httpClient)) }
            LoginScreenMicrosoftView(vm, navController)
        }

        composable(
            route = "/home/{id_sistema}/{id_perfil}",
            arguments = listOf(
                navArgument("id_sistema") { type = NavType.StringType },
                navArgument("id_perfil") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val idSistema = backStackEntry.arguments?.getString("id_sistema")?.toIntOrNull() ?: 0
            val idPerfil = backStackEntry.arguments?.getString("id_perfil")?.toIntOrNull() ?: 0
            val vm: HomeViewModel = koinViewModel()
            HomeScreen(viewmoModel = vm, navigator = navController, idSistema = idSistema, idPerfil = idPerfil)
        }

    }
}
