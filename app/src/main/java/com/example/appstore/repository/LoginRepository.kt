package com.example.appstore.repository

import com.example.appstore.data.LoginRequestData
import com.example.appstore.data.LoginResponseData
import com.example.appstore.service.RetrofitService
import com.example.networkmodule.network.ResponseListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository {

    companion object{
        private var loginRepository:LoginRepository?=null
        fun getInstance():LoginRepository{
            if (loginRepository==null){
                loginRepository = LoginRepository()
            }
            return loginRepository!!
        }
    }


    fun doLogin(loginRequestData: LoginRequestData,listener: ResponseListener){
        RetrofitService.getLoginRetrofit().loginRequest(loginRequestData).enqueue(object :Callback<LoginResponseData>{
            override fun onResponse(
                call: Call<LoginResponseData>,
                response: Response<LoginResponseData>
            ) {
                val loginResponseData = response.body()
                if (loginResponseData!=null){
                    if (loginResponseData!!.success){
                        listener.onSuccess(loginResponseData)
                    }else{
                        listener.onFailure(loginResponseData.errorCode)
                    }
                }
            }

                override fun onFailure(call: Call<LoginResponseData>, t: Throwable) {
                    if (t.message != null)
                        listener.onNetworkFailure(t.message!!)
                    else
                        listener.onNetworkFailure("Network Error")
                }

        })
    }
}