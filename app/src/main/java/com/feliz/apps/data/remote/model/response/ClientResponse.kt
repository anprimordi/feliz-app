package com.feliz.apps.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class ClientResponse(
    @SerializedName(value = "id") val id: Long? = null,
    @SerializedName(value = "username") val username: String? = null,
    @SerializedName(value = "password") val password: String? = null,
    @SerializedName(value = "representative_name") val representative: String? = null,
    @SerializedName(value = "date_of_approval") val approvalDate: String? = null,
    @SerializedName(value = "created_at") val createdAt: String? = null,
    @SerializedName(value = "updated_at") val updatedAt: String? = null,
    @SerializedName(value = "token") val token: String? = null
)