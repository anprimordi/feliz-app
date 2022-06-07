package com.feliz.apps.data.remote.model.response

import com.feliz.apps.domain.model.Product
import com.google.gson.annotations.SerializedName

data class PortfolioResponse(
    @SerializedName(value = "id") val id: Long? = null,
    @SerializedName(value = "product_id") val productId: Long? = null,
    @SerializedName(value = "name") val name: String? = null,
    @SerializedName(value = "description") val description: String? = null,
    @SerializedName(value = "date_time") val dateTime: String? = null,
    @SerializedName(value = "address") val address: String? = null,
    @SerializedName(value = "photo") val photoUrl: String? = null,
    @SerializedName(value = "product") val product: Product? = null
) {
}