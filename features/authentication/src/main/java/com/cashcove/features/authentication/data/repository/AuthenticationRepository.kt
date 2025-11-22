package com.cashcove.features.authentication.data.repository

import com.cashcove.core.common.model.UiState
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

interface AuthenticationRepository {
    suspend fun sendOtp(request: SendOtpRequestModel): UiState<SendOtpResponseModel>
    suspend fun verifyOtp(request: VerifyOtpRequestModel): UiState<VerifyOtpResponseModel>
    suspend fun logout(): UiState<LogoutResponseModel>
    suspend fun refreshToken(request: RefreshTokenRequestModel): UiState<RefreshTokenResponseModel>
    suspend fun updateUser(request: UpdateUserRequestModel): UiState<UpdateUserResponseModel>
    suspend fun changePhoneSendOtp(request: ChangePhoneSendOtpRequestModel): UiState<ChangePhoneSendOtpResponseModel>
    suspend fun changePhoneVerifyOtp(request: ChangePhoneVerifyOtpRequestModel): UiState<ChangePhoneVerifyOtpResponseModel>
}