package com.feliz.apps.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class HomeResponse(
    @SerializedName(value = "thumbnail") val thumbnail: List<Thumbnail>? = null,
    @SerializedName(value = "categories") val categories: List<ProductCategory>? = null,
    @SerializedName(value = "upcoming_event") val upcomingEvent: List<Portfolio>? = null,
    @SerializedName(value = "finished_event") val finishedEvent: List<Portfolio>? = null,
    @SerializedName(value = "data_vendors") val dataVendors: List<VendorCategory>? = null
) {
    data class ProductCategory(
        @SerializedName(value = "id") val id: Long? = null,
        @SerializedName(value = "name") val name: String? = null,
        @SerializedName(value = "description") val description: String? = null,
        @SerializedName(value = "photo") val photo: String? = null,
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
        @SerializedName(value = "updated_at") val updatedAt: String? = null
    )

    data class VendorCategory(
        @SerializedName(value = "id") val id: Long? = null,
        @SerializedName(value = "name") val name: String? = null,
        @SerializedName(value = "photo") val photoUrl: String? = null,
        @SerializedName(value = "vendors") val vendors: List<Vendor>? = null
    ) {
        data class Vendor(
            @SerializedName(value = "id") val id: Long? = null,
            @SerializedName(value = "category_vendor_id") val categoryVendorId: Long? = null,
            @SerializedName(value = "name") val name: String? = null,
            @SerializedName(value = "address") val address: String? = null,
            @SerializedName(value = "facility") val facility: String? = null,
            @SerializedName(value = "capacity") val capacity: String? = null,
            @SerializedName(value = "location_area") val locationSpace: String? = null,
            @SerializedName(value = "room") val room: String? = null,
            @SerializedName(value = "latitude") val latitude: Double? = null,
            @SerializedName(value = "longitude") val longitude: Double? = null,
            @SerializedName(value = "updated_at") val updatedAt: String? = null,
            @SerializedName(value = "created_at") val createdAt: String? = null,
            @SerializedName(value = "thumbnail") val thumbnail: VendorThumbnail
        ) {
            data class VendorThumbnail(
                @SerializedName(value = "id") val id: Long? = null,
                @SerializedName(value = "vendor_id") val vendorId: Long? = null,
                @SerializedName(value = "url") val url: String? = null,
                @SerializedName(value = "created_at") val createdAt: String? = null,
                @SerializedName(value = "updated_at") val updatedAt: String? = null
            )
        }
    }

    data class Thumbnail(
        @SerializedName(value = "id") val id: Long? = null,
        @SerializedName(value = "name") val name: String? = null,
        @SerializedName(value = "photo") val url: String? = null
    )
}

//@SerializedName(value = "") val ,
//@SerializedName(value = "") val ,
//@SerializedName(value = "") val ,
//@SerializedName(value = "") val ,
//@SerializedName(value = "") val ,