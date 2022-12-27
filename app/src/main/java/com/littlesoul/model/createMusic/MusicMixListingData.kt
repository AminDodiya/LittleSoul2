package com.littlesoul.model.createMusic

import com.google.gson.annotations.SerializedName


data class MusicMixListingData(
    var categoryID: Int = 0, // 4
    var description: String = "",
    var image: String = "", // http://webapps.iqlance-demo.com/little_soul/public/images/url/create_your_birds_icn.png
    @SerializedName("sub_category_id")
    var subCategoryId: Int = 0, // 11
    var title: String = "", // Birds
    var url: String = "" // http://webapps.iqlance-demo.com/little_soul/public/images/url/Birds.mp3
)