package com.cashcove.app.navigation

import com.cashcove.core.common.constants.RouteConstants

sealed class AppScreen(val route: String) {
    data object Splash : AppScreen(RouteConstants.App.SPLASH_SCREEN)
    data object Login : AppScreen(RouteConstants.Authentication.LOGIN_SCREEN)
    data object Register : AppScreen(RouteConstants.Authentication.REGISTER_SCREEN)
    data object Otp : AppScreen(RouteConstants.Authentication.OTP_SCREEN)
    data object Onboarding : AppScreen(RouteConstants.Authentication.ONBOARDING_SCREEN)
    data object Main : AppScreen(RouteConstants.Main.MAIN_MODULE)
}
