package com.feliz.apps.data.remote.source

import com.feliz.apps.data.remote.AppRemoteClient
import com.feliz.apps.data.remote.model.request.mapper.MapperRequestVendorCount
import com.feliz.apps.data.remote.model.response.mapper.vendor.MapperResponseVendor
import com.feliz.apps.data.remote.model.response.mapper.vendor.MapperResponseVendorCategoryDetailList
import com.feliz.apps.data.remote.service.VendorService
import com.feliz.apps.domain.model.Vendor
import com.feliz.apps.domain.model.VendorCategory
import com.feliz.apps.domain.model.VendorCount
import com.feliz.apps.domain.model.common.Error
import com.feliz.apps.domain.model.common.Result
import com.feliz.apps.domain.model.common.Success
import com.feliz.apps.domain.source.VendorDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class VendorRemoteDataSource(
    private val client: AppRemoteClient
) : VendorDataSource {

    override suspend fun getVendorCategoryList(): Result<List<VendorCategory>> {
        return withContext(Dispatchers.IO) {
            try {
                val service = client.create(VendorService::class.java)
                val response = service.getVendorCategoryList()
                if (response.isSuccess) {
                    val data = response.data?.map { it.toDomain() } ?: emptyList()
                    Success(data = data)
                } else {
                    Error(message = response.message)
                }
            } catch (ex: Exception) {
                Error(ex.message ?: "Unable to load vendor category list.")
            }
        }
    }

    override suspend fun getVendorCategoryDetailList(
        countCategory: Int,
        countVendor: Int
    ): Result<List<VendorCategory>> {
        return withContext(Dispatchers.IO) {
            client.execute {
                val service = client.create(VendorService::class.java)
                val request = MapperRequestVendorCount.fromDomain(
                    VendorCount(
                        countCategory = countCategory,
                        countVendor = countVendor
                    )
                )
                service.getVendorCategoryDetailList(request)
            }.mapTo {
                Success(MapperResponseVendorCategoryDetailList.toDomainList(it.data!!))
            }
        }
    }

    override suspend fun getVendorListByCategoryId(categoryId: Long): Result<List<Vendor>> {
        return withContext(Dispatchers.IO) {
            client.execute {
                val service = client.create(VendorService::class.java)
                service.getVendorListByCategoryId(categoryId)
            }.mapTo {
                Success(MapperResponseVendor.toDomainList(it.data!!))
            }
        }
    }

    override suspend fun getVendorById(vendorId: Long): Result<Vendor?> {
        return withContext(Dispatchers.IO) {
            client.execute {
                val service = client.create(VendorService::class.java)
                service.getVendorById(vendorId)
            }.map {
                val data = it.data
                if (data != null) MapperResponseVendor.toDomain(data)
                else null
            }
        }
    }
}