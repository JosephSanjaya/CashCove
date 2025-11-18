package com.cashcove.features.authentication.data.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.cashcove.features.authentication.presentation.onbording.OnBoardingScreen

@Composable
fun AuthenticationNavGraph(
    navController: NavHostController,
    startDestination: String = AuthenticationScreen.OnBoarding.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = AuthenticationScreen.OnBoarding.route) {
            OnBoardingScreen(
                onNavigateToRegister = {
                    navController.navigate(AuthenticationScreen.Register.route)
                }
            )
        }
    }
}
