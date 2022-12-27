package com.littlesoul.model.home

import com.google.gson.annotations.SerializedName


data class User(
    @SerializedName("child_age")
    var childAge: Int = 0, // 8
    @SerializedName("child_firstname")
    var childFirstname: String = "", // Tarryn
    @SerializedName("child_lastname")
    var childLastname: String = "", // dodiya
    @SerializedName("is_expierd")
    var isExpierd: Int = 0, // 0
    @SerializedName("is_purchased")
    var isPurchased: Int = 0, // 0
    @SerializedName("is_Loved_purchased")
    var is_Loved_purchased: Int = 0, // 0
    @SerializedName("parent_email")
    var parentEmail: String = "", // amin.iqlance@gmail.com
    @SerializedName("parent_firstname")
    var parentFirstname: String = "", // amin
    @SerializedName("parent_lastname")
    var parentLastname: String = "", // Dodiya
    @SerializedName("parent_phonecode")
    var parentPhonecode: Int = 0, // 91
    @SerializedName("parent_phonenumber")
    var parentPhonenumber: Long = 0, // 9714000854
    var reference: String = "",
    var userID: Int = 0, // 205
    var verified: String = "" // 1
)