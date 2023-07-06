package com.example.appstore

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.appstore.App.Companion.context
import com.example.appstore.screens.AppListPage
import com.example.appstore.screens.LoginPage
import com.example.appstore.screens.ScreenMain
import com.example.appstore.ui.theme.AppStoreTheme
import com.example.appstore.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
    val loginViewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppStoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScreenMain(loginViewModel)
                }
            }
        }

        /*loginViewModel.successLoginData().observe(this) {
            Log.e("Response Data", it.toString())
            //navController.navigate(Routes.AppList.route)
        }

        loginViewModel.errorLoginData().observe(this) {
            Log.e("Response Data", it.toString())
        }

        loginViewModel.networkFailureUIData().observe(this) {
            Toast.makeText(
                context,
                "Network failure",
                Toast.LENGTH_SHORT
            ).show()
            Log.e("Response Data", it.toString())
        }*/
    }
}

