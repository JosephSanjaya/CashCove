package com.cashcove.features.auth.register.domain.usecase

import com.cashcove.core.common.model.RepositoryBaseResult
import com.cashcove.core.common.model.UsecaseBaseResult
import com.cashcove.core.datastore.UserPreferencesManager
import com.cashcove.features.auth.register.data.model.SendOtpRequestDTO
import com.cashcove.features.auth.register.data.repository.RegisterRepository
import com.cashcove.features.auth.register.domain.model.Register
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRegisterUsecase(
    private val repository: RegisterRepository,
    private val userPreferences: UserPreferencesManager
) {
    suspend operator fun invoke(phoneNumber: String): Flow<UsecaseBaseResult<Register>> =
        flow {
            emit(UsecaseBaseResult.Loading)
            val register = Register(phoneNumber)
            if (register.validate()) {
                when (val result = repository.sendOtp(SendOtpRequestDTO(phoneNumber))) {
                    is RepositoryBaseResult.Success -> {
                        userPreferences.updatePhoneNumber(phoneNumber)
                        emit(UsecaseBaseResult.Success(data = register))
                    }

                    is RepositoryBaseResult.Error -> emit(
                        UsecaseBaseResult.Error(
                            Exception(result.exception.message ?: "something went wrong!")
                        )
                    )
                }
            } else {
                emit(UsecaseBaseResult.Error(Exception("phone number validation error!")))
            }
        }
}