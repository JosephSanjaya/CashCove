package com.cashcove.features.auth.otp.data.repository

import com.cashcove.core.common.model.RepositoryBaseResult
import com.cashcove.core.common.repository.BaseRepository
import com.cashcove.core.di.RepositoryDependencies
import com.cashcove.features.auth.otp.data.model.ResendOtpRequestDTO
import com.cashcove.features.auth.otp.data.model.ResendOtpResponseDTO
import com.cashcove.features.auth.otp.data.model.VerifyOtpRequestDTO
import com.cashcove.features.auth.otp.data.model.VerifyOtpResponseDTO
import com.cashcove.features.auth.otp.data.service.OtpService

class OtpRepositoryImpl(
    private val otpService: OtpService,
    private val dependencies: RepositoryDependencies
) : BaseRepository(dependencies), OtpRepository {
    override suspend fun verifyOtp(request: VerifyOtpRequestDTO): RepositoryBaseResult<VerifyOtpResponseDTO> =
        safeApiCall {
            otpService.verifyOtp(request)
        }

    override suspend fun resendOtp(request: ResendOtpRequestDTO): RepositoryBaseResult<ResendOtpResponseDTO> =
        safeApiCall {
            otpService.sendOtp(request)
        }
}