package pe.lecordonbleu.institutoestudiante

import androidx.compose.runtime.Composable

@Composable
expect fun showAlertDialog(
    title: String,
    message: String,
    positiveButtonText: String,
    onPositiveAction: () -> Unit,
    icon: (@Composable (() -> Unit))? = null
)
