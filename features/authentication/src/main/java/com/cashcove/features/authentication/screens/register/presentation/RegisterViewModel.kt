package com.cashcove.features.authentication.screens.register.presentation

import androidx.lifecycle.viewModelScope
import com.cashcove.core.common.model.UiState
import com.cashcove.core.viewmodel.BaseViewModel
import com.cashcove.features.authentication.screens.register.domain.usecase.RegisterUsecase
import kotlinx.coroutines.launch

class RegisterViewModel(
    initialState: RegisterState = RegisterState(),
    private val registerUsecase: RegisterUsecase
) : BaseViewModel<RegisterState, RegisterIntent, RegisterSideEffect>(initialState) {

    override fun onIntent(intent: RegisterIntent) = reduce(intent)

    override fun reduce(intent: RegisterIntent) {
        when (intent) {
            is RegisterIntent.NavigateToLogin -> {
                postSideEffect(RegisterSideEffect.NavigateToLogin)
            }

            is RegisterIntent.NavigateToMain -> {
                postSideEffect(RegisterSideEffect.NavigateToMain)
            }

            is RegisterIntent.ChangePhoneNumber -> {
                updateState { copy(phoneNumber = intent.phoneNumber) }
            }

            is RegisterIntent.SendOtp -> {
                updateState { copy(phoneNumber = intent.phoneNumber) }
                viewModelScope.launch {
                    registerUsecase(intent.phoneNumber).collect { result ->
                        updateState { copy(registerUiState = result) }
                        if (result is UiState.Success) {
                            postSideEffect(RegisterSideEffect.NavigateToOtpVerification(intent.phoneNumber))
                        }
                    }
                }
            }
        }
    }
}
