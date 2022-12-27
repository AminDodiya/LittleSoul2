package com.littlesoul.ui.login.apiModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.littlesoul.model.CommonResponse
import com.littlesoul.model.RequestState
import com.littlesoul.model.apiKey.ApiKeyData
import com.littlesoul.model.cms.CMSData
import com.littlesoul.model.guest.GuestData
import com.littlesoul.model.signup.SignUpRespData
import com.littlesoul.shareddata.base.BaseActivity
import com.littlesoul.shareddata.base.BaseView
import com.littlesoul.shareddata.repo.UserRepo
import com.littlesoul.ui.login.LoginActivity



class LoginApiModel(private val mUserRepository: UserRepo) : ViewModel() {
    private val mLDLoginRequest = MutableLiveData<RequestState<SignUpRespData>>()
    fun getLoginRequest(): LiveData<RequestState<SignUpRespData>> = mLDLoginRequest



    private val mLDKeyRequest = MutableLiveData<RequestState<ApiKeyData>>()
    fun getKeyRequest(): LiveData<RequestState<ApiKeyData>> = mLDKeyRequest






    fun getLogin(
        isInternetConnected: Boolean,
        loading: Boolean,
        baseView: LoginActivity,
        etNameEmail: String,
        etPassword: String,
        deviceToken: String?
    ) {
        val loginJson = JsonObject()
        loginJson.addProperty("email", etNameEmail)
        loginJson.addProperty("password", etPassword)
        loginJson.addProperty("deviceToken", deviceToken)
        mUserRepository.getLogin(
            loginJson,
            isInternetConnected,
            loading,
            baseView,
            mLDLoginRequest
        )
    }







    fun getKey(
        isInternetConnected: Boolean,
        loading: Boolean,
        baseView: BaseActivity
    ) {
        val key = JsonObject()
        mUserRepository.getKey(
            key,
            isInternetConnected,
            loading,
            baseView,
            mLDKeyRequest
        )
    }




}