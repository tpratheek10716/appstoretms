package com.example.appstore.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.appstore.R
import com.example.appstore.utils.Utils
import com.example.appstore.utils.Utils.Companion.getDeviceId
import com.example.appstore.viewmodel.GetAppListViewModel
import com.example.appstore.viewmodel.LoginViewModel

class LoginMainActivity : AppCompatActivity() {

    lateinit var mLoginViewModel: LoginViewModel

    lateinit var mGetAppListViewModel: GetAppListViewModel

    val PERMISSION_REQUEST_CODE = 1001

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_main)

        /* mLoginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
         mGetAppListViewModel = ViewModelProvider(this)[GetAppListViewModel::class.java]
         val getAllAppListRequestData = GetAppListRequestData()
         getAllAppListRequestData.deviceSerial = "010001009702"
         getAllAppListRequestData.orgCode = "EZETAP"
         getAllAppListRequestData.username = "0000000000"
         getAllAppListRequestData.password = "1023qpr"

         mGetAppListViewModel.getAppList(getAllAppListRequestData)

         mGetAppListViewModel.successAppListData().observe(this) {
             Log.e("Response Data", it.toString())
         }

         mGetAppListViewModel.networkFailureAppListData().observe(this) {
             Log.e("Response Data", it.toString())
         }

         mGetAppListViewModel.errorAppListData().observe(this) {
             Log.e("Response Data", it.toString())
         }


         val loginRequestData = LoginRequestData()
         loginRequestData.deviceSerial = "010001009702"
         loginRequestData.username = "0000000000"
         loginRequestData.password = "1023qpr"
         loginRequestData.pushToken = "123444444"

 //        mLoginViewModel.doLogin(loginRequestData)
         Log.e("Api Calling", "Loading")

         mLoginViewModel.successLoginData().observe(this) {
             Log.e("Response Data", it.toString())
         }

         mLoginViewModel.errorLoginData().observe(this) {
             Log.e("Response Data", it.toString())
         }

         mLoginViewModel.networkFailureUIData().observe(this) {
             Log.e("Response Data", it.toString())
         }*/
        val button: Button = findViewById(R.id.buttontext)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_PHONE_STATE),
                PERMISSION_REQUEST_CODE
            )
        } else {
            val deviceId = getDeviceId(this)
            Log.e("deviceId",deviceId)
        }
        button.setOnClickListener {
            Utils.openInstalledApplication(this, "com.ezetap.p2pstandaloneapp", "com.ezetap.p2pstandaloneapp.P2PMainActivity")
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val deviceId = getDeviceId(this)
                Log.e("deviceId",deviceId)
            } else {
              Toast.makeText(this,"Permission Denied",Toast.LENGTH_LONG).show()
            }
        }
    }
}