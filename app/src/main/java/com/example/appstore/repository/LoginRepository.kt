package com.example.appstore.repository

import android.util.Log
import com.example.appstore.data.LoginRequestData
import com.example.appstore.data.LoginResponseData
import com.example.appstore.service.RetrofitService
import com.example.networkmodule.network.ResponseListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository {

    companion object {
        private var loginRepository: LoginRepository? = null
        fun getInstance(): LoginRepository {
            if (loginRepository == null) {
                loginRepository = LoginRepository()
            }
            return loginRepository!!
        }
    }


    fun doLogin(loginRequestData: LoginRequestData, listener: ResponseListener) {
        RetrofitService.getLoginRetrofit().loginRequest(loginRequestData)
            .enqueue(object : Callback<LoginResponseData> {
                override fun onResponse(
                    call: Call<LoginResponseData>,
                    response: Response<LoginResponseData>
                ) {

                    val loginResponseData = response.body()
                    if (!response.isSuccessful) {
                        Log.e("success", "network fail")
                        listener.onNetworkFailure("Network error")
                    } else if (loginResponseData != null) {
                        Log.e("success", "succes")
                        if (loginResponseData!!.success) {
                            listener.onSuccess(loginResponseData)
                        } else {
                            Log.e("success", "failure")
                            listener.onFailure(loginResponseData.errorCode)
                        }
                    }

                }

                override fun onFailure(call: Call<LoginResponseData>, t: Throwable) {
                    Log.e("failure", "fail")
                    if (t.message != null)
                        listener.onNetworkFailure(t.message!!)
                    else
                        listener.onNetworkFailure("Network Error")
                }

            })
    }
}