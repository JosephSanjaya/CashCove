package com.cashcove.features.auth.login.data.repository

import com.cashcove.core.common.model.RepositoryBaseResult
import com.cashcove.features.auth.login.data.model.SendOtpRequestDTO
import com.cashcove.features.auth.login.data.model.SendOtpResponseDTO

interface LoginRepository {
    suspend fun sendOtp(request: SendOtpRequestDTO): RepositoryBaseResult<SendOtpResponseDTO>
}