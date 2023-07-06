package com.example.appstore.screens

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appstore.Routes
import com.example.appstore.viewmodel.LoginViewModel

@Composable
fun ScreenMain(loginViewModel: LoginViewModel,context:Context){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {

        composable(Routes.Login.route) {
            LoginPage(navController = navController, loginViewModel)
        }

        composable(Routes.AppList.route) {
            AppListPage(navController = navController,context)
        }
    }
}