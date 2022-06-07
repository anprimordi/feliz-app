package com.feliz.apps.data.remote.model.response

import com.google.gson.annotations.SerializedName

class SearchResponse {
    data class Product(
        @SerializedName(value = "id") val id: Long? = null,
        @SerializedName(value = "category_product_id") val categoryProductId: Long? = null,
        @SerializedName(value = "name") val name: String? = null,
        @SerializedName(value = "price") val price: Long? = null,
        @SerializedName(value = "detail") val detail: String? = null,
        @SerializedName(value = "created_at") val createdAt: String? = null,
        @SerializedName(value = "updated_at") val updatedAt: String? = null,
        @SerializedName(value = "thumbnail") val thumbnail: Thumbnail? = null
    ) {
        data class Thumbnail(
            @SerializedName(value = "id") val id: Long? = null,
            @SerializedName(value = "product_id") val productId: Long? = null,
            @SerializedName(value = "url") val url: String? = null,
            @SerializedName(value = "created_at") val createdAt: String? = null,
            @SerializedName(value = "updated_at") val updatedAt: String? = null
        )
    }

    data class Vendor(
        @SerializedName(value = "id") val id: Long? = null,
        @SerializedName(value = "category_vendor_id") val categoryVendorId: Long? = null,
        @SerializedName(value = "name") val name: String? = null,
        @SerializedName(value = "address") val address: String? = null,
        @SerializedName(value = "facility") val facility: String? = null,
        @SerializedName(value = "capacity") val capacity: String? = null,
        @SerializedName(value = "location_area") val locationArea: String? = null,
        @SerializedName(value = "room") val room: String? = null,
        @SerializedName(value = "latitude") val latitude: String? = null,
        @SerializedName(value = "longitude") val longitude: String? = null,
        @SerializedName(value = "created_at") val createdAt: String? = null,
        @SerializedName(value = "updated_at") val updatedAt: String? = null,
        @SerializedName(value = "thumbnail") val thumbnail: Thumbnail? = null
    ) {
        data class Thumbnail(
            @SerializedName(value = "id") val id: Long? = null,
            @SerializedName(value = "vendor_id") val vendorId: Long? = null,
            @SerializedName(value = "url") val url: String? = null,
            @SerializedName(value = "created_at") val createdAt: String? = null,
            @SerializedName(value = "updated_at") val updatedAt: String? = null
        )
    }

    data class Portfolio(
        @SerializedName(value = "id") val id: Long? = null,
        @SerializedName(value = "product_id") val productId: Long? = null,
        @SerializedName(value = "name") val name: String? = null,
        @SerializedName(value = "description") val description: String? = null,
        @SerializedName(value = "date_time") val dateTime: String? = null,
        @SerializedName(value = "address") val address: String? = null,
        @SerializedName(value = "photo") val photo: String? = null,
        @SerializedName(value = "created_at") val createdAt: String? = null,
        @SerializedName(value = "updated_at") val updatedAt: String? = null
    )


}