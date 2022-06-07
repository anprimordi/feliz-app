package com.feliz.apps.data.remote.service

import com.feliz.apps.data.remote.model.Wrapper
import com.feliz.apps.data.remote.model.response.ProductCategoryResponse
import com.feliz.apps.data.remote.model.response.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    @GET(value = "product/category-list")
    suspend fun getProductCategoryList(): Wrapper<List<ProductCategoryResponse>>

    @GET(value = "product/category-detail/{category_id}")
    suspend fun getProductCategoryById(
        @Path(value = "category_id") categoryId: Long
    ): Wrapper<ProductCategoryResponse>

    @GET(value = "product/{product_id}")
    suspend fun getProductDetailById(
        @Path(value = "product_id") productId: Long
    ): Wrapper<ProductResponse>
}