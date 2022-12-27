package com.littlesoul.common.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AddressCommon {

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("user_id")
    @Expose
    var userId: Int? = null

    @SerializedName("building")
    @Expose
    var building: String? = null

    @SerializedName("street")
    @Expose
    var street: String? = null

    @SerializedName("city")
    @Expose
    var city: String? = null

    @SerializedName("state")
    @Expose
    var state: String? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("pincode")
    @Expose
    var pincode: String? = null

    @SerializedName("landmark")
    @Expose
    var landmark: String? = null

    @SerializedName("map_address")
    @Expose
    var mapAddress: String? = null

    @SerializedName("latitude")
    @Expose
    var latitude: Double? = null

    @SerializedName("longitude")
    @Expose
    var longitude: Double? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("address")
    @Expose
    var address: String? = null

}
