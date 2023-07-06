package com.example.appstore.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GetAppListRequestData(
    @SerializedName("deviceSerial")
    var deviceSerial: String? = null,
    @SerializedName("orgCode")
    var orgCode: String? = null,
    @SerializedName("username")
    var username: String? = null,
    @SerializedName("password")
    var password: String? = null
) : Serializable
