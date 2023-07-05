package com.example.appstore.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitService {

    const val PUBLISH_BASE_URL = "https://sandhya.pagekite.me/api/3.0/appStore/"

    fun getAuthorisedRetrofit(token:String): ApiInterface {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().authenticator(TokenAuthenticator(token)).addInterceptor(loggingInterceptor).build()
        return Retrofit.Builder().baseUrl(PUBLISH_BASE_URL).client(client)
            .addConverterFactory(MoshiConverterFactory.create()).build().create(ApiInterface::class.java)
    }

    fun getLoginRetrofit():ApiInterface{
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
        return Retrofit.Builder().baseUrl(PUBLISH_BASE_URL).client(client)
            .addConverterFactory(MoshiConverterFactory.create()).build().create(ApiInterface::class.java)
    }
}