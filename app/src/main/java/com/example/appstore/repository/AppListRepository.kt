package com.example.appstore.repository

import com.example.appstore.data.AppListResponseData
import com.example.appstore.data.GetAppListRequestData
import com.example.appstore.service.RetrofitService
import com.example.networkmodule.network.ResponseListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppListRepository {

    companion object{
        private var appListRepository:AppListRepository?=null

        fun getInstance():AppListRepository{
            if (appListRepository==null){
                appListRepository = AppListRepository()
            }
            return appListRepository!!
        }
    }


    fun getAppList(appListRequestData: GetAppListRequestData,listener:ResponseListener){
        RetrofitService.getLoginRetrofit().getAllAppList(appListRequestData).enqueue(object :Callback<AppListResponseData>{
            override fun onResponse(
                call: Call<AppListResponseData>,
                response: Response<AppListResponseData>
            ) {
                if (response.isSuccessful){
                    val appListResponseData = response.body()
                    if (appListResponseData!=null){
                        if (appListResponseData.success){
                            listener.onSuccess(appListResponseData)
                        }else{
                            listener.onFailure(appListResponseData.errorCode)
                        }
                    }
                }else{
                    listener.onFailure("${response.code()}: Network failure")
                }
            }

            override fun onFailure(call: Call<AppListResponseData>, t: Throwable) {
                listener.onNetworkFailure(t.message!!)
            }

        })
    }
}