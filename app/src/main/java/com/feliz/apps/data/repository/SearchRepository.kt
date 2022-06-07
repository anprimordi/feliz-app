package com.feliz.apps.data.repository

import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.domain.model.Product
import com.feliz.apps.domain.model.Vendor
import com.feliz.apps.domain.model.common.Result
import com.feliz.apps.domain.source.SearchDataSource

class SearchRepository(
    private val remoteSource: SearchDataSource
) : SearchDataSource {
    override suspend fun getProductList(query: String): Result<List<Product>?> {
        return remoteSource.getProductList(query = query)
    }

    override suspend fun getVendorList(query: String): Result<List<Vendor>?> {
        return remoteSource.getVendorList(query = query)
    }

    override suspend fun getUpcomingEventList(query: String): Result<List<Portfolio>?> {
        return remoteSource.getUpcomingEventList(query = query)
    }

    override suspend fun getFinishedEventList(query: String): Result<List<Portfolio>?> {
        return remoteSource.getFinishedEventList(query = query)
    }
}