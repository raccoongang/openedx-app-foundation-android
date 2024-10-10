package org.openedx.foundation.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration

internal const val COMPACT_MAX_WIDTH = 600
internal const val MEDIUM_MAX_WIDTH = 840
internal const val COMPACT_MAX_HEIGHT = 480
internal const val MEDIUM_MAX_HEIGHT = 900

data class WindowSize(
    val width: WindowType,
    val height: WindowType
) {
    val isTablet: Boolean
        get() = height != WindowType.Compact && width != WindowType.Compact
}

fun <T> WindowSize.windowSizeValue(expanded: T, compact: T): T {
    return if (height != WindowType.Compact && width != WindowType.Compact) {
        expanded
    } else {
        compact
    }
}

enum class WindowType {
    Compact, Medium, Expanded
}

@Composable
fun rememberWindowSize(): WindowSize {
    val configuration = LocalConfiguration.current
    val screenWidth by remember(key1 = configuration) {
        mutableIntStateOf(configuration.screenWidthDp)
    }
    val screenHeight by remember(key1 = configuration) {
        mutableIntStateOf(configuration.screenHeightDp)
    }

    return WindowSize(
        width = getScreenWidth(screenWidth),
        height = getScreenHeight(screenHeight)
    )
}

fun getScreenWidth(width: Int): WindowType = when {
    width < COMPACT_MAX_WIDTH -> WindowType.Compact
    width < MEDIUM_MAX_WIDTH -> WindowType.Medium
    else -> WindowType.Expanded
}

fun getScreenHeight(height: Int): WindowType = when {
    height < COMPACT_MAX_HEIGHT -> WindowType.Compact
    height < MEDIUM_MAX_HEIGHT -> WindowType.Medium
    else -> WindowType.Expanded
}
