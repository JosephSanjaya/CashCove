package com.cashcove.core.common.utils.extensions

fun String?.isSafe(): Boolean = !(isNullOrEmpty() || isBlank())
fun String.isSafe(): Boolean = isNotBlank()
