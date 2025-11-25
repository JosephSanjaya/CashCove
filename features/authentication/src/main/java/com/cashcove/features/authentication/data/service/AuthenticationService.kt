package com.cashcove.features.authentication.data.service

import com.cashcove.features.authentication.data.model.request.ChangePhoneSendOtpRequestModel
import com.cashcove.features.authentication.data.model.request.ChangePhoneVerifyOtpRequestModel
import com.cashcove.features.authentication.data.model.request.RefreshTokenRequestModel
import com.cashcove.features.authentication.data.model.request.UpdateUserRequestModel
import com.cashcove.features.authentication.data.model.response.ChangePhoneSendOtpResponseModel
import com.cashcove.features.authentication.data.model.response.ChangePhoneVerifyOtpResponseModel
import com.cashcove.features.authentication.data.model.response.GetUserResponseModel
import com.cashcove.features.authentication.data.model.response.LogoutResponseModel
import com.cashcove.features.authentication.data.model.response.RefreshTokenResponseModel
import com.cashcove.features.authentication.data.model.response.UpdateUserResponseModel
import com.cashcove.features.authentication.screens.login.data.model.SendOtpRequestDTO
import com.cashcove.features.authentication.screens.login.data.model.SendOtpResponseDTO
import com.cashcove.features.authentication.screens.onboarding.data.model.OnboardingResponseDTO
import com.cashcove.features.authentication.screens.otp.data.model.VerifyOtpRequestDTO
import com.cashcove.features.authentication.screens.otp.data.model.VerifyOtpResponseDTO
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.PUT

interface AuthenticationService {
    @POST("auth/send-otp")
    suspend fun sendOtp(
        @Body request: SendOtpRequestDTO
    ): SendOtpResponseDTO

    @POST("auth/verify-otp")
    suspend fun verifyOtp(
        @Body request: VerifyOtpRequestDTO
    ): VerifyOtpResponseDTO

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

    @GET("/data/onboarding")
    suspend fun onboarding(): OnboardingResponseDTO
}
