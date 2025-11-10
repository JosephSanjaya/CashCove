package com.cashcove.app.splash

import androidx.lifecycle.viewModelScope
import com.cashcove.core.datastore.AuthPreferencesManager
import com.cashcove.core.datastore.UserPreferencesManager
import com.cashcove.core.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashViewModel(
    private val authPreferencesManager: AuthPreferencesManager,
    private val userPreferencesManager: UserPreferencesManager
) : BaseViewModel<SplashState, SplashSideEffect>(
    initialState = SplashState()
) {
    init {
        checkAuthStatus()
    }

    fun onIntent(intent: SplashIntent) {
        when (intent) {
            is SplashIntent.CheckAuthStatus -> checkAuthStatus()
        }
    }

    private fun checkAuthStatus() {
        updateState { copy(isLoading = true) }

        viewModelScope.launch {
            try {
                val authPreferences = authPreferencesManager.getAuthPreferences().first()
                val isFirstLaunch = userPreferencesManager.isFirstLaunch().first()

                updateState {
                    copy(
                        isLoading = false,
                        isAuthenticated = authPreferences.isLoggedIn,
                        isFirstLaunch = isFirstLaunch
                    )
                }
                when {
                    isFirstLaunch -> postSideEffect(SplashSideEffect.NavigateToOnboarding)
                    else -> postSideEffect(SplashSideEffect.NavigateToAuthentication)
                }
            } catch (e: Exception) {
                updateState { copy(isLoading = false) }
                postSideEffect(SplashSideEffect.NavigateToAuthentication)
            }
        }
    }
}
