package com.littlesoul.model.yogaCategory

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Video(
    var categoryID: Int = 0, // 1
    var id: Int = 0, // 1
    var image: String = "",
    var title: String = "",
    var audiourl: String = "",
    var description: String? = null,
    @SerializedName("sub_category_id")
    var subCategoryId: Int = 0, // 1
    var url: String = "", // http://webapps.iqlance-demo.com/little_soul/public/images/url/video.mp4?opwvc=1
    var localUrl: String = "", // http://webapps.iqlance-demo.com/little_soul/public/images/url/video.mp4?opwvc=1
    @SerializedName("video_download")
    var videoDownload: String = "", // false
    @SerializedName("is_upload_audio")
    var is_upload_audio: Int = 0, // false
) : Parcelable