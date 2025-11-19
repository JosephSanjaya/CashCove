package com.cashcove.features.authentication.data.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.cashcove.features.authentication.presentation.login.LoginScreen
import com.cashcove.features.authentication.presentation.onbording.OnBoardingScreen
import com.cashcove.features.authentication.presentation.register.RegisterScreen

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
                onNavigateToRegister = { navController.navigate(AuthenticationScreen.Register.route) },
                onNavigateToLogin = { navController.navigate(AuthenticationScreen.Login.route) }
            )
        }
        composable(route = AuthenticationScreen.Login.route) {
            LoginScreen(
                onNavigateToRegister = { navController.navigate(AuthenticationScreen.Register.route) },
                onNavigateToMain = { navController.navigate(AuthenticationScreen.Main.route) }
            )
        }
        composable(route = AuthenticationScreen.Register.route) {
            RegisterScreen(
                onNavigateToMain = { navController.navigate(AuthenticationScreen.Main.route) },
                onNavigateToLogin = { navController.navigate(AuthenticationScreen.Login.route) }
            )
        }
    }
}
