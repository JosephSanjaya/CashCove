package com.cashcove.features.auth.login.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cashcove.core.common.model.CallbackFunction
import com.cashcove.core.common.model.StringCallbackFunction
import com.cashcove.core.common.model.UiState
import com.cashcove.core.ui.theme.CashCoveTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    onNavigateToMain: CallbackFunction,
    onNavigateToRegister: CallbackFunction,
    viewModel: LoginViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect {
            when (it) {
                LoginSideEffect.NavigateToMain -> onNavigateToMain()
                LoginSideEffect.NavigateToRegister -> onNavigateToRegister()
                LoginSideEffect.NavigateToOtpVerification -> {
                    // Navigate to OTP verification screen
                    // TODO: Add navigation callback when OTP screen is ready
                    onNavigateToMain() // Temporary: navigate to main for now
                }
            }
        }
    }

    LoginContent(
        phoneNumber = state.phoneNumber,
        loginUiState = state.loginUiState,
        onPhoneNumberChange = { phoneNumber ->
            viewModel.onIntent(LoginIntent.UpdatePhoneNumber(phoneNumber))
        },
        onSendOtpClick = {
            viewModel.onIntent(LoginIntent.SendOtp(state.phoneNumber))
        },
    )
}

@Composable
private fun LoginContent(
    phoneNumber: String,
    loginUiState: UiState,
    onPhoneNumberChange: StringCallbackFunction,
    onSendOtpClick: CallbackFunction,
) {
    Column(Modifier.fillMaxWidth()) {
        Text("enter phone number")
        TextField(value = phoneNumber, onValueChange = onPhoneNumberChange)
        when (loginUiState) {
            is UiState.Loading -> CircularProgressIndicator()
            else -> Button(onClick = onSendOtpClick) { Text("send otp") }
        }
    }
}

@Preview(showBackground = true, heightDp = 500)
@Composable
private fun LoginContentPreview() {
    CashCoveTheme {
        LoginContent(
            phoneNumber = "09373608585",
            loginUiState = UiState.Idle,
            onSendOtpClick = {},
            onPhoneNumberChange = {},
        )
    }
}
