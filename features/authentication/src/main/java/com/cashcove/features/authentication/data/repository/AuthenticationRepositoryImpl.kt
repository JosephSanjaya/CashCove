package com.cashcove.features.authentication.data.repository

import com.cashcove.core.common.model.UiState
import com.cashcove.core.common.repository.BaseRepository
import com.cashcove.core.di.RepositoryDependencies
import com.cashcove.features.authentication.data.model.request.ChangePhoneSendOtpRequestModel
import com.cashcove.features.authentication.data.model.request.ChangePhoneVerifyOtpRequestModel
import com.cashcove.features.authentication.data.model.request.RefreshTokenRequestModel
import com.cashcove.features.authentication.data.model.login.SendOtpRequestDTO
import com.cashcove.features.authentication.data.model.request.UpdateUserRequestModel
import com.cashcove.features.authentication.data.model.request.VerifyOtpRequestModel
import com.cashcove.features.authentication.data.model.response.ChangePhoneSendOtpResponseModel
import com.cashcove.features.authentication.data.model.response.ChangePhoneVerifyOtpResponseModel
import com.cashcove.features.authentication.data.model.response.LogoutResponseModel
import com.cashcove.features.authentication.data.model.response.RefreshTokenResponseModel
import com.cashcove.features.authentication.data.model.login.SendOtpResponseDTO
import com.cashcove.features.authentication.data.model.response.UpdateUserResponseModel
import com.cashcove.features.authentication.data.model.response.VerifyOtpResponseModel
import com.cashcove.features.authentication.data.service.AuthenticationService

class AuthenticationRepositoryImpl(
    private val authenticationService: AuthenticationService,
    private val dependencies: RepositoryDependencies
) : BaseRepository(dependencies), AuthenticationRepository {
    override suspend fun sendOtp(request: SendOtpRequestDTO): UiState<SendOtpResponseDTO> =
        safeApiCall { authenticationService.sendOtp(request) }

    override suspend fun verifyOtp(request: VerifyOtpRequestModel): UiState<VerifyOtpResponseModel> =
        safeApiCall { authenticationService.verifyOtp(request) }

    override suspend fun logout(): UiState<LogoutResponseModel> =
        safeApiCall { authenticationService.logout() }

    override suspend fun refreshToken(request: RefreshTokenRequestModel): UiState<RefreshTokenResponseModel> =
        safeApiCall { authenticationService.refreshToken(request) }

    override suspend fun updateUser(request: UpdateUserRequestModel): UiState<UpdateUserResponseModel> =
        safeApiCall { authenticationService.updateUser(request) }

    override suspend fun changePhoneSendOtp(request: ChangePhoneSendOtpRequestModel): UiState<ChangePhoneSendOtpResponseModel> =
        safeApiCall { authenticationService.changePhoneSendOtp(request) }

    override suspend fun changePhoneVerifyOtp(request: ChangePhoneVerifyOtpRequestModel): UiState<ChangePhoneVerifyOtpResponseModel> =
        safeApiCall { authenticationService.changePhoneVerifyOtp(request) }
}