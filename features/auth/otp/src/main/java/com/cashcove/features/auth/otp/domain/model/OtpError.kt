package com.cashcove.features.auth.otp.domain.model

sealed class OtpError(
    override val message: String? = null,
    override val cause: Throwable? = null,
) : Exception(message, cause) {
    data object TokensAreEmpty : OtpError("Tokens are empty")
    data object UserDataEmpty : OtpError("User data is empty")
    data object EmptyResponse : OtpError("Empty response from server")
    data object InvalidOtp : OtpError("Invalid otp format")

    data class Network(override val cause: Throwable?) : OtpError("Network error", cause)
    data class Server(val code: Int, val errorBody: String?) :
        OtpError("Server error:$code - errorBody:$errorBody")

    data class Unknown(override val cause: Throwable?) : OtpError("Unknown error", cause)
}