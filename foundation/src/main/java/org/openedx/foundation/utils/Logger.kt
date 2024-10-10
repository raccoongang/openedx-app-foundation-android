package org.openedx.foundation.utils

import android.util.Log
import org.openedx.foundation.BuildConfig

class Logger(private val tag: String) {
    fun d(message: () -> String) {
        if (BuildConfig.DEBUG) Log.d(tag, message())
    }

    fun e(message: () -> String) {
        if (BuildConfig.DEBUG) Log.e(tag, message())
    }

    fun i(message: () -> String) {
        if (BuildConfig.DEBUG) Log.i(tag, message())
    }

    fun w(message: () -> String) {
        if (BuildConfig.DEBUG) Log.w(tag, message())
    }
}
