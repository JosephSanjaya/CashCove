package com.cashcove.features.auth.login.data.repository

import com.cashcove.core.common.model.UiState
import com.cashcove.core.common.repository.BaseRepository
import com.cashcove.core.di.RepositoryDependencies
import com.cashcove.features.auth.login.data.model.SendOtpRequestDTO
import com.cashcove.features.auth.login.data.model.SendOtpResponseDTO
import com.cashcove.features.auth.login.data.service.LoginService

class LoginRepositoryImpl(
    private val loginService: LoginService, private val dependencies: RepositoryDependencies
) : BaseRepository(dependencies), LoginRepository {
    override suspend fun sendOtp(request: SendOtpRequestDTO): UiState<SendOtpResponseDTO> =
        safeApiCall {
            loginService.sendOtp(request)
        }
}