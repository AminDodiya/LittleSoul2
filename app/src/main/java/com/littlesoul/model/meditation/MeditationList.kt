package com.littlesoul.model.meditation

import com.google.gson.annotations.SerializedName


data class MeditationList(
    var categoryID: Int = 0, // 2
    var description: String = "",
    var image: String = "", // http://webapps.iqlance-demo.com/little_soul/public/images/url/img_happy_island.png
    var image2: String = "",
    @SerializedName("sub_category_id")
    var subCategoryId: Int = 0, // 4
    var title: String = "", // happy island
    var url: String = "" // http://webapps.iqlance-demo.com/little_soul/public/images/url/meditation.mp3
)