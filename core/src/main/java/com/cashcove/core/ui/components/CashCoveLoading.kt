package com.cashcove.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cashcove.core.ui.theme.CashCoveTheme

@Composable
fun CashCoveLoading(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxSize()) {
        CircularProgressIndicator()
        Text("Loading...")
    }
}

@Preview(showBackground = true, heightDp = 540)
@Composable
private fun CashCoveLoadingPreview() {
    CashCoveTheme {
        CashCoveLoading()
    }
}
