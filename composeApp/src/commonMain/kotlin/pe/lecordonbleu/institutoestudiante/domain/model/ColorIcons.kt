package pe.lecordonbleu.institutoestudiante.domain.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import pe.lecordonbleu.institutoestudiante.getColorsTheme

object ColorIcons {

    @Composable
    fun colors(): List<Color> {
        val theme = getColorsTheme()
        return listOf(
            theme.colorVioletaIntenso,
            theme.colorAzulMedio,
            theme.colorNaranjaBrillante,
            theme.colorAzulFuerte,
            theme.colorRojoAlerta,
            theme.colorAzulMarcacionClaro
        )
    }
}
