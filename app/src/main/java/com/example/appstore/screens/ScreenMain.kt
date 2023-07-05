package com.example.appstore.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appstore.Routes
import com.example.appstore.viewmodel.LoginViewModel

@Composable
fun ScreenMain(loginViewModel: LoginViewModel){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {

        composable(Routes.Login.route) {
            LoginPage(navController = navController, loginViewModel)
        }

        composable(Routes.AppList.route) {
            AppListPage(navController = navController)
        }
    }
}