package com.cashcove.features.auth.login.presentation

import androidx.lifecycle.viewModelScope
import com.cashcove.core.viewmodel.BaseViewModel
import com.cashcove.features.auth.login.domain.model.LoginUsecaseResult
import com.cashcove.features.auth.login.domain.usecase.UserLoginUsecase
import kotlinx.coroutines.launch

class LoginViewModel(
    initialState: LoginState = LoginState(),
    private val loginUsecase: UserLoginUsecase
) : BaseViewModel<LoginState, LoginIntent, LoginSideEffect>(initialState) {

    fun onIntent(intent: LoginIntent) = reduce(intent)

    override fun reduce(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.NavigateToMain -> {
                postSideEffect(LoginSideEffect.NavigateToMain)
            }

            is LoginIntent.NavigateToRegister -> {
                postSideEffect(LoginSideEffect.NavigateToRegister)
            }

            is LoginIntent.OtpSent -> postSideEffect(LoginSideEffect.NavigateToOtpVerification)
            is LoginIntent.SendOtp -> {
                viewModelScope.launch {
                    loginUsecase(intent.phoneNumber).collect {
                        updateState { copy(loginUiState = it) }
                        if (it is LoginUsecaseResult.Success) {
                            postSideEffect(LoginSideEffect.NavigateToOtpVerification)
                        }
                    }
                }
            }

            else -> {}
        }
    }
}
