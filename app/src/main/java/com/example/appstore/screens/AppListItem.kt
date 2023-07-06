package com.example.appstore.screens

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.RECEIVER_EXPORTED
import androidx.core.content.ContextCompat.registerReceiver
import com.example.appstore.App
import com.example.appstore.AppListInfo
import com.example.appstore.utils.DownloadController

@Composable
fun AppListItem(apps: AppListInfo,context: Context) {
    registerReceiver(context,onDownloadComplete,
        IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE),RECEIVER_EXPORTED)
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {
        Row (
            horizontalArrangement = Arrangement.End
        ){
            AppImage(apps)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)) {
                Text(text = apps.name, style = typography.h6)
                Text(text = apps.version, style = typography.caption)

            }

            var getButtonVisible by remember { mutableStateOf(true) }
            var pending by remember { mutableStateOf(false) }

            Button(
                onClick = {
                    if (getButtonVisible) {
                        val url =
                            "https://demo.ezetap.com/ezetap/apps/demo/EZETAP/ANDROID/EZEPOS/10/ezetap_android_epos.apk"
                        Toast.makeText(
                            context,
                            "App found $url",
                            Toast.LENGTH_SHORT
                        ).show()

                        pending = true
                        DownloadController(context, url).enqueueDownload()
                        getButtonVisible = false
                    }
                    else
                    {
                        //install app
                        pending = false
                    }
                },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .width(100.dp)
                    .height(50.dp),
                enabled = !pending || !getButtonVisible
            ) {
                if(getButtonVisible)
                    Text(text = "Get")
                else
                    Text(text = "Install")
            }
        }
    }
}

@Composable
private fun AppImage(apps: AppListInfo) {
    Image(
        painter = painterResource(id = apps.logo),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(48.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
    )
}

private val onDownloadComplete: BroadcastReceiver = object : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        //Fetching the download id received with the broadcast
        val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
        //Checking if the received broadcast is for our enqueued download by matching download id
        if (App.downloadID == id) {
            Toast.makeText(context, "Download Completed", Toast.LENGTH_SHORT).show()
        }
    }
}