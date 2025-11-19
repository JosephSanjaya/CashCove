package com.cashcove.features.authentication.presentation.otp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cashcove.core.ui.theme.CashCoveTheme

@Composable
fun OtpScreen(){

}


@Composable
private fun OtpContent(){
    Column(Modifier.fillMaxSize()) {
        var otpState by remember { mutableStateOf("") }
        Text("enter otp")
        TextField(value = otpState, onValueChange = {otpState = it})
        Button(onClick = {}) {Text("Confirm") }
    }
}


@Preview(showBackground = true, heightDp = 500)
@Composable
private fun OtpContentPreview(){
    CashCoveTheme {
        OtpContent()
    }
}