package com.littlesoul.model.guest

import com.google.gson.annotations.SerializedName


data class Apikey(
    var apiKey: String = "", // 9074e068cb4c0856dafc4b0eb9c441d5778de518
    var apiKeyID: Int = 0, // 491
    @SerializedName("created_at")
    var createdAt: String = "", // 2021-02-25 08:41:37
    var role: String = "", // User
    @SerializedName("updated_at")
    var updatedAt: String = "" // 2021-02-25 08:41:37
)