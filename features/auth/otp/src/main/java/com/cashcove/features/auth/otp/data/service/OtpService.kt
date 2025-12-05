package com.cashcove.features.auth.otp.data.service

import com.cashcove.features.auth.otp.data.model.ResendOtpRequestDTO
import com.cashcove.features.auth.otp.data.model.ResendOtpResponseDTO
import com.cashcove.features.auth.otp.data.model.VerifyOtpRequestDTO
import com.cashcove.features.auth.otp.data.model.VerifyOtpResponseDTO
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST

interface OtpService {
    @POST("auth/send-otp")
    suspend fun sendOtp(
        @Body request: ResendOtpRequestDTO
    ): ResendOtpResponseDTO

    @POST("auth/verify-otp")
    suspend fun verifyOtp(
        @Body request: VerifyOtpRequestDTO
    ): VerifyOtpResponseDTO
}