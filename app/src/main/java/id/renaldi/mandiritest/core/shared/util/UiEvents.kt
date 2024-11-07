package com.oyamo.dooka_app.core.util

sealed class UiEvents {
    data class SnackbarEvent(val message: String) : UiEvents()
    data class NavigateEvent(val route: String) : UiEvents()
}