package com.cashcove.features.authentication.data.service

import com.cashcove.features.authentication.data.entity.request.ChangePhoneSendOtpRequestModel
import com.cashcove.features.authentication.data.entity.request.ChangePhoneVerifyOtpRequestModel
import com.cashcove.features.authentication.data.entity.request.RefreshTokenRequestModel
import com.cashcove.features.authentication.data.entity.request.SendOtpRequestModel
import com.cashcove.features.authentication.data.entity.request.UpdateUserRequestModel
import com.cashcove.features.authentication.data.entity.request.VerifyOtpRequestModel
import com.cashcove.features.authentication.data.entity.response.ChangePhoneSendOtpResponseModel
import com.cashcove.features.authentication.data.entity.response.ChangePhoneVerifyOtpResponseModel
import com.cashcove.features.authentication.data.entity.response.GetUserResponseModel
import com.cashcove.features.authentication.data.entity.response.LogoutResponseModel
import com.cashcove.features.authentication.data.entity.response.RefreshTokenResponseModel
import com.cashcove.features.authentication.data.entity.response.SendOtpResponseModel
import com.cashcove.features.authentication.data.entity.response.UpdateUserResponseModel
import com.cashcove.features.authentication.data.entity.response.VerifyOtpResponseModel
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.PUT

interface AuthenticationService {
    @POST("auth/send-otp")
    suspend fun sendOtp(
        @Body request: SendOtpRequestModel
    ): SendOtpResponseModel

    @POST("auth/verify-otp")
    suspend fun verifyOtp(
        @Body request: VerifyOtpRequestModel
    ): VerifyOtpResponseModel

    @POST("auth/logout")
    suspend fun logout(): LogoutResponseModel

    @POST("auth/refresh-token")
    suspend fun refreshToken(
        @Body request: RefreshTokenRequestModel
    ): RefreshTokenResponseModel

    @GET("auth/me")
    suspend fun getCurrentUser(): GetUserResponseModel

    @PUT("auth/me")
    suspend fun updateUser(
        @Body request: UpdateUserRequestModel
    ): UpdateUserResponseModel

    @POST("auth/change-phone/send-otp")
    suspend fun changePhoneSendOtp(
        @Body request: ChangePhoneSendOtpRequestModel
    ): ChangePhoneSendOtpResponseModel

    @POST("auth/change-phone/verify-otp")
    suspend fun changePhoneVerifyOtp(
        @Body request: ChangePhoneVerifyOtpRequestModel
    ): ChangePhoneVerifyOtpResponseModel
}