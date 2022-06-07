package com.feliz.apps.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class SearchQueryRequest(
    @SerializedName(value = "query") val query: String? = null
)