package com.cashcove.features.authentication.screens.onboarding.domain.usecases

import com.cashcove.core.common.model.UiState
import com.cashcove.features.authentication.data.repository.AuthenticationRepository
import com.cashcove.features.authentication.screens.onboarding.data.model.OnboardingResponseDTO
import com.cashcove.features.authentication.screens.onboarding.domain.model.OnboardingItemList
import com.cashcove.features.authentication.screens.onboarding.domain.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OnboardingUsecase(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(): Flow<UiState<OnboardingItemList>> = flow {
        emit(UiState.Loading)
        when (val result = repository.onboarding()) {
            is UiState.Success<OnboardingResponseDTO> -> emit(UiState.Success(result.data.toDomain()))
            is UiState.Error -> emit(result)
            UiState.Idle -> {}
            UiState.Loading -> {}
        }
    }
}
