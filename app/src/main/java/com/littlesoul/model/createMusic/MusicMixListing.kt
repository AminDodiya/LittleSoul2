package com.littlesoul.model.createMusic

import com.google.gson.annotations.SerializedName

data class MusicMixListing(
    @SerializedName("bottom_title")
    var bottomTitle: String = "", // Select Sound
    var description: String = "", // Listen to your favorite calming sounds
    var image: String = "", // http://webapps.iqlance-demo.com/little_soul/public/images/sub_category/img_create_music.png
    var list: List<MusicMixListingData> = listOf(),
    @SerializedName("sub_category_id")
    var subCategoryId: Int = 0, // 11
    var title: String = "" // Soothing sounds and mixes
)