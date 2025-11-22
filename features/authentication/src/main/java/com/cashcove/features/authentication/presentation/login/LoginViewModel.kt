package com.cashcove.features.authentication.presentation.login

import androidx.lifecycle.viewModelScope
import com.cashcove.core.common.model.UiState
import com.cashcove.core.viewmodel.BaseViewModel
import com.cashcove.features.authentication.data.entity.request.SendOtpRequestModel
import com.cashcove.features.authentication.data.repository.AuthenticationRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    initialState: LoginState = LoginState(),
    private val repository: AuthenticationRepository
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

            is LoginIntent.SendOtp -> {
                viewModelScope.launch {
                    when (val result =
                        repository.sendOtp(SendOtpRequestModel(phoneNumber = intent.phoneNumber))) {
                        is UiState.Success -> {
                            postSideEffect(LoginSideEffect.NavigateToOtpVerification)
                        }

                        is UiState.Error -> {
                            updateState { it}
                        }

                        is UiState.Loading -> {}
                        is UiState.Idle -> {}
                    }
                }
            }

            else -> {}
        }
    }
}
