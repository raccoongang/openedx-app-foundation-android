package org.openedx.foundation.extension

import androidx.fragment.app.Fragment
import androidx.window.layout.WindowMetricsCalculator
import org.openedx.foundation.presentation.COMPACT_MAX_HEIGHT
import org.openedx.foundation.presentation.COMPACT_MAX_WIDTH
import org.openedx.foundation.presentation.MEDIUM_MAX_HEIGHT
import org.openedx.foundation.presentation.MEDIUM_MAX_WIDTH
import org.openedx.foundation.presentation.WindowSize
import org.openedx.foundation.presentation.WindowType

fun Fragment.computeWindowSizeClasses(): WindowSize {
    val metrics = WindowMetricsCalculator.getOrCreate()
        .computeCurrentWindowMetrics(requireActivity())

    val widthDp = metrics.bounds.width() /
            resources.displayMetrics.density
    val widthWindowSize = when {
        widthDp < COMPACT_MAX_WIDTH -> WindowType.Compact
        widthDp < MEDIUM_MAX_WIDTH -> WindowType.Medium
        else -> WindowType.Expanded
    }

    val heightDp = metrics.bounds.height() /
            resources.displayMetrics.density
    val heightWindowSize = when {
        heightDp < COMPACT_MAX_HEIGHT -> WindowType.Compact
        heightDp < MEDIUM_MAX_HEIGHT -> WindowType.Medium
        else -> WindowType.Expanded
    }
    return WindowSize(widthWindowSize, heightWindowSize)
}
