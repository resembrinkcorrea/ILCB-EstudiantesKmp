package pe.lecordonbleu.institutoestudiante.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

private fun NavBackStackEntry.argInt(key: String): Int =
    arguments?.getInt(key) ?: 0

private fun NavBackStackEntry.argString(key: String): String =
    arguments?.getString(key) ?: ""

@Composable
fun Navigation(
    navController: NavController = rememberNavController()
) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = "/onboarding"
    ) {

        composable("/onboarding") {
            // OnBoardingScreen(navController)
        }

        composable("/login") {
            // LoginScreen(navController)
        }

        composable("/home") {
            // HomeScreen(navController)
        }

    }
}
