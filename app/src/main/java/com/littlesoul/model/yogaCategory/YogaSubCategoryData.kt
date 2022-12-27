package com.littlesoul.model.yogaCategory

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class YogaSubCategoryData(
    var categoryID: Int = 0, // 1
    var description: String = "", // bedtime yoga
    var image: String = "", // http://webapps.iqlance-demo.com/little_soul/public/images/sub_category/img_bedtime_yoga.png
    @SerializedName("sub_category_id")
    var subCategoryId: Int = 0, // 1
    var title: String = "", // bedtime yoga
    var videos: ArrayList<Video> = ArrayList()
) : Parcelable