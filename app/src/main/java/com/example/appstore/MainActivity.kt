package com.example.appstore

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.appstore.screens.ScreenMain
import com.example.appstore.ui.theme.AppStoreTheme
import com.example.appstore.viewmodel.GetAppListViewModel
import com.example.appstore.viewmodel.LoginViewModel
import com.google.firebase.messaging.FirebaseMessaging


class MainActivity : ComponentActivity() {
    val loginViewModel by viewModels<LoginViewModel>()

    val getAppListViewModel by viewModels<GetAppListViewModel>()
    private fun initFirebaseDeviceToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                Log.e("Token", token)
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppStoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScreenMain(loginViewModel, this)
                }
            }
        }
        initFirebaseDeviceToken()
    }
}

