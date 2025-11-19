package com.cashcove.features.authentication.presentation.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.cashcove.core.common.model.CallbackFunction
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    onNavigateToMain: CallbackFunction,
    onNavigateToRegister: CallbackFunction,
    viewModel: LoginViewModel = koinViewModel(),
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
