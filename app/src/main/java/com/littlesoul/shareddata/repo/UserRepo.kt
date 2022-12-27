package com.littlesoul.shareddata.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonObject
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

import com.littlesoul.ui.login.LoginActivity


interface UserRepo {
    fun getKey(
        body: JsonObject, isinternetConnected: Boolean,
        loading: Boolean, baseView: BaseView,
        callback: MutableLiveData<RequestState<ApiKeyData>>
    )

    fun getLogin(
        loginJson: JsonObject,
        internetConnected: Boolean,
        loading: Boolean,
        baseView: LoginActivity,
        mLDLoginRequest: MutableLiveData<RequestState<SignUpRespData>>
    )



}

