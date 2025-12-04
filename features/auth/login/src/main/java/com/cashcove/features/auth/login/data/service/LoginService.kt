package com.cashcove.features.auth.login.data.service

import com.cashcove.features.auth.login.data.model.SendOtpRequestDTO
import com.cashcove.features.auth.login.data.model.SendOtpResponseDTO
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST

interface LoginService {
    @POST("auth/send-otp")
    suspend fun sendOtp(
        @Body request: SendOtpRequestDTO
    ): SendOtpResponseDTO
}