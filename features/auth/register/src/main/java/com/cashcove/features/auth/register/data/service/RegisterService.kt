package com.cashcove.features.auth.register.data.service

import com.cashcove.features.auth.register.data.model.SendOtpRequestDTO
import com.cashcove.features.auth.register.data.model.SendOtpResponseDTO
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST

interface RegisterService {
    @POST("auth/send-otp")
    suspend fun sendOtp(
        @Body request: SendOtpRequestDTO
    ): SendOtpResponseDTO
}