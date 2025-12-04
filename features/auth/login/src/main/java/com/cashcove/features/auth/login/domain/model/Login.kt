package com.cashcove.features.auth.login.domain.model

import com.cashcove.core.common.utils.extensions.validatePhoneNumber

data class Login(val phoneNumber: String) {
    fun validate(): Boolean = phoneNumber.validatePhoneNumber()
}
