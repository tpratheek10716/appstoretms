package com.example.appstore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appstore.data.LoginRequestData
import com.example.appstore.data.LoginResponseData
import com.example.appstore.repository.LoginRepository
import com.example.appstore.service.ResponseListenerState
import com.example.networkmodule.network.ResponseListener
import kotlinx.coroutines.flow.MutableStateFlow

class LoginViewModel : ViewModel() {

    var loginRepository: LoginRepository? = null

    private var loginSuccessMutableLiveData = MutableLiveData<LoginResponseData>()
    private var loginNetworkFailureMutableLiveData = MutableLiveData<String>()
    private var loginFailureMutableLiveData = MutableLiveData<String>()

    val loginState = MutableStateFlow<ResponseListenerState>(ResponseListenerState.Empty)


    fun doLogin(loginRequestData: LoginRequestData) {
        loginRepository = LoginRepository.getInstance()
        loginState.value = ResponseListenerState.Start
        loginRepository!!.doLogin(loginRequestData, object : ResponseListener {
            override fun onSuccess(success: Any?) {
                loginSuccessMutableLiveData.value = success as LoginResponseData
                loginState.value = ResponseListenerState.onSuccess(success as LoginResponseData)
            }

            override fun onNetworkFailure(error: String) {
                loginNetworkFailureMutableLiveData.value = error
                loginState.value = ResponseListenerState.onNetworkError(error)
            }

            override fun onFailure(parseError: String?) {
                loginFailureMutableLiveData.value = parseError
                if (parseError != null) {
                    loginState!!.value = ResponseListenerState.onFailure(parseError)
                }
            }

        })
    }

    fun successLoginData(): LiveData<LoginResponseData> = loginSuccessMutableLiveData

    fun networkFailureUIData(): LiveData<String> = loginNetworkFailureMutableLiveData

    fun errorLoginData(): LiveData<String> = loginFailureMutableLiveData
}