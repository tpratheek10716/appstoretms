package com.example.appstore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appstore.data.LoginRequestData
import com.example.appstore.data.LoginResponseData
import com.example.appstore.repository.LoginRepository
import com.example.networkmodule.network.ResponseListener

class LoginViewModel : ViewModel() {

    var loginRepository: LoginRepository? = null

    private var loginSuccessMutableLiveData = MutableLiveData<LoginResponseData>()
    private var loginNetworkFailureMutableLiveData = MutableLiveData<Throwable>()
    private var loginFailureMutableLiveData = MutableLiveData<String>()


    fun doLogin(loginRequestData: LoginRequestData) {
        loginRepository = LoginRepository.getInstance()
        loginRepository!!.doLogin(loginRequestData, object : ResponseListener {
            override fun onSuccess(success: Any?) {
                loginSuccessMutableLiveData.value = success as LoginResponseData
            }

            override fun onNetworkFailure(error: Throwable) {
                loginNetworkFailureMutableLiveData.value = error
            }

            override fun onFailure(parseError: String?) {
                loginFailureMutableLiveData.value = parseError
            }

        })
    }

    fun successLoginData(): LiveData<LoginResponseData> = loginSuccessMutableLiveData

    fun networkFailureUIData(): LiveData<Throwable> = loginNetworkFailureMutableLiveData

    fun errorLoginData(): LiveData<String> = loginFailureMutableLiveData
}