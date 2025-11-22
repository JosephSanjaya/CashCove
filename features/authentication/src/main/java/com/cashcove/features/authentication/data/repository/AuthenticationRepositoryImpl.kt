package com.cashcove.features.authentication.data.repository

import com.cashcove.core.common.model.UiState
import com.cashcove.core.common.repository.BaseRepository
import com.cashcove.core.di.RepositoryDependencies
import com.cashcove.features.authentication.data.entity.request.ChangePhoneSendOtpRequestModel
import com.cashcove.features.authentication.data.entity.request.ChangePhoneVerifyOtpRequestModel
import com.cashcove.features.authentication.data.entity.request.RefreshTokenRequestModel
import com.cashcove.features.authentication.data.entity.request.SendOtpRequestModel
import com.cashcove.features.authentication.data.entity.request.UpdateUserRequestModel
import com.cashcove.features.authentication.data.entity.request.VerifyOtpRequestModel
import com.cashcove.features.authentication.data.entity.response.ChangePhoneSendOtpResponseModel
import com.cashcove.features.authentication.data.entity.response.ChangePhoneVerifyOtpResponseModel
import com.cashcove.features.authentication.data.entity.response.LogoutResponseModel
import com.cashcove.features.authentication.data.entity.response.RefreshTokenResponseModel
import com.cashcove.features.authentication.data.entity.response.SendOtpResponseModel
import com.cashcove.features.authentication.data.entity.response.UpdateUserResponseModel
import com.cashcove.features.authentication.data.entity.response.VerifyOtpResponseModel
import com.cashcove.features.authentication.data.service.AuthenticationService

class AuthenticationRepositoryImpl(
    private val authenticationService: AuthenticationService,
    private val dependencies: RepositoryDependencies
) : BaseRepository(dependencies), AuthenticationRepository {
    override suspend fun sendOtp(request: SendOtpRequestModel): UiState<SendOtpResponseModel> =
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