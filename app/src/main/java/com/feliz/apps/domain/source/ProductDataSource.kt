package com.feliz.apps.domain.source

import com.feliz.apps.domain.model.Product
import com.feliz.apps.domain.model.ProductCategory
import com.feliz.apps.domain.model.common.Result

interface ProductDataSource {

    fun setProductCategoryName(categoryName: String)
    fun getProductCategoryName(): String?

    suspend fun getProductCategoryList(): Result<List<ProductCategory>?>
    suspend fun getProductCategoryById(categoryId: Long): Result<ProductCategory?>
    suspend fun getProductDetailById(productId: Long): Result<Product?>
}