package org.openedx.foundation.interfaces

interface Analytics {
    fun logUserId(userId: Long)
    fun logEvent(eventName: String, params: Map<String, Any?>)
    fun logScreenEvent(screenName: String, params: Map<String, Any?>)
}
