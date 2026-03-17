package pe.lecordonbleu.institutoestudiante

import androidx.compose.ui.window.ComposeUIViewController
import org.koin.core.context.startKoin
import pe.lecordonbleu.institutoestudiante.core.di.appModule

fun MainViewController() = ComposeUIViewController { App() }

fun doInitKoin() {
    startKoin {
        modules(appModule())
    }
}