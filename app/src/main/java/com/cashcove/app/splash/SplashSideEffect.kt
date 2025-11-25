package com.cashcove.app.splash

sealed interface SplashSideEffect {
    data object NavigateToAuthentication : SplashSideEffect
    data object NavigateToMain : SplashSideEffect
}
