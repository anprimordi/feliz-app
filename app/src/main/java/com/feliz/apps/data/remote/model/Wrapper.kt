package com.feliz.apps.data.remote.model

import com.google.gson.annotations.SerializedName

data class Wrapper<T>(
    @SerializedName(value = "status") val isSuccess: Boolean,
    @SerializedName(value = "message") val message: String,
    @SerializedName(value = "data") val data: T? = null
)