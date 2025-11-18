package com.cashcove.features.authentication.presentation.onbording

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.cashcove.core.R as coreR
import com.cashcove.features.authentication.data.entity.OnBoardingPageModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel = koinViewModel(),
    onNavigateToRegister: () -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect {
            when (it) {
                OnBoardingSideEffect.NavigateToRegister -> onNavigateToRegister
            }
        }
    }
    val onboardingList = listOf(
        OnBoardingPageModel(
            title = "Welcome",
            description = "Welcome to CashCove app! Manage your finances with ease and stay in control of your money.",
            imageRes = coreR.drawable.outline_attach_money_24
        ),
        OnBoardingPageModel(
            title = "Track Your Expenses",
            description = "Keep track of all your expenses and income in one place. Get insights into your spending habits.",
            imageRes = coreR.drawable.outline_attach_money_24
        ),
        OnBoardingPageModel(
            title = "Get Started",
            description = "Start managing your finances today. Create your account and take control of your money!",
            imageRes = coreR.drawable.outline_attach_money_24
        ),
    )
}
