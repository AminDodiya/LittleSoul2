package com.littlesoul.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.File

@Parcelize
data class FilesSaved(
    var savedFile: File? = null,
    var categoryID: String = "",
    var title: String = "",
    var categoryType: String = "",
    var playVideo: Boolean = false,

    ) : Parcelable