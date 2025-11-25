package com.cashcove.features.authentication.screens.login.domain.usecases

import com.cashcove.core.common.model.UiState
import com.cashcove.features.authentication.data.repository.AuthenticationRepository
import com.cashcove.features.authentication.screens.login.data.model.SendOtpRequestDTO
import com.cashcove.features.authentication.screens.login.domain.model.Login
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginUsecase(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(phoneNumber: String): Flow<UiState<Login>> = flow {
        emit(UiState.Loading)
        val login = Login(phoneNumber)
        if (login.validate()) {
            when (val result = repository.sendOtp(SendOtpRequestDTO(phoneNumber))) {
                is UiState.Error -> emit(result)
                is UiState.Success -> emit(UiState.Success(login))
                else -> {}
            }
        } else {
            emit(UiState.Error("phone number validation error!"))
        }
    }
}
