package com.cashcove.features.authentication.presentation.onbording

sealed interface OnBoardingSideEffect {
    data object NavigateToRegister : OnBoardingSideEffect
}
