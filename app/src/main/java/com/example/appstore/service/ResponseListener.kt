package com.example.networkmodule.network

interface ResponseListener {
    fun onSuccess(success: Any?)
    fun onNetworkFailure(error: String)
    fun onFailure(parseError: String?)
}