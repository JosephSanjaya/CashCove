package com.cashcove.features.auth.register.presentation

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
import com.cashcove.features.auth.register.domain.model.Register
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreen(
    onNavigateToMain: CallbackFunction,
    onNavigateToRegister: CallbackFunction,
    viewModel: RegisterViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect {
            when (it) {
                RegisterSideEffect.NavigateToMain -> onNavigateToMain()
                RegisterSideEffect.NavigateToLogin -> onNavigateToRegister()
                RegisterSideEffect.NavigateToOtpVerification -> {
                    // Navigate to OTP verification screen
                    // TODO: Add navigation callback when OTP screen is ready
                    onNavigateToMain() // Temporary: navigate to main for now
                }
            }
        }
    }

    RegisterContent(
        phoneNumber = state.phoneNumber,
        registerUiState = state.registerUiState,
        onPhoneNumberChange = { phoneNumber ->
            viewModel.onIntent(RegisterIntent.UpdatePhoneNumber(phoneNumber))
        },
        onSendOtpClick = {
            viewModel.onIntent(RegisterIntent.SendOtp(state.phoneNumber))
        },
    )
}

@Composable
private fun RegisterContent(
    phoneNumber: String,
    registerUiState: UiState<Register>,
    onPhoneNumberChange: StringCallbackFunction,
    onSendOtpClick: CallbackFunction,
) {
    Column(Modifier.fillMaxWidth()) {
        Text("enter phone number")
        TextField(value = phoneNumber, onValueChange = onPhoneNumberChange)
        when (registerUiState) {
            is UiState.Loading -> CircularProgressIndicator()
            else -> Button(onClick = onSendOtpClick) { Text("send otp") }
        }
    }
}

@Preview(showBackground = true, heightDp = 500)
@Composable
private fun RegisterContentPreview() {
    CashCoveTheme {
        RegisterContent(
            phoneNumber = "09373608585",
            registerUiState = UiState.Idle,
            onSendOtpClick = {},
            onPhoneNumberChange = {},
        )
    }
}
