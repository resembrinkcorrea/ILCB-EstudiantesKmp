package pe.lecordonbleu.institutoestudiante

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIActivityIndicatorView
import platform.UIKit.UIActivityIndicatorViewStyleLarge
import platform.UIKit.UIColor

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun LoadingIndicator(modifier: Modifier) {
    UIKitView(
        modifier = modifier,
        factory = {
            val indicator = UIActivityIndicatorView(UIActivityIndicatorViewStyleLarge)
            indicator.color = UIColor.grayColor
            indicator.startAnimating()
            indicator
        },
        update = { it.startAnimating() }
    )
}
