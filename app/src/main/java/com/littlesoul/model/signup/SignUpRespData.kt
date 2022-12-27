package com.littlesoul.model.signup

import com.google.gson.annotations.SerializedName


data class SignUpRespData(
    val accesstoken: String,
    val apikey: String,
    val child_age: Int,
    val child_firstname: String,
    val child_lastname: String,
    val otp: Int,
    val parent_email: String,
    val parent_firstname: String,
    val parent_lastname: String,
    val parent_phonecode: Int,
    val parent_phonenumber: Long,
    val reference: String,
    val userID: Int,
    val verified: String,
    @SerializedName("is_notification_enable")
    val is_notification_enable: String,
    @SerializedName("is_wifi_download_enable")
    val is_wifi_download_enable: String,
    @SerializedName("aws_access_key")
    val aws_access_key: String,
    @SerializedName("aws_secret_key")
    val aws_secret_key: String
)
