package com.cashcove.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cashcove.app.splash.SplashScreen
import com.cashcove.features.authentication.data.navigation.AuthenticationNavGraph
@Composable
fun SplashNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Splash.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Splash.route) {
            SplashScreen(
                onNavigateToAuthentication = {
                    navController.navigate(Screen.Authentication.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToMain = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
            )
        }

        composable(route = Screen.Authentication.route) {
            AuthenticationNavGraph(navController)
        }

        composable(route = Screen.Main.route) {
            // TODO: Add MainScreen when created
            // MainScreen()
        }
    }
}
