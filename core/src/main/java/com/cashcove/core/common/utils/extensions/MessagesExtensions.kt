package com.cashcove.core.common.utils.extensions

fun String?.safeError(): String = if (this.isSafe()) this!! else "Error occurred!"
fun String.safeError(): String = if (this.isSafe()) this else "Error occurred!"
