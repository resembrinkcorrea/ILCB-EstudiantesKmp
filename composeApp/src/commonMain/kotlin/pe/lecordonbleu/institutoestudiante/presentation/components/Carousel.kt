package pe.lecordonbleu.institutoestudiante.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import pe.lecordonbleu.institutoestudiante.presentation.vo.ImagesCarousel
import kotlin.collections.get

@Composable
fun Carousel(onFinished: () -> Unit) {
    var currentIndex by remember { mutableStateOf(0) } // Control del índice actual
    val images = ImagesCarousel.images // Lista de imágenes

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val painter = painterResource(images[currentIndex]) // Imagen actual
        AnimatedCarouselItem(
            painter = painter,
            modifier = Modifier.fillMaxSize()
        )

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            images.forEachIndexed { index, _ ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(12.dp)
                        .background(
                            color = if (index == currentIndex) Color.Gray else Color.DarkGray,
                            shape = CircleShape
                        )
                )
            }
        }

        // Control automático de transición
        LaunchedEffect(currentIndex) {
            delay(1000) // Tiempo de transición entre imágenes
            if (currentIndex < images.size - 1) {
                currentIndex++ // Cambia al siguiente índice
            } else {
                onFinished() // Llama al callback al finalizar el carrusel
            }
        }
    }
}

@Composable
fun AnimatedCarouselItem(
    painter: Painter,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .animateContentSize()
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }

}
