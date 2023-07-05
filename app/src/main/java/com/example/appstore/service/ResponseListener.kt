package com.example.networkmodule.network

interface ResponseListener {
    fun onSuccess(success: Any?)
    fun onNetworkFailure(error: Throwable)
    fun onFailure(parseError: String?)
}