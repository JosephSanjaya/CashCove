package com.cashcove.features.authentication.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.cashcove.core.common.model.CallbackFunction
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel(),
    onNavigateToMain: CallbackFunction,
    onNavigateToRegister: CallbackFunction,
) {
    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect {
            when (it) {
                LoginSideEffect.NavigateToMain -> onNavigateToMain()
                LoginSideEffect.NavigateToRegister -> onNavigateToRegister()
            }
        }
    }
}