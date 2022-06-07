package com.feliz.apps.data.remote.model.request.mapper

import com.feliz.apps.data.remote.model.request.SearchQueryRequest

object MapperRequestSearchQuery {
    fun fromDomain(query: String?): SearchQueryRequest {
        return SearchQueryRequest(query = query)
    }
}