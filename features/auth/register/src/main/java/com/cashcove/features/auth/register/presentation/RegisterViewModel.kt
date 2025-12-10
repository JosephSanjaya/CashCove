package com.cashcove.features.auth.register.presentation

import androidx.lifecycle.viewModelScope
import com.cashcove.core.common.model.UiState
import com.cashcove.core.common.utils.extensions.toUiState
import com.cashcove.core.viewmodel.BaseViewModel
import com.cashcove.features.auth.register.domain.usecase.UserRegisterUsecase
import kotlinx.coroutines.launch

class RegisterViewModel(
    initialState: RegisterState = RegisterState(),
    private val registerUsecase: UserRegisterUsecase
) : BaseViewModel<RegisterState, RegisterIntent, RegisterSideEffect>(initialState) {

    fun onIntent(intent: RegisterIntent) = reduce(intent)

    override fun reduce(intent: RegisterIntent) {
        when (intent) {
            is RegisterIntent.NavigateToMain -> {
                postSideEffect(RegisterSideEffect.NavigateToMain)
            }

            is RegisterIntent.NavigateToLogin -> {
                postSideEffect(RegisterSideEffect.NavigateToLogin)
            }

            is RegisterIntent.OtpSent -> postSideEffect(RegisterSideEffect.NavigateToOtpVerification)
            is RegisterIntent.SendOtp -> {
                viewModelScope.launch {
                    registerUsecase(intent.phoneNumber).collect { registerUsecaseResult ->
                        registerUsecaseResult.toUiState().let {
                            updateState { copy(registerUiState = it) }
                            if (it is UiState.Success) {
                                postSideEffect(RegisterSideEffect.NavigateToOtpVerification)
                            }
                        }
                    }
                }
            }

            else -> {}
        }
    }
}
