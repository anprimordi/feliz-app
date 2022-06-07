package com.feliz.apps.data.remote.service

import com.feliz.apps.data.remote.model.Wrapper
import com.feliz.apps.data.remote.model.request.VendorCountRequest
import com.feliz.apps.data.remote.model.response.VendorCategoryResponse
import com.feliz.apps.data.remote.model.response.VendorResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface VendorService {

    @GET(value = "vendor/category-list")
    suspend fun getVendorCategoryList(): Wrapper<List<VendorCategoryResponse>>

    @GET(value = "vendor/category-detail-list")
    suspend fun getVendorCategoryDetailList(
        @Body body: VendorCountRequest
    ): Wrapper<List<VendorCategoryResponse>>

    @GET(value = "vendor/list-by-category/{category_id}")
    suspend fun getVendorListByCategoryId(@Path(value = "category_id") categoryId: Long): Wrapper<List<VendorResponse>>

    @GET(value = "vendor/{vendor_id}")
    suspend fun getVendorById(@Path(value = "vendor_id") vendorId: Long): Wrapper<VendorResponse>
}