package org.openedx.foundation.extension

import kotlin.math.log10
import kotlin.math.pow

private const val KIBI = 1024.0
private const val MIN_SIZE_REMINDER = 0.05
private const val MAX_SIZE_REMINDER = 0.95

fun Long.toFileSize(round: Int = 2, space: Boolean = true): String {
    return try {
        if (this <= 0) {
            "0MB"
        } else {
            val units = arrayOf("B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB")
            val digitGroups = (log10(this.toDouble()) / log10(KIBI)).toInt()
            val size = this / KIBI.pow(digitGroups.toDouble())
            val formatString =
                if (size % 1 < MIN_SIZE_REMINDER || size % 1 >= MAX_SIZE_REMINDER) "%.0f" else "%.${round}f"
            String.format(formatString, size) + if (space) " " else "" + units[digitGroups]
        }
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}
