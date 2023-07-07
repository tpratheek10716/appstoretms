package com.example.appstore.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AppListResponseData(
    @SerializedName("success")
    var success: Boolean = false,
    @SerializedName("appDetails")
    var appDetails: ArrayList<AppDetailsListing>? = null,
    @SerializedName("orgCode")
    var orgCode: String? = null,
    @SerializedName("deviceSerial")
    var deviceSerial: String? = null,
    @SerializedName("errorCode")
    var errorCode: String? = null
) : Serializable


data class AppDetailsListing(
    @SerializedName("applicationId")
    var applicationId: String? = null,
    @SerializedName("versionCode")
    var versionCode: String? = null,
    @SerializedName("versionName")
    var versionName: String? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("appUrl")
    var appUrl: String? = null,
    @SerializedName("packageName")
    var packageName: String? = null,
    @SerializedName("appName")
    var appName: String? = null,
    @SerializedName("launcherPackageName")
    var launcherPackageName: String? = null
) : Serializable
