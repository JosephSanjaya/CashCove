package com.cashcove.features.auth.login.domain.model

sealed class LoginUsecaseResult {
    data object Success : LoginUsecaseResult()
    data class Error(val errorMessage: String) : LoginUsecaseResult()
    data object Idle : LoginUsecaseResult()
    data object Loading : LoginUsecaseResult()
}