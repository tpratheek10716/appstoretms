package com.example.appstore.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class LoginRequestData(
    @SerializedName("username")
    var username: String? = null,
    @SerializedName("password")
    var password: String? = null,
    @SerializedName("deviceSerial")
    var deviceSerial: String? = null,
    @SerializedName("pushToken")
    var pushToken: String? = null
) : Serializable
