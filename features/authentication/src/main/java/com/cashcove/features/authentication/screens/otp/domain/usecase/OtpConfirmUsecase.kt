package com.cashcove.features.authentication.screens.otp.domain.usecase

import com.cashcove.core.common.model.UiState
import com.cashcove.features.authentication.data.repository.AuthenticationRepository
import com.cashcove.features.authentication.screens.otp.data.model.VerifyOtpRequestDTO
import com.cashcove.features.authentication.screens.otp.domain.model.Otp
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OtpConfirmUsecase(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(phoneNumber: String, otp: Otp): Flow<UiState<Otp>> = flow {
        emit(UiState.Loading)
        if (otp.validate()) {
            when (val result = repository.verifyOtp(VerifyOtpRequestDTO(phoneNumber, otp.code))) {
                is UiState.Success -> emit(UiState.Success(otp))
                is UiState.Error -> emit(result)
                else -> {}
            }
        } else {
            emit(UiState.Error("otp validation error"))
        }
    }
}
