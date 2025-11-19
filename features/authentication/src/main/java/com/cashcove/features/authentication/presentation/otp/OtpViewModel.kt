package com.cashcove.features.authentication.presentation.otp

import com.cashcove.core.viewmodel.BaseViewModel

class OtpViewModel(initState: OtpState): BaseViewModel<OtpState, OtpIntent, OtpSideEffect>(initState){
    override fun onIntent(intent: OtpIntent) {
        when(intent) {
            is OtpIntent.NavigateToMain -> {}
            is OtpIntent.OtpEnter -> {}
            OtpIntent.ResendOtp -> {}
        }
    }

}