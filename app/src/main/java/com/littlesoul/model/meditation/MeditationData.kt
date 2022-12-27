package com.littlesoul.model.meditation

import com.google.gson.annotations.SerializedName

data class MeditationData(
    var description: String = "", // Listen and go on a journey in your mind
    var image: String = "", // http://webapps.iqlance-demo.com/little_soul/public/images/sub_category/img_meditation.png
    var list: List<MeditationList> = listOf(),
    @SerializedName("sub_category_id")
    var subCategoryId: Int = 0, // 4
    var title: String = "", // Meditation
    var bottom_title: String = "" // Meditation
)