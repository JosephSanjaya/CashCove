package com.cashcove.features.authentication.screens.register.domain.model

import com.cashcove.core.common.utils.extensions.validatePhoneNumber

data class Register(val phoneNumber: String) {
    fun validate(): Boolean = phoneNumber.validatePhoneNumber()
}
