package com.cashcove.core.common.utils.extensions

import com.cashcove.core.common.constants.RegexPatterns

fun String?.isSafe(): Boolean = !(isNullOrEmpty() || isBlank())
fun String.validatePhoneNumber(): Boolean = matches(RegexPatterns.PHONE_NUMBER.toRegex())
fun String.validateOtp(): Boolean = isSafe() && length == 6
