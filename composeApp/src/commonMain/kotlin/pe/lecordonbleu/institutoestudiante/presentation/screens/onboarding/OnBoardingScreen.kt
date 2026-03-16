package pe.lecordonbleu.institutoestudiante.presentation.screens.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.semantics.Role.Companion.Carousel
import androidx.navigation.NavController
import pe.lecordonbleu.institutoestudiante.getSettingsStorage
import pe.lecordonbleu.institutoestudiante.presentation.components.Carousel

@Composable
fun OnBoardingScreen(navigator: NavController) {
    val settingsStorage = getSettingsStorage()
    var carouselFinished by remember { mutableStateOf(false) }
    val session = settingsStorage.getInt("Session", -1)

    LaunchedEffect(session) {
        if (session != -1) {
            navigator.navigate("/login") {
                popUpTo(navigator.graph.startDestinationRoute ?: "/") { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    if (session == -1) {
        if (carouselFinished) {
            navigator.navigate("/login") {
                popUpTo(navigator.graph.startDestinationRoute ?: "/") { inclusive = true }
                launchSingleTop = true
            }
        } else {
            Carousel { carouselFinished = true }
        }
    }
}
