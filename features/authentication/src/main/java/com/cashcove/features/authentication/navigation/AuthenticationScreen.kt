package com.cashcove.features.authentication.navigation

import com.cashcove.core.common.constants.RouteConstants

sealed class AuthenticationScreen(val route: String) {
    data object OnBoarding : AuthenticationScreen(RouteConstants.Authentication.ONBOARDING_SCREEN)
    data object Login : AuthenticationScreen(RouteConstants.Authentication.LOGIN_SCREEN)
    data object Register : AuthenticationScreen(RouteConstants.Authentication.REGISTER_SCREEN)
    data object Otp : AuthenticationScreen(RouteConstants.Authentication.OTP_SCREEN)
    data object Main : AuthenticationScreen(RouteConstants.Authentication.MAIN_SCREEN)
}
