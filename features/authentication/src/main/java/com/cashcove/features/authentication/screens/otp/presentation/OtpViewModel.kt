package com.cashcove.features.authentication.screens.otp.presentation

import androidx.lifecycle.viewModelScope
import com.cashcove.core.common.model.UiState
import com.cashcove.core.viewmodel.BaseViewModel
import com.cashcove.features.authentication.screens.otp.domain.model.Otp
import com.cashcove.features.authentication.screens.otp.domain.usecase.OtpConfirmUsecase
import com.cashcove.features.authentication.screens.otp.domain.usecase.OtpResendMessageUsecase
import kotlinx.coroutines.launch

class OtpViewModel(
    initState: OtpState = OtpState(),
    private val otpConfirmUsecase: OtpConfirmUsecase,
    private val otpResendMessageUsecase: OtpResendMessageUsecase
) : BaseViewModel<OtpState, OtpIntent, OtpSideEffect>(initState) {

    override fun onIntent(intent: OtpIntent) = reduce(intent)

    override fun reduce(intent: OtpIntent) {
        when (intent) {
            is OtpIntent.NavigateToMain -> {
                postSideEffect(OtpSideEffect.NavigateToMain)
            }

            is OtpIntent.UpdatePhoneNumber -> {
                updateState { copy(phoneNumber = intent.phoneNumber) }
            }

            is OtpIntent.UpdateOtpCode -> {
                updateState { copy(otpCode = intent.otpCode) }
            }

            is OtpIntent.OtpEnter -> {
                viewModelScope.launch {
                    otpConfirmUsecase(
                        phoneNumber = currentState().phoneNumber,
                        otp = Otp(code = intent.otp)
                    ).collect { result ->
                        updateState { copy(confirmOtpUiState = result) }
                        if (result is UiState.Success) {
                            postSideEffect(OtpSideEffect.NavigateToMain)
                        }
                    }
                }
            }

            is OtpIntent.ResendOtp -> {
                viewModelScope.launch {
                    otpResendMessageUsecase(currentState().phoneNumber).collect { result ->
                        updateState { copy(confirmOtpUiState = result) }
                    }
                }
            }

            OtpIntent.NavigateBack -> postSideEffect(OtpSideEffect.NavigateBack)
        }
    }
}
