package com.example.appstore.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    const val PUBLISH_BASE_URL = "https://1cdf-2405-201-c00d-9038-72a3-b847-6485-5dee.ngrok-free.app/api/3.0/razorpay-app-store/"

    fun getAuthorisedRetrofit(token:String): ApiInterface {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().authenticator(TokenAuthenticator(token)).addInterceptor(loggingInterceptor).build()
        return Retrofit.Builder().baseUrl(PUBLISH_BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiInterface::class.java)
    }

    fun getLoginRetrofit():ApiInterface{
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        return Retrofit.Builder().baseUrl(PUBLISH_BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiInterface::class.java)
    }
}