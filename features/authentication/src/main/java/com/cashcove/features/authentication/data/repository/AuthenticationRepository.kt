package com.cashcove.features.authentication.data.repository

import com.cashcove.core.common.model.UiState
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

interface AuthenticationRepository {
    suspend fun sendOtp(request: SendOtpRequestDTO): UiState<SendOtpResponseDTO>
    suspend fun verifyOtp(request: VerifyOtpRequestModel): UiState<VerifyOtpResponseModel>
    suspend fun logout(): UiState<LogoutResponseModel>
    suspend fun refreshToken(request: RefreshTokenRequestModel): UiState<RefreshTokenResponseModel>
    suspend fun updateUser(request: UpdateUserRequestModel): UiState<UpdateUserResponseModel>
    suspend fun changePhoneSendOtp(request: ChangePhoneSendOtpRequestModel): UiState<ChangePhoneSendOtpResponseModel>
    suspend fun changePhoneVerifyOtp(request: ChangePhoneVerifyOtpRequestModel): UiState<ChangePhoneVerifyOtpResponseModel>
}