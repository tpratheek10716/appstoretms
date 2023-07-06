package com.example.appstore

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.appstore.screens.ScreenMain
import com.example.appstore.ui.theme.AppStoreTheme
import com.example.appstore.utils.Utils
import com.example.appstore.viewmodel.GetAppListViewModel
import com.example.appstore.viewmodel.LoginViewModel


class MainActivity : ComponentActivity() {
    val loginViewModel by viewModels<LoginViewModel>()

    val getAppListViewModel by viewModels<GetAppListViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppStoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScreenMain(loginViewModel,this)
                }
            }
        }

    }
}

