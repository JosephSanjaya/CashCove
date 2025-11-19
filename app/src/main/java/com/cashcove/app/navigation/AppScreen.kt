package com.cashcove.app.navigation

import com.cashcove.core.common.constants.RouteConstants

sealed class AppScreen(val route: String) {
    data object Splash : AppScreen(RouteConstants.App.SPLASH_SCREEN)
    data object Authentication : AppScreen(RouteConstants.Authentication.AUTHENTICATION_MODULE)
    data object Main : AppScreen(RouteConstants.Main.MAIN_MODULE)
}
