package com.example.appstore.utils

import com.example.appstore.data.LoginRequestData

fun getLoginReqData() : LoginRequestData {
    val loginRequestData = LoginRequestData()
    loginRequestData.deviceSerial = "010001009702"
    loginRequestData.username = "0000000000"
    loginRequestData.password = "1023qpr"
    loginRequestData.pushToken = "123444444"
    return loginRequestData
}