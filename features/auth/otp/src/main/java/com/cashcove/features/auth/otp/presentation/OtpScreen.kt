package com.cashcove.features.auth.otp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cashcove.core.common.model.CallbackFunction
import com.cashcove.core.common.model.UiState
import com.cashcove.core.ui.theme.CashCoveTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun OtpScreen(
    onNavigateToMain: CallbackFunction,
    onNavigateToRegister: CallbackFunction,
    onNavigateToLogin: CallbackFunction,
    onNavigateBack: CallbackFunction,
    onBack: CallbackFunction,
    viewModel: OtpViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect {
            when (it) {
                OtpSideEffect.NavigateToMain -> onNavigateToMain()
                OtpSideEffect.NavigateBack -> onNavigateBack()
                OtpSideEffect.NavigateToLogin -> onNavigateToLogin()
                OtpSideEffect.NavigateToRegister -> onNavigateToRegister()
            }
        }
    }

    OtpContent(
        confirmOtpUiState = state.confirmOtpUiState,
        isTimerActive = state.isTimerActive,
        timerValue = state.timerValue,
        onIntent = viewModel::onIntent,
    )
}

@Composable
private fun OtpContent(
    confirmOtpUiState: UiState<*>,
    isTimerActive: Boolean,
    timerValue: String,
    onIntent: (OtpIntent) -> Unit,
) {
    Column(Modifier.fillMaxSize()) {
        var otpState by remember { mutableStateOf("") }
        Text("enter otp")
        TextField(
            value = otpState,
            onValueChange = { newValue ->
                otpState = newValue
                onIntent(OtpIntent.OtpEnter(newValue))
            }
        )
        if (isTimerActive) {
            Text(timerValue)
        } else {
            onIntent(OtpIntent.ResendOtp)
        }
        when (confirmOtpUiState) {
            is UiState.Loading -> CircularProgressIndicator()
            else -> Button(
                onClick = {
                    onIntent(OtpIntent.NavigateToMain)
                }
            ) {
                Text("Confirm")
            }
        }
    }
}

@Preview(showBackground = true, heightDp = 500)
@Composable
private fun OtpContentPreview() {
    CashCoveTheme {
        OtpContent(
            confirmOtpUiState = UiState.Idle,
            isTimerActive = true,
            timerValue = "00:15",
            onIntent = {},
        )
    }
}