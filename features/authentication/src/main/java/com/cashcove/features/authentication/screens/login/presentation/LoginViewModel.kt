package com.cashcove.features.authentication.screens.login.presentation

import androidx.lifecycle.viewModelScope
import com.cashcove.core.common.model.UiState
import com.cashcove.core.viewmodel.BaseViewModel
import com.cashcove.features.authentication.screens.login.domain.usecases.LoginUsecase
import kotlinx.coroutines.launch

class LoginViewModel(
    initialState: LoginState = LoginState(),
    private val loginUsecase: LoginUsecase
) : BaseViewModel<LoginState, LoginIntent, LoginSideEffect>(initialState) {

    override fun onIntent(intent: LoginIntent) = reduce(intent)

    override fun reduce(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.NavigateToMain -> {
                postSideEffect(LoginSideEffect.NavigateToMain)
            }

            is LoginIntent.NavigateToRegister -> {
                postSideEffect(LoginSideEffect.NavigateToRegister)
            }

            is LoginIntent.ChangePhoneNumber -> {
                updateState { copy(phoneNumber = intent.phoneNumber) }
            }

            is LoginIntent.SendOtp -> {
                updateState { copy(phoneNumber = intent.phoneNumber) }
                viewModelScope.launch {
                    loginUsecase(intent.phoneNumber).collect { result ->
                        updateState { copy(loginUiState = result) }
                        if (result is UiState.Success) {
                            postSideEffect(LoginSideEffect.NavigateToOtpVerification(intent.phoneNumber))
                        }
                    }
                }
            }
        }
    }
}
