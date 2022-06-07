package com.feliz.apps.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class VendorResponse(
    @SerializedName(value = "id") val id: Long? = null,
    @SerializedName(value = "category_vendor_id") val categoryId: Long? = null,
    @SerializedName(value = "name") val name: String? = null,
    @SerializedName(value = "address") val address: String? = null,
    @SerializedName(value = "facility") val facility: String? = null,
    @SerializedName(value = "capacity") val capacity: String? = null,
    @SerializedName(value = "location_area") val locationSpace: String? = null,
    @SerializedName(value = "room") val room: String? = null,
    @SerializedName(value = "latitude") val latitude: Double? = null,
    @SerializedName(value = "longitude") val longitude: Double? = null,
    @SerializedName(value = "updated_at") val updatedAt: String? = null,
    @SerializedName(value = "thumbnail") val thumbnail: Thumbnail? = null,
    @SerializedName(value = "category") val category: Category? = null,
    @SerializedName(value = "photos") val photos: List<Photos>? = null
) {
    data class Thumbnail(
        @SerializedName(value = "id") val id: Long? = null,
        @SerializedName(value = "vendor_id") val vendorId: Long? = null,
        @SerializedName(value = "url") val url: String? = null,
        @SerializedName(value = "created_at") val createdAt: String? = null,
        @SerializedName(value = "updated_at") val updatedAt: String? = null
    )

    data class Category(
        @SerializedName(value = "id") val id: Long,
        @SerializedName(value = "name") val name: String,
        @SerializedName(value = "photo") val photo: String
    )

    data class Photos(
        @SerializedName(value = "id") val id: Long? = null,
        @SerializedName(value = "vendor_id") val vendorId: Long? = null,
        @SerializedName(value = "url") val url: String? = null
    )
}
