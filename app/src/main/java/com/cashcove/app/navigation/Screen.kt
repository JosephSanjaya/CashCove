package com.cashcove.app.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Authentication : Screen("authentication")
    data object Main : Screen("main")
    data object Onboarding : Screen("onboarding")
}
