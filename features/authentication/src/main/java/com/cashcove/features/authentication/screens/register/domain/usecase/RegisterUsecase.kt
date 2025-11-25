package com.cashcove.features.authentication.screens.register.domain.usecase

import com.cashcove.core.common.model.UiState
import com.cashcove.features.authentication.data.repository.AuthenticationRepository
import com.cashcove.features.authentication.screens.login.data.model.SendOtpRequestDTO
import com.cashcove.features.authentication.screens.register.domain.model.Register
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterUsecase(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(phoneNumber: String): Flow<UiState<Register>> = flow {
        emit(UiState.Loading)
        val register = Register(phoneNumber)
        if (register.validate()) {
            when (val result = repository.sendOtp(SendOtpRequestDTO(phoneNumber))) {
                is UiState.Error -> emit(result)
                is UiState.Success -> emit(UiState.Success(register))
                else -> {}
            }
        } else {
            emit(UiState.Error("phone number validation error!"))
        }
    }
}
