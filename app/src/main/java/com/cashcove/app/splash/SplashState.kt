package com.cashcove.app.splash

data class SplashState(
    val isLoading: Boolean = true,
    val isAuthenticated: Boolean = false,
    val isFirstLaunch: Boolean = false
)
