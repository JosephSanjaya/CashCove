package com.cashcove.app.splash

sealed interface SplashIntent {
    data object CheckAuthStatus : SplashIntent
}
