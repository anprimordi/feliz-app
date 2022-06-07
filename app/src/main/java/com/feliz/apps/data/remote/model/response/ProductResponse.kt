package com.feliz.apps.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName(value = "id") val id: Long? = null,
    @SerializedName(value = "category_product_id") val categoryId: Long? = null,
    @SerializedName(value = "name") val name: String? = null,
    @SerializedName(value = "price") val price: Long? = null,
    @SerializedName(value = "detail") val detail: String? = null,
    @SerializedName(value = "created_at") val createdAt: String? = null,
    @SerializedName(value = "updated_at") val updatedAt: String? = null,
    @SerializedName(value = "thumbnail") val thumbnail: Thumbnail? = null,
    @SerializedName(value = "photos") val photos: List<Photos>? = null,
    @SerializedName(value = "events") val portfolio: List<Portfolio>? = null
) {
    data class Thumbnail(
        @SerializedName(value = "id") val id: Long? = null,
        @SerializedName(value = "product_id") val productId: Long? = null,
        @SerializedName(value = "url") val url: String? = null
    )

    data class Photos(
        @SerializedName(value = "id") val id: Long? = null,
        @SerializedName(value = "product_id") val productId: Long? = null,
        @SerializedName(value = "url") val url: String? = null,
        @SerializedName(value = "created_at") val createdAt: String? = null,
        @SerializedName(value = "updated_at") val updatedAt: String? = null
    )

    data class Portfolio(
        @SerializedName(value = "id") val id: Long? = null,
        @SerializedName(value = "product_id") val productId: Long? = null,
        @SerializedName(value = "name") val name: String? = null,
        @SerializedName(value = "description") val description: String? = null,
        @SerializedName(value = "date_time") val dateTime: String? = null,
        @SerializedName(value = "address") val address: String? = null,
        @SerializedName(value = "photo") val photo: String? = null,
        @SerializedName(value = "created_at") val createdAt: String? = null,
        @SerializedName(value = "updated_at") val updatedAt: String? = null,
    )
}