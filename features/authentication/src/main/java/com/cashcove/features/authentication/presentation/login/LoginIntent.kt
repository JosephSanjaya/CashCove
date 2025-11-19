package com.cashcove.features.authentication.presentation.login

sealed interface LoginIntent {
    data object NavigateToRegister : LoginIntent
    data object NavigateToMain : LoginIntent
}