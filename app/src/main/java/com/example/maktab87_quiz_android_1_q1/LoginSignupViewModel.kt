package com.example.maktab87_quiz_android_1_q1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginSignupViewModel : ViewModel() {
    private var _loginUserName = MutableLiveData("")
    val loginUserName: LiveData<String> = _loginUserName
    private var _loginPassword = MutableLiveData("")
    val loginPassword: LiveData<String> = _loginPassword
    private var _signUpUserName = MutableLiveData("")
    val signUpUserName: LiveData<String> = _signUpUserName
    private var _signUpPassword = MutableLiveData("")
    val signUpPassword: LiveData<String> = _signUpPassword

    fun setLoginUserName(s: String) {
        _loginUserName.value = s
    }

    fun setLoginPassword(s: String) {
        _loginPassword.value = s
    }

    fun setSignUpUserName(s: String) {
        _signUpUserName.value = s
    }

    fun setSignUpPassword(s: String) {
        _signUpPassword.value = s
    }
}