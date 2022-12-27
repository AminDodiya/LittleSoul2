package com.littlesoul.model.home

import com.google.gson.annotations.SerializedName


data class Category(
    @SerializedName("created_at")
    var createdAt: String = "", // 2020-11-25 03:18:09
    var description: String = "", // Bring your body mind and spirit into balance with these fun classes!
    var id: Int = 0, // 1
    var image: String = "", // http://webapps.iqlance-demo.com/little_soul/public/images/category/yoga_block.png
    var name: String = "", // Yoga
    var status: String = "", // Active
    @SerializedName("updated_at")
    var updatedAt: String = "" // 2020-11-25 03:18:09
)