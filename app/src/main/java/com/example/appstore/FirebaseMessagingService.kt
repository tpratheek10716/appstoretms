package com.example.appstore

import android.R
import android.R.attr.text
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.appstore.App.Companion.context
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.Random


class FirebaseMessagingService : FirebaseMessagingService() {
    var title: String? = null
    var message: String? = null
    var status: String? = null
    var externalReferenceNumer: String? = null
    lateinit var pendingIntent: PendingIntent

    lateinit var notificationBuilder: NotificationCompat.Builder
    var channelId = 100
    lateinit var notificationManager: NotificationManager

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Log.e(
            "data",
            "${remoteMessage.notification!!.title}---${remoteMessage.notification!!.body}---${remoteMessage.data}----"
        )

        title = remoteMessage.notification!!.title
        message = remoteMessage.notification!!.body
        channelId = generateRandom()
        showNotification(title!!,message!!)

    }

    private fun broadCast(
        title: String,
        message: String,
        type: String,
        status: String?,
        refNumber: String?
    ) {
    }

    fun generateRandom(): Int {
        val random = Random()
        return random.nextInt(9999 - 1000) + 1000
    }

    fun showNotification(title: String, message: String) {
        // Pass the intent to switch to the MainActivity
        val intent = Intent(this, MainActivity::class.java)
        // Assign channel ID
        val channel_id = "notification_channel"
        // Here FLAG_ACTIVITY_CLEAR_TOP flag is set to clear
        // the activities present in the activity stack,
        // on the top of the Activity that is to be launched
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        // Pass the intent to PendingIntent to start the
        // next Activity
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        // Create a Builder object using NotificationCompat
        // class. This will allow control over all the flags
        val builder = NotificationCompat.Builder(this, channel_id)
            .setSmallIcon(R.drawable.picture_frame)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)

        // A customized design for the notification can be
        // set only for Android versions 4.1 and above. Thus
        // condition for the same is checked here.

            builder.setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.picture_frame)


        // Create an object of NotificationManager class to
        // notify the user of events that happen in the background.
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Check if the Android Version is greater than Oreo
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channel_id, "web_app",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }

        notificationManager.notify(0, builder.build())
    }


}