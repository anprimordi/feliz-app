package com.feliz.apps.data.repository

import com.feliz.apps.domain.model.Vendor
import com.feliz.apps.domain.model.VendorCategory
import com.feliz.apps.domain.model.common.Result
import com.feliz.apps.domain.source.VendorDataSource

class VendorRepository(
    private val remoteSource: VendorDataSource
) : VendorDataSource {

    override suspend fun getVendorCategoryList(): Result<List<VendorCategory>> {
        return remoteSource.getVendorCategoryList()
    }

    override suspend fun getVendorCategoryDetailList(
        countCategory: Int,
        countVendor: Int
    ): Result<List<VendorCategory>> {
        return remoteSource.getVendorCategoryDetailList(countCategory, countVendor)
    }

    override suspend fun getVendorListByCategoryId(categoryId: Long): Result<List<Vendor>> {
        return remoteSource.getVendorListByCategoryId(categoryId)
    }

    override suspend fun getVendorById(vendorId: Long): Result<Vendor?> {
        return remoteSource.getVendorById(vendorId)
    }

}