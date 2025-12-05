package com.cashcove.core.common.utils.extensions

import com.cashcove.core.common.constants.RegexPatterns

fun String?.isSafe(): Boolean = !(isNullOrEmpty() || isBlank())
fun String.isSafe(): Boolean = isNotBlank()
fun String.validatePhoneNumber(): Boolean = matches(RegexPatterns.PHONE_NUMBER.toRegex())
fun String.validateOtpCode(): Boolean = isNotBlank() && length == 5