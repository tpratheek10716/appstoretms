package com.example.appstore.service

import com.example.appstore.data.LoginRequestData
import com.example.appstore.data.LoginResponseData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("login")
    fun loginRequest(@Body loginRequestData: LoginRequestData): Call<LoginResponseData>
}