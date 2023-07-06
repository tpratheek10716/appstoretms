package com.example.appstore.utils

import android.content.Intent
import android.os.Environment
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import java.io.File


/*fun InstallApplication() {
    Log.i("info", "\n Entered in Installed Application !!")
    val intent = Intent(Intent.ACTION_VIEW)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
    Log.i("inof", "\n 2222222222222222222 :: Entered in Installed Application")
    val apkURI = FileProvider.getUriForFile(
        getApplicationContext(), getPackageName() + ".provider", File(
            Environment.getExternalStorageDirectory().toString() + "/" + ApkName.toString()
        )
    )
    Log.i("inof", "\n 333333333333333333333 :: Entered in Installed Application")
    intent.setDataAndType(apkURI, "application/vnd.android.package-archive")
    Log.i(
        "inof", """
 44444444444444444444444444 :: Entered in Installed Application${Environment.getExternalStorageDirectory()}${ApkName.toString()}"""
    )
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    Log.i("inof", "\n 5555555555555555555555555555555555 :: Entered in Installed Application")
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    startActivity(intent)
    Log.i("inof", "\n 6666666666666666666666666666666 :: Entered in Installed Application")
}
https://demo.ezetap.com/ezetap/apps/demo/EZETAP/ANDROID/ALL/1798/ezetap_android_service.apk*/
