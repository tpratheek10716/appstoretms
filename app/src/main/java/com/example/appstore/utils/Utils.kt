package com.example.appstore.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import androidx.core.content.ContextCompat

class Utils {

    companion object {

        fun openInstalledApplication(activity: Activity, packageName: String,launcherPackageName:String) {
            val intent = Intent()
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.component = ComponentName(packageName, launcherPackageName)
            activity.startActivity(intent)
        }

        fun hasReadPhoneStatePermission(context: Context): Boolean {
            return ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_PHONE_STATE
            ) == PackageManager.PERMISSION_GRANTED
        }

        @SuppressLint("HardwareIds")
        fun getDeviceId(context: Context): String {
            if (hasReadPhoneStatePermission(context)) {
                val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

                // For devices with Android 10 and above, use the getImei() method
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    try {
                        val imei = telephonyManager.imei
                        if (!imei.isNullOrBlank()) {
                            return imei
                        }
                    } catch (e: SecurityException) {
                        // IMEI access restricted, fallback to Android ID
                    }
                }

                // For devices with Android 9 and below, use the deprecated getDeviceId() method
                try {
                    val deviceId = telephonyManager.deviceId
                    if (!deviceId.isNullOrBlank()) {
                        return deviceId
                    }
                } catch (e: SecurityException) {
                    // IMEI access restricted, fallback to Android ID
                }
            }

            // If permission is not granted or IMEI access is restricted, use the Android ID as fallback
            return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        }

        fun isAppInstalled(packageName: String, context: Context): Boolean {
            val packageManager = context.packageManager
            return try {
                packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
                true
            } catch (e: PackageManager.NameNotFoundException) {
                false
            }
        }


    }
}