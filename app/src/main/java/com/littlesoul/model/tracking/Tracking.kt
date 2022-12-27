package com.littlesoul.model.tracking

import com.google.gson.annotations.SerializedName


data class Tracking(
    var categoryID: Int = 0, // 1
    @SerializedName("category_name")
    var categoryName: String = "", // Yoga
    var minutes: Int = 0 // 70
)