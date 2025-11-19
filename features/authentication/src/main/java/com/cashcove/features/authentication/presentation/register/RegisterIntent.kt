package com.cashcove.features.authentication.presentation.register

sealed interface RegisterIntent {
    data object NavigateToLogin : RegisterIntent
    data object NavigateToMain : RegisterIntent
}
