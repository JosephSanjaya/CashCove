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

                // Navigate based on state
                when {
                    isFirstLaunch -> postSideEffect(SplashSideEffect.NavigateToOnboarding)
                    authPreferences.isLoggedIn -> postSideEffect(SplashSideEffect.NavigateToMain)
                    else -> postSideEffect(SplashSideEffect.NavigateToLogin)
                }
            } catch (e: Exception) {
                updateState { copy(isLoading = false) }
                // On error, navigate to login as fallback
                postSideEffect(SplashSideEffect.NavigateToLogin)
            }
        }
    }
}
