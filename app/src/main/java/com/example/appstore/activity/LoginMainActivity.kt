package com.example.appstore.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.appstore.R
import com.example.appstore.data.GetAppListRequestData
import com.example.appstore.data.LoginRequestData
import com.example.appstore.viewmodel.GetAppListViewModel
import com.example.appstore.viewmodel.LoginViewModel

class LoginMainActivity : AppCompatActivity() {

    lateinit var mLoginViewModel: LoginViewModel

    lateinit var mGetAppListViewModel: GetAppListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_main)
        mLoginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
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
        }

    }
}