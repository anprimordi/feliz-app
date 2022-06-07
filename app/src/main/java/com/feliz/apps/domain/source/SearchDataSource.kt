package com.feliz.apps.domain.source

import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.domain.model.Product
import com.feliz.apps.domain.model.Vendor
import com.feliz.apps.domain.model.common.Result

interface SearchDataSource {
    suspend fun getProductList(query: String): Result<List<Product>?>
    suspend fun getVendorList(query: String): Result<List<Vendor>?>
    suspend fun getUpcomingEventList(query: String): Result<List<Portfolio>?>
    suspend fun getFinishedEventList(query: String): Result<List<Portfolio>?>
}