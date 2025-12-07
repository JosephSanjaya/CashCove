package com.cashcove.features.auth.otp.presentation

import androidx.lifecycle.viewModelScope
import com.cashcove.core.viewmodel.BaseViewModel
import com.cashcove.features.auth.otp.domain.usecase.ResendOtpUsecase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OtpViewModel(initState: OtpState, private val resendOtpUsecase: ResendOtpUsecase) :
    BaseViewModel<OtpState, OtpIntent, OtpSideEffect>(initState) {

    private val MaxTimerSeconds = 60
    fun onIntent(intent: OtpIntent) = reduce(intent)
    override fun reduce(intent: OtpIntent) {
        when (intent) {
            OtpIntent.NavigateToMain -> postSideEffect(OtpSideEffect.NavigateToMain)
            OtpIntent.ToBack -> postSideEffect(OtpSideEffect.NavigateBack)
            OtpIntent.ToLogin -> postSideEffect(OtpSideEffect.NavigateToLogin)
            OtpIntent.ToRegister -> postSideEffect(OtpSideEffect.NavigateToRegister)
            OtpIntent.ResendOtp -> resendOtp()
            is OtpIntent.OtpEnter -> {updateState { copy() }}
        }
    }

    private fun resendOtp() {
        viewModelScope.launch {
            resendOtpUsecase().collect {
                runTimerFromTop()
            }
        }
    }

    private suspend fun runTimerFromTop(){
        while (true) {
            val diff = MaxTimerSeconds - state.value.timerValue
            updateState {
                copy(
                    timerValue = timerValue + 1,
                    timerValueString = "$diff"
                )
            }
            if (diff > MaxTimerSeconds)
                break
            delay(1000)
        }
    }
}