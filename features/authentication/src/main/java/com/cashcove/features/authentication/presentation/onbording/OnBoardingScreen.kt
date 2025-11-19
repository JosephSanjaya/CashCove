package com.cashcove.features.authentication.presentation.onbording

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cashcove.core.common.model.CallbackFunction
import com.cashcove.core.ui.theme.CashCoveTheme
import com.cashcove.core.R as coreR
import com.cashcove.features.authentication.data.entity.OnBoardingPageModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import org.koin.androidx.compose.koinViewModel

@Composable
fun OnBoardingScreen(
    onNavigateToRegister: CallbackFunction,
    onNavigateToLogin: CallbackFunction,
    viewModel: OnBoardingViewModel = koinViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect {
            when (it) {
                OnBoardingSideEffect.NavigateToRegister -> onNavigateToRegister()
                OnBoardingSideEffect.NavigateToLogin -> onNavigateToLogin()
            }
        }
    }
    val onboardingList = persistentListOf(
        OnBoardingPageModel(
            title = "Welcome",
            description = "Welcome to CashCove app! Manage your",
            imageRes = coreR.drawable.outline_attach_money_24
        ),
        OnBoardingPageModel(
            title = "Track Your Expenses",
            description = "Keep track of all your expenses.",
            imageRes = coreR.drawable.outline_attach_money_24
        ),
        OnBoardingPageModel(
            title = "Get Started",
            description = "Start managing your finances today.",
            imageRes = coreR.drawable.outline_attach_money_24
        ),
    )
    OnBoardingContent(
        onboardingList = onboardingList,
        onFinishClicked = {}
    )
}

@Composable
private fun OnBoardingContent(
    onboardingList: PersistentList<OnBoardingPageModel>,
    onFinishClicked: CallbackFunction,
) {
    Column(Modifier.fillMaxSize()) {
        onboardingList.forEach {
            Column(Modifier.fillMaxWidth()) {
                Text(it.title)
                Text(it.description)
            }
        }
        Button(onClick = onFinishClicked) {
            Text("Next")
        }
    }
}

@Preview(showBackground = true, heightDp = 500)
@Composable
private fun OnBoardingContentPreview() {
    CashCoveTheme {
        OnBoardingContent(
            onboardingList = persistentListOf(
                OnBoardingPageModel(
                    title = "Get Started",
                    description = "Start managing your finances today.",
                    imageRes = coreR.drawable.outline_attach_money_24
                )
            ),
            onFinishClicked = {}
        )
    }
}
