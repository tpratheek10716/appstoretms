package com.example.appstore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appstore.data.AppListResponseData
import com.example.appstore.data.GetAppListRequestData
import com.example.appstore.data.LoginResponseData
import com.example.appstore.repository.AppListRepository
import com.example.appstore.service.ResponseListenerState
import com.example.networkmodule.network.ResponseListener
import kotlinx.coroutines.flow.MutableStateFlow

class GetAppListViewModel:ViewModel() {

    var getAppListRepository:AppListRepository?=null

    private var getAppListSuccessMutableLiveData = MutableLiveData<AppListResponseData>()
    private var getAppListNetworkFailureMutableLiveData = MutableLiveData<String>()
    private var getAppListFailureMutableLiveData = MutableLiveData<String>()

    val getAppListState = MutableStateFlow<ResponseListenerState>(ResponseListenerState.Empty)


    fun getAppList(getAppListRequestData: GetAppListRequestData){
        getAppListRepository = AppListRepository.getInstance()
        getAppListState.value = ResponseListenerState.Start
        getAppListRepository!!.getAppList(getAppListRequestData,object :ResponseListener{
            override fun onSuccess(success: Any?) {
                getAppListSuccessMutableLiveData.value = success as AppListResponseData
                getAppListState.value = ResponseListenerState.onSuccess(success as AppListResponseData)
            }

            override fun onNetworkFailure(error: String) {
                getAppListNetworkFailureMutableLiveData.value = error
                getAppListState.value = ResponseListenerState.onNetworkError(error)
            }

            override fun onFailure(parseError: String?) {
                getAppListFailureMutableLiveData.value = parseError
                getAppListState.value = ResponseListenerState.onFailure(parseError)
            }
        })
    }


    fun successAppListData(): LiveData<AppListResponseData> = getAppListSuccessMutableLiveData

    fun networkFailureAppListData(): LiveData<String> = getAppListNetworkFailureMutableLiveData

    fun errorAppListData(): LiveData<String> = getAppListFailureMutableLiveData

}