package com.feliz.apps.data.remote.source

import com.feliz.apps.data.remote.AppRemoteClient
import com.feliz.apps.data.remote.model.request.mapper.MapperRequestSearchQuery
import com.feliz.apps.data.remote.model.response.mapper.search.MapperResponseSearchPortfolio
import com.feliz.apps.data.remote.model.response.mapper.search.MapperResponseSearchProduct
import com.feliz.apps.data.remote.model.response.mapper.search.MapperResponseSearchVendor
import com.feliz.apps.data.remote.service.SearchService
import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.domain.model.Product
import com.feliz.apps.domain.model.Vendor
import com.feliz.apps.domain.model.common.Result
import com.feliz.apps.domain.source.SearchDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchRemoteDataSource(
    private val client: AppRemoteClient
) : SearchDataSource {
    override suspend fun getProductList(query: String): Result<List<Product>?> {
        return withContext(Dispatchers.IO) {
            client.execute {
                val service = client.create(SearchService::class.java)
                service.getSearchProductList(query)
            }.map {
                if (it == null) emptyList() else MapperResponseSearchProduct.toDomainList(it.data!!)
            }
        }
    }

    override suspend fun getVendorList(query: String): Result<List<Vendor>?> {
        return withContext(Dispatchers.IO) {
            client.execute {
                val service = client.create(SearchService::class.java)
                val request = MapperRequestSearchQuery.fromDomain(query = query)
                service.getSearchVendorList(query)
            }.map {
                if (it == null) emptyList() else MapperResponseSearchVendor.toDomainList(it.data!!)
            }
        }
    }

    override suspend fun getUpcomingEventList(query: String): Result<List<Portfolio>?> {
        return withContext(Dispatchers.IO) {
            client.execute {
                val service = client.create(SearchService::class.java)
                service.getSearchUpcomingEventList(query)
            }.map {
                if (it == null) emptyList() else MapperResponseSearchPortfolio.toDomainList(it.data!!)
            }
        }
    }

    override suspend fun getFinishedEventList(query: String): Result<List<Portfolio>?> {
        return withContext(Dispatchers.IO) {
            client.execute {
                val service = client.create(SearchService::class.java)
                service.getSearchFinishedEventList(query)
            }.map {
                if (it == null) emptyList() else MapperResponseSearchPortfolio.toDomainList(it.data!!)
            }
        }
    }
}