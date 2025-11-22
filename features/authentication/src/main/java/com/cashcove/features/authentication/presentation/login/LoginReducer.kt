package com.cashcove.features.authentication.presentation.login

import com.cashcove.core.common.model.ReducerResult
import com.cashcove.core.common.model.UiState

object LoginReducer {
    fun reduce(
        state: LoginState,
        intent: LoginIntent
    ): ReducerResult<LoginState, LoginCommand> {
        return when (intent) {
            is LoginIntent.UpdatePhoneNumber -> {
                ReducerResult.StateUpdate(
                    state.copy(
                        phoneNumber = intent.phoneNumber,
                        loginUiState = if (state.loginUiState is UiState.Error) {
                            UiState.Idle
                        } else {
                            state.loginUiState
                        }
                    )
                )
            }

            is LoginIntent.SendOtp -> {
                val phoneNumber = state.phoneNumber
                when {
                    phoneNumber.isBlank() -> {
                        ReducerResult.StateUpdate(
                            state.copy(
                                loginUiState = UiState.Error("Phone number cannot be empty")
                            )
                        )
                    }
                    else -> {
                        ReducerResult.StateWithCommand(
                            state = state.copy(loginUiState = UiState.Loading),
                            command = LoginCommand.SendOtpCommand(phoneNumber)
                        )
                    }
                }
            }

            is LoginIntent.OtpSent -> {
                ReducerResult.StateUpdate(
                    state.copy(
                        loginUiState = UiState.Success(intent.data)
                    )
                )
            }

            is LoginIntent.OtpError -> {
                ReducerResult.StateUpdate(
                    state.copy(
                        loginUiState = UiState.Error(intent.message)
                    )
                )
            }

            is LoginIntent.NavigateToRegister -> {
                ReducerResult.StateUpdate(state)
            }

            is LoginIntent.NavigateToMain -> {
                ReducerResult.StateUpdate(state)
            }
        }
    }
}

