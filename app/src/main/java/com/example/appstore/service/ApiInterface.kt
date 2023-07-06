package com.example.appstore.service

import com.example.appstore.data.AppListResponseData
import com.example.appstore.data.GetAppListRequestData
import com.example.appstore.data.LoginRequestData
import com.example.appstore.data.LoginResponseData
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @POST("login")
    fun loginRequest(@Body loginRequestData: LoginRequestData): Call<LoginResponseData>

    @POST("app-versions")
    fun getAllAppList(@Body getAppListRequestData: GetAppListRequestData):Call<AppListResponseData>
}