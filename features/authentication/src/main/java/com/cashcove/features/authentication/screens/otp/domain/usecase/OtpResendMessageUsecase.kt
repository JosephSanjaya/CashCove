package com.cashcove.features.authentication.screens.otp.domain.usecase

import com.cashcove.core.common.model.UiState
import com.cashcove.features.authentication.data.repository.AuthenticationRepository
import com.cashcove.features.authentication.screens.login.data.model.SendOtpRequestDTO
import com.cashcove.features.authentication.screens.login.data.model.SendOtpResponseDTO
import com.cashcove.features.authentication.screens.otp.domain.model.Otp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OtpResendMessageUsecase(
    private val repository: AuthenticationRepository
) {

    suspend operator fun invoke(phoneNumber: String): Flow<UiState<Otp>> = flow {
        emit(UiState.Loading)
        when (val result = repository.sendOtp(SendOtpRequestDTO(phoneNumber))) {
            is UiState.Success<SendOtpResponseDTO> -> emit(UiState.Success(Otp("")))
            is UiState.Error -> emit(result)
            UiState.Idle -> {}
            UiState.Loading -> {}
        }
    }
}