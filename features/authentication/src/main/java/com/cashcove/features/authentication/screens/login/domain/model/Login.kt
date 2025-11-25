package com.cashcove.features.authentication.screens.login.domain.model

import com.cashcove.core.common.utils.extensions.validatePhoneNumber

data class Login(val phoneNumber: String) {
    fun validate(): Boolean = phoneNumber.validatePhoneNumber()
}
