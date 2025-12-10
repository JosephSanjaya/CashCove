package com.cashcove.features.auth.register.data.repository

import com.cashcove.core.common.model.RepositoryBaseResult
import com.cashcove.features.auth.register.data.model.SendOtpRequestDTO
import com.cashcove.features.auth.register.data.model.SendOtpResponseDTO

interface RegisterRepository {
    suspend fun sendOtp(request: SendOtpRequestDTO): RepositoryBaseResult<SendOtpResponseDTO>
}