package com.cashcove.features.authentication.screens.otp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cashcove.core.common.model.CallbackFunction
import com.cashcove.core.common.model.UiState
import com.cashcove.core.common.utils.extensions.safeError
import com.cashcove.core.ui.components.CashCoveContentLoader
import com.cashcove.core.ui.theme.CashCoveTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OtpScreen(
    phoneNumber: String,
    onNavigateToMain: CallbackFunction,
    onNavigateBack: CallbackFunction,
    viewModel: OtpViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    // Initialize phone number in state
    LaunchedEffect(phoneNumber) {
        if (state.phoneNumber.isEmpty()) {
            viewModel.onIntent(OtpIntent.UpdatePhoneNumber(phoneNumber))
        }
    }

    // Handle side effects
    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect {
            when (it) {
                OtpSideEffect.NavigateToMain -> onNavigateToMain()
                OtpSideEffect.NavigateBack -> onNavigateBack()
            }
        }
    }

    OtpContent(state = state, onIntent = viewModel::onIntent)
}

@Composable
private fun OtpContent(
    state: OtpState,
    onIntent: (OtpIntent) -> Unit
) {
    CashCoveContentLoader(state.confirmOtpUiState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Enter OTP for ${state.phoneNumber}",
                modifier = Modifier.padding(bottom = 24.dp)
            )

            TextField(
                value = state.otpCode,
                onValueChange = { onIntent(OtpIntent.UpdateOtpCode(it)) },
                label = { Text("OTP Code") },
                modifier = Modifier.fillMaxWidth(),
                enabled = state.confirmOtpUiState !is UiState.Loading
            )

            // Show error message if any
            if (state.confirmOtpUiState is UiState.Error) {
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = state.confirmOtpUiState.message.safeError(),
                    color = androidx.compose.material3.MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            Button(
                onClick = { onIntent(OtpIntent.OtpEnter(state.otpCode)) },
                modifier = Modifier.fillMaxWidth(),
                enabled = state.otpCode.isNotBlank() && state.confirmOtpUiState !is UiState.Loading
            ) {
                Text("Confirm")
            }

            Spacer(modifier = Modifier.size(8.dp))

            TextButton(
                onClick = { onIntent(OtpIntent.ResendOtp) },
                modifier = Modifier.fillMaxWidth(),
                enabled = state.confirmOtpUiState !is UiState.Loading
            ) {
                Text("Resend OTP")
            }
            TextButton(onClick = {onIntent(OtpIntent.NavigateBack)}) { Text("back")}
        }
    }
}

@Preview(showBackground = true, heightDp = 500)
@Composable
private fun OtpContentPreview() {
    CashCoveTheme {
        OtpContent(state = OtpState(), onIntent = {})
    }
}
