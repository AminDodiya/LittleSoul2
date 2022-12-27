package com.littlesoul.shareddata.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
import com.littlesoul.common.utils.Config
import com.littlesoul.model.ApiError
import com.littlesoul.model.CommonResponse
import com.littlesoul.model.RequestState
import com.littlesoul.model.apiKey.ApiKeyData
import com.littlesoul.model.cms.CMSData
import com.littlesoul.model.guest.GuestData
import com.littlesoul.model.home.HomeCategoryData
import com.littlesoul.model.meditation.MeditationData
import com.littlesoul.model.signup.SignUpRespData
import com.littlesoul.model.subscriptions.SubscriptionData
import com.littlesoul.model.tips.RecordingTipsData
import com.littlesoul.model.tracking.TrackingData
import com.littlesoul.model.yogaCategory.YogaSubCategoryData
import com.littlesoul.shareddata.base.BaseActivity
import com.littlesoul.shareddata.base.BaseView
import com.littlesoul.shareddata.endpoint.ApiEndPoint
import com.littlesoul.shareddata.networkmanager.NetworkManager

import com.littlesoul.ui.login.LoginActivity


class UserRepository(private val mApiEndPoint: ApiEndPoint) : UserRepo {
    override fun getKey(
        body: JsonObject,
        isinternetConnected: Boolean,
        loading: Boolean,
        baseView: BaseView,
        callback: MutableLiveData<RequestState<ApiKeyData>>
    ) {
        if (!isinternetConnected) {
            callback.value =
                RequestState(progress = false, error = ApiError(Config.NETWORK_ERROR, null))
        } else {
            callback.value = RequestState(progress = true)
            NetworkManager.requestData(
                mApiEndPoint.getKey(),
                baseView, callback
            )
        }
    }

    override fun getLogin(
        loginJson: JsonObject,
        internetConnected: Boolean,
        loading: Boolean,
        baseView: LoginActivity,
        mLDLoginRequest: MutableLiveData<RequestState<SignUpRespData>>
    ) {
        if (!internetConnected) {
            mLDLoginRequest.value =
                RequestState(progress = false, error = ApiError(Config.NETWORK_ERROR, null))
        } else {
            mLDLoginRequest.value = RequestState(progress = true)
            NetworkManager.requestData(
                mApiEndPoint.doLogin(loginJson),
                baseView, mLDLoginRequest
            )
        }
    }




}

