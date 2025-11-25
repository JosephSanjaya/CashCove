package com.cashcove.features.authentication.screens.otp.domain.model

import com.cashcove.core.common.utils.extensions.validateOtp

data class Otp(val code: String) {
    fun validate() = code.validateOtp()
}
