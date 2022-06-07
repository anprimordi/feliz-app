package com.feliz.apps.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class VendorCountRequest(
    @SerializedName(value = "count_category") val countCategory: Int? = null,
    @SerializedName(value = "count_vendor") val countVendor: Int? = null
)
