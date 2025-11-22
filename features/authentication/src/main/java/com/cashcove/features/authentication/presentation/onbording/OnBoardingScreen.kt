package com.cashcove.features.authentication.presentation.onbording

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
import kotlinx.collections.immutable.PersistentList
import org.koin.androidx.compose.koinViewModel

@Composable
fun OnBoardingScreen(
    onNavigateToRegister: CallbackFunction,
    onNavigateToLogin: CallbackFunction,
    viewModel: OnBoardingViewModel = koinViewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect {
            when (it) {
                OnBoardingSideEffect.NavigateToRegister -> onNavigateToRegister()
                OnBoardingSideEffect.NavigateToLogin -> onNavigateToLogin()
            }
        }
    }
    CashCoveContentLoader(state.onboardingDataUiState) {
        OnBoardingContent(
            it.onboardingPages,
            { viewModel.onIntent(OnBoardingIntent.OnboardingFinished) }
        )
    }
}

@Composable
private fun OnBoardingContent(
    onboardingList: PersistentList<OnBoardingState.OnboardingPage>,
    onFinishClicked: CallbackFunction,
) {
    Onboarding(onboardingList)
    Button(onClick = onFinishClicked) { Text("Skip") }
}

@Composable
private fun Onboarding(onboardingPages: PersistentList<OnBoardingState.OnboardingPage>) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { onboardingPages.size })
    HorizontalPager(state = pagerState, userScrollEnabled = true) { index ->
        AsyncImage(
            model = onboardingPages[index].imageUrl,
            contentDescription = null
        )
        Text(onboardingPages[index].title)
        Text(onboardingPages[index].description)
    }

}

@Preview(showBackground = true, heightDp = 500)
@Composable
private fun OnBoardingContentPreview() {
    CashCoveTheme {

    }
}
