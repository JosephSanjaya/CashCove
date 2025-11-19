package com.cashcove.features.authentication.presentation.login

import androidx.lifecycle.viewModelScope
import com.cashcove.core.common.model.UiState
import com.cashcove.core.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class LoginViewModel(
    initState: LoginState = LoginState()
) : BaseViewModel<LoginState, LoginIntent, LoginSideEffect>(initState) {
    override fun onIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.UpdatePhoneNumber -> {
                updateState { copy(phoneNumber = intent.phoneNumber) }
            }
            is LoginIntent.SendOtp -> {
                sendOtp(intent.phoneNumber)
            }
            is LoginIntent.NavigateToMain -> {
                postSideEffect(LoginSideEffect.NavigateToMain)
            }
            is LoginIntent.NavigateToRegister -> {
                postSideEffect(LoginSideEffect.NavigateToRegister)
            }
        }
    }

    private fun sendOtp(phoneNumber: String) {
        if (phoneNumber.isBlank()) {
            updateState { copy(loginUiState = UiState.Error("")) }
            return
        }

        updateState { copy(loginUiState = UiState.Loading) }

        viewModelScope.launch {
            try {
                // TODO: Implement actual OTP sending logic here
                // For now, this is a placeholder
                // Example: useCase.sendOtp(phoneNumber)
                updateState { copy(isLoading = false) }
                // Navigate to OTP verification screen or show success
                // For now, navigate to main as placeholder
                postSideEffect(LoginSideEffect.NavigateToMain)
            } catch (e: Exception) {
                updateState {
                    copy(
                        isLoading = false,
                        error = e.message ?: "Failed to send OTP. Please try again."
                    )
                }
            }
        }
    }
}
