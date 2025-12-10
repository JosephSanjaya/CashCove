package com.cashcove.features.auth.register.data.repository

import com.cashcove.core.common.model.RepositoryBaseResult
import com.cashcove.core.common.repository.BaseRepository
import com.cashcove.core.di.RepositoryDependencies
import com.cashcove.features.auth.register.data.model.SendOtpRequestDTO
import com.cashcove.features.auth.register.data.model.SendOtpResponseDTO
import com.cashcove.features.auth.register.data.service.RegisterService

class RegisterRepositoryImpl(
    private val registerService: RegisterService, private val dependencies: RepositoryDependencies,
) : BaseRepository(dependencies), RegisterRepository {
    override suspend fun sendOtp(request: SendOtpRequestDTO): RepositoryBaseResult<SendOtpResponseDTO> =
        safeApiCall {
            registerService.sendOtp(request)
        }
}