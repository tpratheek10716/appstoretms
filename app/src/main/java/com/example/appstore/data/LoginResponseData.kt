package com.example.appstore.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponseData(
    @SerializedName("orgCode")
    var orgCode: String? = null,
    @SerializedName("token")
    var token: String? = null,
    @SerializedName("success")
    var success: Boolean = false,
    @SerializedName("errorCode")
    var errorCode: String? = null,
    @SerializedName("username")
    var username: String? = null
) : Serializable
