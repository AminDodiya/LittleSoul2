package com.littlesoul.model.tracking

import com.google.gson.annotations.SerializedName


data class Goal(
    @SerializedName("category_id")
    var categoryId: Int = 0, // 1
    var id: Int = 0, // 4
    var minutes: Int = 0, // 10
    var name: String = "" // Yoga
)