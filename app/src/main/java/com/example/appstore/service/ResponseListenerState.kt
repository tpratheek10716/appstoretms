package com.example.appstore.service

sealed class ResponseListenerState{
    object Empty:ResponseListenerState()
    object Start:ResponseListenerState()
    object Loading: ResponseListenerState()
    class onSuccess(success: Any?) : ResponseListenerState()
    class onFailure(parseError:String?):ResponseListenerState()
    class onNetworkError(parseError: String?):ResponseListenerState()
}
