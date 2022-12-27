package com.littlesoul.model

import android.os.ParcelFileDescriptor
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class CommonResponse(
        @SerializedName("status")
        var status: Int?,
        @SerializedName("message")
        var message: String?
)


