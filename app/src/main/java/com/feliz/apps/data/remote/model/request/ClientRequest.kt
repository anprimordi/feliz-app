package com.feliz.apps.data.remote.model.request

import com.google.gson.annotations.SerializedName

class ClientRequest {

    data class Login(
        @SerializedName(value = "username") val username: String? = null,
        @SerializedName(value = "password") val password: String? = null
    )
}