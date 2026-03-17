package pe.lecordonbleu.institutoestudiante

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import ilcbintranetkmp.composeapp.generated.resources.Res
import ilcbintranetkmp.composeapp.generated.resources.myriadproregular
import ilcbintranetkmp.composeapp.generated.resources.rajdhani_bold
import org.jetbrains.compose.resources.Font
import pe.lecordonbleu.institutoestudiante.core.theme.IlcbAzulFuerte
import pe.lecordonbleu.institutoestudiante.core.theme.IlcbAzulMarca
import pe.lecordonbleu.institutoestudiante.core.theme.IlcbAzulMedio
import pe.lecordonbleu.institutoestudiante.core.theme.IlcbBackground
import pe.lecordonbleu.institutoestudiante.core.theme.IlcbBlueAccent
import pe.lecordonbleu.institutoestudiante.core.theme.IlcbBlueDeep
import pe.lecordonbleu.institutoestudiante.core.theme.IlcbBlueDark
import pe.lecordonbleu.institutoestudiante.core.theme.IlcbBlueDarkDm
import pe.lecordonbleu.institutoestudiante.core.theme.IlcbBlueMid
import pe.lecordonbleu.institutoestudiante.core.theme.IlcbBlueMidDm
import pe.lecordonbleu.institutoestudiante.core.theme.IlcbError
import pe.lecordonbleu.institutoestudiante.core.theme.IlcbNaranja
import pe.lecordonbleu.institutoestudiante.core.theme.IlcbOnBrand
import pe.lecordonbleu.institutoestudiante.core.theme.IlcbOnSurface
import pe.lecordonbleu.institutoestudiante.core.theme.IlcbSurface
import pe.lecordonbleu.institutoestudiante.core.theme.IlcbTheme
import pe.lecordonbleu.institutoestudiante.core.theme.IlcbVioleta

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    IlcbTheme(content)
}

// --- API de compatibilidad — pantallas existentes siguen funcionando sin cambios ---
// Los valores vienen de IlcbColor.kt (una sola fuente de verdad)
@Composable
fun getColorsTheme(): DarkModeColors {
    val isDarkMode = isSystemInDarkTheme()
    return DarkModeColors(
        purple               = Color(0xFF6A66FF),
        colorExpenseItem     = if (isDarkMode) Color(0xFF090808) else IlcbSurface,
        backGroundColor      = if (isDarkMode) Color(0xFF1E1C1C) else IlcbBackground,
        textColor            = if (isDarkMode) Color.White else IlcbOnSurface,
        addIconColor         = if (isDarkMode) Color.White else IlcbOnSurface,
        colorArrowRound      = Color.Gray.copy(alpha = .2F),
        colorHeadList        = Color(0xFF00487E),
        colorTransParentSAA  = Color(0xFFFFF0),
        colorGreen800        = Color(0xFF3A6F3A),
        colorYellow800       = Color(0xFFEEAC2A),
        colorBrightSkyBlue   = Color(0xFF4493F8),
        primary              = if (isDarkMode) IlcbBlueDarkDm else IlcbBlueDark,
        secondary            = if (isDarkMode) Color(0xFF303F9F) else IlcbBlueAccent,
        accent               = Color(0xFF536DFE),
        gray                 = Color(0xFF7B6F72),
        white                = IlcbOnSurface,
        bg                   = Color(0xFFF7F8F8),
        darkGray             = Color(0xFF1E1E1E),
        colorVioletaIntenso  = IlcbVioleta,
        colorAzulMedio       = IlcbAzulMedio,
        colorNaranjaBrillante= IlcbNaranja,
        colorAzulFuerte      = IlcbAzulFuerte,
        colorRojoAlerta      = IlcbError,
        colorAzulMarcacionClaro = IlcbAzulMarca,
        colorVirtual         = Color(0xFF00BCD4),
        colorSincronica      = Color(0xFFFFA000),
        colorPresencial      = Color.Black,
        colorTeorico         = Color(0xFF8BC34A),
        colorPractico        = Color(0xFF3F51B5),
        colorClase01         = Color(0xFFE0F2F1),
        colorClase02         = Color(0xFFE3F2FD),
        colorClase03         = Color(0xFFD7CCC8),
        colorClase04         = Color(0xFFE8F5E9),
        colorClase05         = Color(0xFFEDE7F6),
        colorClase06         = Color(0xFFECEFF1),
        colorClase07         = Color(0xFFFCE4EC),
        colorClase08         = Color(0xFFF3E5F5),
        colorClase09         = Color(0xFFFFF3E0),
        colorClase10         = Color(0xFFEDE7F6),
        colorBlueDarkToLightGray = if (isDarkMode) Color.Black else IlcbBlueDeep,
        colorMixPrimary      = if (isDarkMode) IlcbBlueMidDm else IlcbBlueMid
    )
}

// --- Font helpers — se mantienen para compatibilidad con AppComponents ---
@Composable
fun radjhaniFonFamily() = FontFamily(
    Font(Res.font.rajdhani_bold, weight = FontWeight.Bold)
)

@Composable
fun leCordonBleuFont() = FontFamily(
    Font(Res.font.myriadproregular, FontWeight.Bold)
)

// --- Data class — sin cambios, compatibilidad total ---
data class DarkModeColors(
    val purple: Color,
    val colorExpenseItem: Color,
    val backGroundColor: Color,
    val textColor: Color,
    val addIconColor: Color,
    val colorArrowRound: Color,
    val colorHeadList: Color,
    val colorTransParentSAA: Color,
    val colorGreen800: Color,
    val colorYellow800: Color,
    val colorMixPrimary: Color,
    val colorBrightSkyBlue: Color,
    val primary: Color,
    val secondary: Color,
    val accent: Color,
    val gray: Color,
    val white: Color,
    val bg: Color,
    val darkGray: Color,
    val colorVioletaIntenso: Color,
    val colorAzulMedio: Color,
    val colorNaranjaBrillante: Color,
    val colorAzulFuerte: Color,
    val colorRojoAlerta: Color,
    val colorAzulMarcacionClaro: Color,
    val colorVirtual: Color,
    val colorSincronica: Color,
    val colorPresencial: Color,
    val colorTeorico: Color,
    val colorPractico: Color,
    val colorClase01: Color,
    val colorClase02: Color,
    val colorClase03: Color,
    val colorClase04: Color,
    val colorClase05: Color,
    val colorClase06: Color,
    val colorClase07: Color,
    val colorClase08: Color,
    val colorClase09: Color,
    val colorClase10: Color,
    val colorBlueDarkToLightGray: Color
)
