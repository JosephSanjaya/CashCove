package com.cashcove.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cashcove.app.splash.SplashScreen
import com.cashcove.features.authentication.presentation.login.LoginScreen
import com.cashcove.features.authentication.presentation.otp.OtpScreen
import com.cashcove.features.authentication.presentation.register.RegisterScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = AppScreen.Splash.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = AppScreen.Splash.route) {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate(AppScreen.Login.route) {
                        popUpTo(AppScreen.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(AppScreen.Register.route) {
                        popUpTo(AppScreen.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToMain = {
                    navController.navigate(AppScreen.Main.route) {
                        popUpTo(AppScreen.Splash.route) { inclusive = true }
                    }
                },
            )
        }
        composable(route = AppScreen.Login.route) {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(AppScreen.Register.route)
                },
                onNavigateToMain = {
                    navController.navigate(AppScreen.Main.route)
                }
            )
        }
        composable(route = AppScreen.Register.route) {
            RegisterScreen(
                onNavigateToMain = {
                    navController.navigate(AppScreen.Main.route)
                },
                onNavigateToLogin = {
                    navController.navigate(AppScreen.Login.route)
                }
            )
        }
        composable(route= AppScreen.Otp.route){
            OtpScreen(

            )
        }

        composable(route = AppScreen.Main.route) {
            // TODO: Add MainScreen when created
            // MainScreen()
        }
    }
}
