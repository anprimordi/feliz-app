package com.feliz.apps.data.remote.service

import com.feliz.apps.data.remote.model.Wrapper
import com.feliz.apps.data.remote.model.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET(value = "search/products")
    suspend fun getSearchProductList(
        @Query(value = "query")
        query: String
    ): Wrapper<List<SearchResponse.Product>>

    @GET(value = "search/vendors")
    suspend fun getSearchVendorList(
        @Query(value = "query")
        query: String
    ): Wrapper<List<SearchResponse.Vendor>>

    @GET(value = "search/upcoming-events")
    suspend fun getSearchUpcomingEventList(
        @Query(value = "query")
        query: String
    ): Wrapper<List<SearchResponse.Portfolio>>

    @GET(value = "search/finished-events")
    suspend fun getSearchFinishedEventList(
        @Query(value = "query")
        query: String
    ): Wrapper<List<SearchResponse.Portfolio>>
}