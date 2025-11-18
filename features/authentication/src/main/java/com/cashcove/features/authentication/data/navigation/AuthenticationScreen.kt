package com.cashcove.features.authentication.data.navigation

sealed class AuthenticationScreen(val route: String) {
    data object OnBoarding : AuthenticationScreen("onbording")
    data object Login : AuthenticationScreen("login")
    data object Register : AuthenticationScreen("register")
}
