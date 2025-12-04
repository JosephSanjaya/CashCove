package com.cashcove.features.auth.login.domain.usecase

import com.cashcove.core.common.model.RepositoryBaseResult
import com.cashcove.features.auth.login.data.model.SendOtpRequestDTO
import com.cashcove.features.auth.login.data.repository.LoginRepository
import com.cashcove.features.auth.login.domain.model.Login
import com.cashcove.features.auth.login.domain.model.LoginUsecaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserLoginUsecase(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(phoneNumber: String): Flow<LoginUsecaseResult> = flow {
        emit(LoginUsecaseResult.Loading)
        val login = Login(phoneNumber)
        if (login.validate()) {
            when (val result = repository.sendOtp(SendOtpRequestDTO(phoneNumber))) {
                RepositoryBaseResult.Loading -> emit(LoginUsecaseResult.Loading)
                is RepositoryBaseResult.Success -> emit(LoginUsecaseResult.Success)
                is RepositoryBaseResult.Error -> emit(
                    LoginUsecaseResult.Error(
                        result.exception.message ?: "something went wrong!"
                    )
                )
            }
        } else {
            emit(LoginUsecaseResult.Error("phone number validation error!"))
        }
    }
}