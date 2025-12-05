package com.cashcove.features.auth.otp.presentation

import com.cashcove.core.viewmodel.BaseViewModel

class OtpViewModel(initState: OtpState) :
    BaseViewModel<OtpState, OtpIntent, OtpSideEffect>(initState) {

    fun onIntent(intent: OtpIntent) = reduce(intent)
    override fun reduce(intent: OtpIntent) {
        when (intent) {
            OtpIntent.NavigateToMain -> postSideEffect(OtpSideEffect.NavigateToMain)
            OtpIntent.ToBack -> postSideEffect(OtpSideEffect.NavigateBack)
            OtpIntent.ToLogin -> postSideEffect(OtpSideEffect.NavigateToLogin)
            OtpIntent.ToRegister -> postSideEffect(OtpSideEffect.NavigateToRegister)
            OtpIntent.ResendOtp -> {}
            is OtpIntent.OtpEnter -> {}

        }
    }

}