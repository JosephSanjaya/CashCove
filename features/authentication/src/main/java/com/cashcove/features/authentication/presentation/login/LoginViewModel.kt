package com.cashcove.features.authentication.presentation.login

import androidx.lifecycle.viewModelScope
import com.cashcove.core.common.model.UiState
import com.cashcove.core.viewmodel.BaseViewModel
import com.cashcove.features.authentication.data.model.login.SendOtpRequestDTO
import com.cashcove.features.authentication.data.repository.AuthenticationRepository
import com.cashcove.features.authentication.domain.usecase.LoginUsecase
import kotlinx.coroutines.launch

class LoginViewModel(
    initialState: LoginState = LoginState(),
    private val loginUsecase: LoginUsecase
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
                    loginUsecase(intent.phoneNumber).collect { updateState { copy(loginUiState = it) } }
                }
            }
            else -> {}
        }
    }
}
