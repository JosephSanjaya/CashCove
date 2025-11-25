package com.cashcove.features.authentication.screens.login.presentation

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
import com.cashcove.features.authentication.screens.login.domain.model.Login
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(
    onNavigateToMain: CallbackFunction,
    onNavigateToRegister: CallbackFunction,
    onNavigateToOtp: (String) -> Unit,
    viewModel: LoginViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect {
            when (it) {
                LoginSideEffect.NavigateToMain -> onNavigateToMain()
                LoginSideEffect.NavigateToRegister -> onNavigateToRegister()
                is LoginSideEffect.NavigateToOtpVerification -> {
                    onNavigateToOtp(it.phoneNumber)
                }
            }
        }
    }
    LoginContent(
        state = state,
        onIntent = viewModel::onIntent
    )
}

@Composable
private fun LoginContent(
    state: LoginState,
    onIntent: (LoginIntent) -> Unit
) {
    Column(Modifier.fillMaxWidth()) {
        Text("enter phone number")
        TextField(
            value = state.phoneNumber,
            onValueChange = { onIntent(LoginIntent.ChangePhoneNumber(it)) },
        )
        Button(onClick = {onIntent(LoginIntent.NavigateToRegister)}) {Text("I don't have an account") }
        when (state.loginUiState) {
            is UiState.Loading -> CircularProgressIndicator()
            is UiState.Error -> Text(state.loginUiState.message.safeError())
            else -> Button(onClick = { onIntent(LoginIntent.SendOtp(state.phoneNumber)) }) {
                Text("send otp")
            }
        }
    }
}

private class LoginContentPreviewParams : PreviewParameterProvider<LoginState> {
    override val values: Sequence<LoginState> = sequenceOf(
        LoginState(loginUiState = UiState.Loading),
        LoginState(loginUiState = UiState.Error("some error to be shown")),
        LoginState(loginUiState = UiState.Success(data = Login(phoneNumber = "0123456789"))),
    )
}

@Preview(showBackground = true, heightDp = 500)
@Composable
private fun LoginContentPreview(
    @PreviewParameter(LoginContentPreviewParams::class) param: LoginState
) {
    CashCoveTheme {
        LoginContent(
            state = param,
            onIntent = {}
        )
    }
}
