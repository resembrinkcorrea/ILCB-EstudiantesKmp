package pe.lecordonbleu.institutoestudiante.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

private fun NavBackStackEntry.argInt(key: String): Int =
    arguments?.getInt(key) ?: 0

private fun NavBackStackEntry.argString(key: String): String =
    arguments?.getString(key) ?: ""
import org.koin.compose.viewmodel.koinViewModel
import pe.lecordonbleu.institutoestudiante.presentation.screens.login.LoginScreen
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
            val vm: LoginViewModel = koinViewModel()
            LoginScreen(vm, navController)
        }

        composable(
            route = "/home/{id_sistema}/{id_perfil}",
            arguments = listOf(
                navArgument("id_sistema") { type = NavType.IntType },
                navArgument("id_perfil") { type = NavType.IntType }
            )
        ) {
            // TODO: HomeScreen(navController)
        }

    }
}
