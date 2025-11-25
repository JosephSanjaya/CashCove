package com.cashcove.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cashcove.core.common.model.CallbackFunction
import com.cashcove.core.common.utils.extensions.safeError
import com.cashcove.core.ui.theme.CashCoveTheme

@Composable
fun CashCoveError(
    error: String?,
    modifier: Modifier = Modifier,
    onRetry: CallbackFunction? = null,
) {
    Column(modifier.fillMaxSize()) {
        Icon(
            Icons.Default.Warning,
            contentDescription = "error",
        )
        Text(error.safeError())
        onRetry?.let {
            Button(onClick = onRetry) { Text("Retry!") }
        }
    }
}

@Preview(showBackground = true, heightDp = 540)
@Composable
private fun CashCoveErrorPreview() {
    CashCoveTheme {
        CashCoveError(error = "error!") {}
    }
}
