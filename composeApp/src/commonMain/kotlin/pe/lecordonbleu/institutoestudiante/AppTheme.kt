package pe.lecordonbleu.institutoestudiante

import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ilcbintranetkmp.composeapp.generated.resources.Res
import ilcbintranetkmp.composeapp.generated.resources.myriadproregular
import ilcbintranetkmp.composeapp.generated.resources.rajdhani_bold
import org.jetbrains.compose.resources.Font

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = MaterialTheme.colors.copy(primary = Color.Black),
        shapes = MaterialTheme.shapes.copy(
            small = AbsoluteCutCornerShape(0.dp),
            medium = AbsoluteCutCornerShape(0.dp),
            large = AbsoluteCutCornerShape(0.dp),
        )
    ) {
        content()
    }
}

@Composable
fun getColorsTheme(): DarkModeColors {
    val isDarkMode = false

    val Purple = Color(0xFF6A66FF)
    val ColorExpenseItem = if (isDarkMode) Color(0xFF090808) else Color(0xFFF1F1F1)
    val BackGroundColor = if (isDarkMode) Color(0xFF1E1C1C) else Color.White
    val TextColor = if (isDarkMode) Color.White else Color.Black
    val AddIconColor = if (isDarkMode) Purple else Color.Black
    val ColorArrowRound = if (isDarkMode) Purple else Color.Gray.copy(alpha = .2F)
    val ColorHeadList = if (isDarkMode) Color(0XFF00487e) else Color(0XFF00487e)
    val ColorTransParentSAA = if (isDarkMode) Color(0XFFfff0) else Color(0XFFfff0)
    val ColorGreen800 = if (isDarkMode) Color(0xFF3a6f3a) else Color(0xFF3a6f3a)
    val ColorYellow800 = if (isDarkMode) Color(0xFFeeac2a) else Color(0xFFeeac2a)
    val ColorBrightSkyBlue = if (isDarkMode) Color(0XFF4493f8) else Color(0XFF4493f8)
    val Primary = if (isDarkMode) Color(0xFF1A237E) else Color(0xFF182957)
    val Secondary = if (isDarkMode) Color(0xFF303F9F) else Color(0xFF3F51B5)
    val AccentColor = Color(0xFF536DFE)
    val GrayColor = if (isDarkMode) Color(0xFFB0BEC5) else Color(0xFF7B6F72)
    val WhiteColor = if (isDarkMode) Color.White else Color.Black
    val BgColor = if (isDarkMode) Color(0xFF121212) else Color(0xFFF7F8F8)
    val DarkGrayBackground = if (isDarkMode) Color.White else Color(0xFF1E1E1E)
    val ColorBlueDarkToLightGray = if (isDarkMode) Color.Black else Color(0xFF00205C)
    val ColorMixPrimary = if (isDarkMode) Color(0xFF7D9AD1) else Color(0xFF2A4A8E)

    val colorVioletaIntenso = if (isDarkMode) Color(0xFF4E0D70) else Color(0xFF7719aa)
    val colorAzulMedio = if (isDarkMode) Color(0xFF3B44A1) else Color(0xFF5d61c8)
    val colorNaranjaBrillante = if (isDarkMode) Color(0xFFa85e04) else Color(0xFFf08705)
    val colorAzulFuerte = if (isDarkMode) Color(0xFF004ca8) else Color(0xFF0063dd)
    val colorRojoAlerta = if (isDarkMode) Color(0xFFa41c1b) else Color(0xFFd82625)
    val colorAzulMarcacionClaro = if (isDarkMode) Color(0xFF2677C7) else Color(0xFF3894F9)

    val colorVirtual = if (isDarkMode) Color(0xFF00BCD4) else Color(0xFF00BCD4)
    val colorSincronica = if (isDarkMode) Color(0xFFFFA000) else Color(0xFFFFA000)
    val colorPresencial = if (isDarkMode) Color.Black else Color.Black
    val colorTeorico = if (isDarkMode) Color(0xFF8BC34A) else Color(0xFF8BC34A)
    val colorPractico = if (isDarkMode) Color(0xFF3F51B5) else Color(0xFF3F51B5)

    val colorClase01 = if (isDarkMode) Color(0xFFB2DFDB) else Color(0xFFE0F2F1)
    val colorClase02 = if (isDarkMode) Color(0xFF90CAF9) else Color(0xFFE3F2FD)
    val colorClase03 = if (isDarkMode) Color(0xFFBCAAA4) else Color(0xFFD7CCC8)
    val colorClase04 = if (isDarkMode) Color(0xFFA5D6A7) else Color(0xFFE8F5E9)
    val colorClase05 = if (isDarkMode) Color(0xFF9FA8DA) else Color(0xFFEDE7F6)
    val colorClase06 = if (isDarkMode) Color(0xFFB0BEC5) else Color(0xFFECEFF1)
    val colorClase07 = if (isDarkMode) Color(0xFFF48FB1) else Color(0xFFFCE4EC)
    val colorClase08 = if (isDarkMode) Color(0xFFCE93D8) else Color(0xFFF3E5F5)
    val colorClase09 = if (isDarkMode) Color(0xFFFFCC80) else Color(0xFFFFF3E0)
    val colorClase10 = if (isDarkMode) Color(0xFFB39DDB) else Color(0xFFEDE7F6)

    return DarkModeColors(
        purple = Purple,
        colorExpenseItem = ColorExpenseItem,
        backGroundColor = BackGroundColor,
        textColor = TextColor,
        addIconColor = AddIconColor,
        colorArrowRound = ColorArrowRound,
        colorHeadList = ColorHeadList,
        colorTransParentSAA = ColorTransParentSAA,
        colorGreen800 = ColorGreen800,
        colorYellow800 = ColorYellow800,
        colorBrightSkyBlue = ColorBrightSkyBlue,
        primary = Primary,
        secondary = Secondary,
        accent = AccentColor,
        gray = GrayColor,
        white = WhiteColor,
        bg = BgColor,
        darkGray = DarkGrayBackground,
        colorVioletaIntenso = colorVioletaIntenso,
        colorAzulMedio = colorAzulMedio,
        colorNaranjaBrillante = colorNaranjaBrillante,
        colorAzulFuerte = colorAzulFuerte,
        colorRojoAlerta = colorRojoAlerta,
        colorAzulMarcacionClaro = colorAzulMarcacionClaro,
        colorVirtual = colorVirtual,
        colorSincronica = colorSincronica,
        colorPresencial = colorPresencial,
        colorTeorico = colorTeorico,
        colorPractico = colorPractico,
        colorClase01 = colorClase01,
        colorClase02 = colorClase02,
        colorClase03 = colorClase03,
        colorClase04 = colorClase04,
        colorClase05 = colorClase05,
        colorClase06 = colorClase06,
        colorClase07 = colorClase07,
        colorClase08 = colorClase08,
        colorClase09 = colorClase09,
        colorClase10 = colorClase10,
        colorBlueDarkToLightGray = ColorBlueDarkToLightGray,
        colorMixPrimary = ColorMixPrimary
    )
}

@Composable
fun radjhaniFonFamily() = FontFamily(
    Font(Res.font.rajdhani_bold, weight = FontWeight.Bold)
)

@Composable
fun leCordonBleuFont() = FontFamily(
    Font(Res.font.myriadproregular, FontWeight.Bold)
)

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
