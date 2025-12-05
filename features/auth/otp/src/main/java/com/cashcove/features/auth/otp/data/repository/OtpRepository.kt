package com.cashcove.features.auth.otp.data.repository

import com.cashcove.core.common.model.RepositoryBaseResult
import com.cashcove.features.auth.otp.data.model.ResendOtpRequestDTO
import com.cashcove.features.auth.otp.data.model.ResendOtpResponseDTO
import com.cashcove.features.auth.otp.data.model.VerifyOtpRequestDTO
import com.cashcove.features.auth.otp.data.model.VerifyOtpResponseDTO

interface OtpRepository {
    suspend fun verifyOtp(request: VerifyOtpRequestDTO): RepositoryBaseResult<VerifyOtpResponseDTO>
    suspend fun resendOtp(request: ResendOtpRequestDTO): RepositoryBaseResult<ResendOtpResponseDTO>
}