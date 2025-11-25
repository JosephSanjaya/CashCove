package com.cashcove.features.authentication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cashcove.features.authentication.screens.login.presentation.LoginScreen
import com.cashcove.features.authentication.screens.onboarding.presentation.OnboardingScreen
import com.cashcove.features.authentication.screens.otp.presentation.OtpScreen
import com.cashcove.features.authentication.screens.register.presentation.RegisterScreen

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
            OnboardingScreen(
                onNavigateToRegister = { navController.navigate(AuthenticationScreen.Register.route) },
                onNavigateToLogin = { navController.navigate(AuthenticationScreen.Login.route) }
            )
        }
        composable(route = AuthenticationScreen.Login.route) {
            LoginScreen(
                onNavigateToRegister = { navController.navigate(AuthenticationScreen.Register.route) },
                onNavigateToOtp = { phoneNumber ->
                    navController.navigate("${AuthenticationScreen.Otp.route}/$phoneNumber")
                },
                onNavigateToMain = { navController.navigate(AuthenticationScreen.Main.route) }
            )
        }
        composable(route = "${AuthenticationScreen.Otp.route}/{phoneNumber}") { backStackEntry ->
            val phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: ""
            OtpScreen(
                phoneNumber = phoneNumber,
                onNavigateToMain = { navController.navigate(AuthenticationScreen.Main.route) }
            )
        }
        composable(route = AuthenticationScreen.Register.route) {
            RegisterScreen(
                onNavigateToMain = { navController.navigate(AuthenticationScreen.Main.route) },
                onNavigateToLogin = { navController.navigate(AuthenticationScreen.Login.route) },
                onNavigateToOtp = { phoneNumber ->
                    navController.navigate("${AuthenticationScreen.Otp.route}/$phoneNumber")
                }
            )
        }
    }
}
