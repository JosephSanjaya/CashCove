package com.cashcove.features.authentication.presentation.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.cashcove.core.common.model.CallbackFunction
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = koinViewModel(),
    onNavigateToLogin: CallbackFunction,
    onNavigateToMain: CallbackFunction,
) {
    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect {
            when (it) {
                RegisterSideEffect.NavigateToLogin -> onNavigateToLogin()
                RegisterSideEffect.NavigateToMain -> onNavigateToMain()
            }
        }
    }
}