package com.littlesoul.model.subscriptions

import com.google.gson.annotations.SerializedName


data class SubscriptionData(
    var amount: String = "", // 29.99
    @SerializedName("package_expiry_date")
    var packageExpiryDate: String = "", // 2022-03-05 12:00:00
    @SerializedName("product_id")
    var productId: String = "", // com.littlesoul
    @SerializedName("sku_key")
    var skuKey: String = "",
    var status: String = "", // purchased
    @SerializedName("user_id")
    var userId: String = "" // 205
)