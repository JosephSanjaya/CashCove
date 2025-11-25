package com.cashcove.features.authentication.screens.onboarding.presentation

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.cashcove.core.common.model.CallbackFunction
import com.cashcove.core.ui.components.CashCoveContentLoader
import com.cashcove.core.ui.theme.CashCoveTheme
import com.cashcove.features.authentication.screens.onboarding.domain.model.OnboardingItemList
import org.koin.androidx.compose.koinViewModel

@Composable
fun OnboardingScreen(
    onNavigateToRegister: CallbackFunction,
    onNavigateToLogin: CallbackFunction,
    viewModel: OnboardingViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect {
            when (it) {
                OnboardingSideEffect.NavigateToRegister -> onNavigateToRegister()
                OnboardingSideEffect.NavigateToLogin -> onNavigateToLogin()
            }
        }
    }
    CashCoveContentLoader(state.onboardingDataUiState) {
        OnboardingContent(
            state = state,
            onIntent = viewModel::onIntent
        )
    }
}

@Composable
private fun OnboardingContent(
    state: OnboardingState,
    onIntent: (OnboardingIntent) -> Unit,
) {
    CashCoveContentLoader(state.onboardingDataUiState) {
        Onboarding(it)
    }
    Button(onClick = { onIntent(OnboardingIntent.OnboardingFinished) }) { Text("Skip") }
}

@Composable
private fun Onboarding(onboardingItemList: OnboardingItemList) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { onboardingItemList.data.size }
    )
    HorizontalPager(state = pagerState, userScrollEnabled = true) { index ->
        AsyncImage(
            model = onboardingItemList.data[index].imageUrl,
            contentDescription = null
        )
        Text(onboardingItemList.data[index].title)
        Text(onboardingItemList.data[index].description)
    }
}

@Preview(showBackground = true, heightDp = 500)
@Composable
private fun OnboardingContentPreview() {
    CashCoveTheme {
        OnboardingContent(state = OnboardingState(), onIntent = {})
    }
}
