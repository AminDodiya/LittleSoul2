package com.littlesoul.model.guest

import com.google.gson.annotations.SerializedName

data class GuestData(
    var accesstoken: Any? = null, // null
    var apikey: Apikey = Apikey(),
    @SerializedName("customer_id")
    var customerId: Long = 0 // 2899686807
)