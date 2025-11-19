package com.cashcove.features.authentication.presentation.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.cashcove.core.common.model.CallbackFunction
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreen(
    onNavigateToLogin: CallbackFunction,
    onNavigateToMain: CallbackFunction,
    viewModel: RegisterViewModel = koinViewModel(),
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
