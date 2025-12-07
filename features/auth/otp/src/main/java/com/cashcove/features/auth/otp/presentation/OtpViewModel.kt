package com.cashcove.features.auth.otp.presentation

import androidx.lifecycle.viewModelScope
import com.cashcove.core.common.model.UiState
import com.cashcove.core.common.model.UsecaseBaseResult
import com.cashcove.core.common.utils.extensions.toUiState
import com.cashcove.core.viewmodel.BaseViewModel
import com.cashcove.features.auth.otp.domain.usecase.ResendOtpUsecase
import com.cashcove.features.auth.otp.domain.usecase.VerifyOtpUsecase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OtpViewModel(
    initState: OtpState,
    private val resendOtpUsecase: ResendOtpUsecase,
    private val verifyOtpUsecase: VerifyOtpUsecase
) :
    BaseViewModel<OtpState, OtpIntent, OtpSideEffect>(initState) {

    private val maxTimerSeconds = 60
    fun onIntent(intent: OtpIntent) = reduce(intent)
    override fun reduce(intent: OtpIntent) {
        when (intent) {
            OtpIntent.NavigateToMain -> postSideEffect(OtpSideEffect.NavigateToMain)
            OtpIntent.ToBack -> postSideEffect(OtpSideEffect.NavigateBack)
            OtpIntent.ToLogin -> postSideEffect(OtpSideEffect.NavigateToLogin)
            OtpIntent.ToRegister -> postSideEffect(OtpSideEffect.NavigateToRegister)
            OtpIntent.ResendOtp -> resendOtp()
            is OtpIntent.OtpEnter -> confirmOtp(intent.otp)
        }
    }

    private fun confirmOtp(otp: String) {
        viewModelScope.launch {
            updateState { copy(verifyOtpUiState = UiState.Loading) }
            verifyOtpUsecase(otp).collect {
                updateState { copy(verifyOtpUiState = it.toUiState()) }
            }
        }
    }

    private fun resendOtp() {
        viewModelScope.launch {
            resendOtpUsecase().collect {
                updateState { copy(resendOtpUiState = it.toUiState()) }
                if (it is UsecaseBaseResult.Success) {
                    runTimerFromTop()
                }
            }
        }
    }

    private suspend fun runTimerFromTop() {
        while (true) {
            val diff = maxTimerSeconds - state.value.timerValue
            updateState {
                copy(
                    timerValue = timerValue + 1,
                    timerValueString = "$diff",
                    isTimerActive = true,
                )
            }
            if (diff > maxTimerSeconds)
                break
            delay(1_000)
        }
        updateState {
            copy(
                resendOtpUiState = UiState.Idle,
                timerValue = 60,
                timerValueString = "",
                isTimerActive = false,
            )
        }
    }
}