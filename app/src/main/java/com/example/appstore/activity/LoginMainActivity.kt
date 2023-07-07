package com.example.appstore.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.appstore.R
import com.example.appstore.utils.Utils.Companion.getDeviceId
import com.example.appstore.viewmodel.GetAppListViewModel
import com.example.appstore.viewmodel.LoginViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LoginMainActivity : AppCompatActivity() {

    lateinit var mLoginViewModel: LoginViewModel

    lateinit var mGetAppListViewModel: GetAppListViewModel

    val PERMISSION_REQUEST_CODE = 1001

    lateinit var username: EditText
    lateinit var password: EditText
    lateinit var loginButton: Button
    lateinit var progressBar: ProgressBar

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
//        val button: Button = findViewById(R.id.buttontext)
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
            Log.e("deviceId", deviceId)
        }
        initViews()

//        button.setOnClickListener {
//            Utils.openInstalledApplication(this, "com.ezetap.p2pstandaloneapp", "com.ezetap.p2pstandaloneapp.P2PMainActivity")
//        }

    }

    private fun initViews() {
        username = findViewById(R.id.usernameEditText)
        password = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        progressBar = findViewById(R.id.progressBar)
        loginButton.setOnClickListener {
            if (username.text.isNullOrEmpty()) {
                Toast.makeText(this, "Please enter username", Toast.LENGTH_LONG).show()
            } else if (password.text.isNullOrEmpty()) {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show()
            } else if (username.text.length < 10) {
                Toast.makeText(this, "Please enter valid username", Toast.LENGTH_LONG).show()
            } else if (password.text.length < 3) {
                Toast.makeText(this, "Please enter valid password", Toast.LENGTH_LONG).show()
            } else if (username.text.toString() == "8123369463" || username.text.toString() == "8741074557") {
                progressBar.visibility = View.VISIBLE
                loginButton.isEnabled = false
                GlobalScope.launch {
                    delay(500)
                    startActivity(Intent(this@LoginMainActivity, AppListActivity::class.java))
                    finish()
                }
            } else {
                Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_LONG).show()
            }
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
                Log.e("deviceId", deviceId)
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show()
            }
        }
    }
}