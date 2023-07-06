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
import com.example.appstore.viewmodel.LoginViewModel


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
                    ScreenMain(loginViewModel,this)
                }
            }
        }

        //registerReceiver(onDownloadComplete, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))

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

    /*private val onDownloadComplete: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            //Fetching the download id received with the broadcast
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            //Checking if the received broadcast is for our enqueued download by matching download id
            if (downloadID == id) {
                Toast.makeText(this@MainActivity, "Download Completed", Toast.LENGTH_SHORT).show()
                downloadPending = false
            }
        }
    }*/

}

