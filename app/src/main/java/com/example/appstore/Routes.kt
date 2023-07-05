package com.example.appstore

sealed class Routes(val route: String) {
    object Login : Routes("Login")
    object AppList : Routes("AppList")
}