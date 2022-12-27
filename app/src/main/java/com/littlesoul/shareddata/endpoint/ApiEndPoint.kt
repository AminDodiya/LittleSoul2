package com.littlesoul.shareddata.endpoint

import com.google.gson.JsonObject
import com.littlesoul.model.CommonResponse
import com.littlesoul.model.CommonResponseModel
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
import retrofit2.Call
import retrofit2.http.*


interface ApiEndPoint {
    @GET("api/apikey")
    fun getKey(
    ): Call<CommonResponseModel<ApiKeyData>>

    @GET("api/proflle_detail")
    fun getProfile(
    ): Call<CommonResponseModel<SignUpRespData>>

    @GET("api/meditionrecordingtips")
    fun getRecordingTips(
    ): Call<CommonResponseModel<RecordingTipsData>>

    @GET("api/skiplogin")
    fun doGuestLogin(
    ): Call<CommonResponseModel<GuestData>>

    @POST("api/cmspages")
    fun getCMS(
        @Body
        body: JsonObject
    ): Call<CommonResponseModel<CMSData>>

    @POST("api/uplodeAudio")
    fun uploadAudio(
        @Body
        body: JsonObject
    ): Call<CommonResponseModel<RecordingTipsData>>

    @POST("api/inAppPurchase")
    fun doPay(
        @Body
        body: JsonObject
    ): Call<CommonResponseModel<SubscriptionData>>

    @POST("api/login")
    fun doLogin(
        @Body
        body: JsonObject
    ): Call<CommonResponseModel<SignUpRespData>>

    @POST("api/signup")
    fun userSignUp(@Body body: JsonObject): Call<CommonResponseModel<SignUpRespData>>

    @POST("api/forgetPassword")
    fun userForgotPassword(@Body body: JsonObject): Call<CommonResponseModel<CommonResponse>>

    @POST("api/verify")
    fun userOtpVerify(@Body body: JsonObject): Call<CommonResponseModel<SignUpRespData>>

    @POST("api/resendOTP")
    fun userOtpResend(@Body body: JsonObject): Call<CommonResponseModel<SignUpRespData>>

    @POST("api/home")
    fun getHome(
    ): Call<CommonResponseModel<HomeCategoryData>>

    @POST("api/subCategoryList")
    fun getYogaCategory(
        @Body body: JsonObject
    ): Call<CommonResponseModel<ArrayList<YogaSubCategoryData>>>

    @POST("api/mindfulness_detail")
    fun getMeditationDetails(
        @Body body: JsonObject
    ): Call<CommonResponseModel<MeditationData>>

    @POST("api/mixmusicListing")
    fun getMusicDetails(
        @Body body: JsonObject
    ): Call<CommonResponseModel<MeditationData>>

    @POST("api/mixmusic")
    fun getMusicMix(
        @Body body: JsonObject
    ): Call<CommonResponseModel<MeditationData>>

    @POST("api/editProfile")
    fun updateProfile(@Body body: JsonObject): Call<CommonResponseModel<SignUpRespData>>

    @GET("api/logout")
    fun doLogout(
    ): Call<CommonResponseModel<ApiKeyData>>

    @POST("api/tracking")
    fun getTracking(
        @Body
        body: JsonObject
    ): Call<CommonResponseModel<TrackingData>>

    @POST("api/setGoal")
    fun setGoal(
        @Body
        body: JsonObject
    ): Call<CommonResponseModel<TrackingData>>

    @POST("api/videoDownload")
    fun doVideoDownload(
        @Body
        body: JsonObject
    ): Call<CommonResponseModel<YogaSubCategoryData>>


    @POST("api/stopTime")
    fun updateTime(
        @Body
        body: JsonObject
    ): Call<CommonResponseModel<TrackingData>>

    @FormUrlEncoded
    @POST("api/offlineStopTime")
    fun offlineTrack(
        @Field("offline_videos") JsonObject: JsonObject
    ): Call<CommonResponseModel<TrackingData>>
}