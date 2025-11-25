package com.cashcove.features.authentication.screens.register.presentation

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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cashcove.core.common.model.CallbackFunction
import com.cashcove.core.common.model.UiState
import com.cashcove.core.common.utils.extensions.safeError
import com.cashcove.core.ui.theme.CashCoveTheme
import com.cashcove.features.authentication.screens.register.domain.model.Register
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterScreen(
    onNavigateToLogin: CallbackFunction,
    onNavigateToMain: CallbackFunction,
    onNavigateToOtp: (String) -> Unit,
    viewModel: RegisterViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect {
            when (it) {
                RegisterSideEffect.NavigateToLogin -> onNavigateToLogin()
                RegisterSideEffect.NavigateToMain -> onNavigateToMain()
                is RegisterSideEffect.NavigateToOtpVerification -> {
                    onNavigateToOtp(it.phoneNumber)
                }
            }
        }
    }
    RegisterContent(
        state = state,
        onIntent = viewModel::onIntent
    )
}

@Composable
private fun RegisterContent(
    state: RegisterState,
    onIntent: (RegisterIntent) -> Unit
) {
    Column(Modifier.fillMaxWidth()) {
        Text("enter phone number")
        TextField(
            value = state.phoneNumber,
            onValueChange = { onIntent(RegisterIntent.ChangePhoneNumber(it)) },
        )
        Button(onClick = {onIntent(RegisterIntent.NavigateToLogin)}) {Text("I already have an account") }
        when (state.registerUiState) {
            is UiState.Loading -> CircularProgressIndicator()
            is UiState.Error -> Text(state.registerUiState.message.safeError())
            else -> Button(onClick = { onIntent(RegisterIntent.SendOtp(state.phoneNumber)) }) {
                Text("send otp")
            }
        }
    }
}

private class RegisterContentPreviewParams : PreviewParameterProvider<RegisterState> {
    override val values: Sequence<RegisterState> = sequenceOf(
        RegisterState(registerUiState = UiState.Loading),
        RegisterState(registerUiState = UiState.Error("some error to be shown")),
        RegisterState(registerUiState = UiState.Success(data = Register(phoneNumber = "0123456789"))),
    )
}

@Preview(showBackground = true, heightDp = 500)
@Composable
private fun RegisterContentPreview(
    @PreviewParameter(RegisterContentPreviewParams::class) param: RegisterState
) {
    CashCoveTheme {
        RegisterContent(
            state = param,
            onIntent = {}
        )
    }
}
