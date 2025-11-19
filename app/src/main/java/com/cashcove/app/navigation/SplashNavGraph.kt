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
    startDestination: String = AppScreen.Splash.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = AppScreen.Splash.route) {
            SplashScreen(
                onNavigateToAuthentication = {
                    navController.navigate(AppScreen.Authentication.route) {
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

        composable(route = AppScreen.Authentication.route) {
            AuthenticationNavGraph(navController)
        }

        composable(route = AppScreen.Main.route) {
            // TODO: Add MainScreen when created
            // MainScreen()
        }
    }
}
