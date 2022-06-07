package com.feliz.apps.data.repository

import com.feliz.apps.domain.model.Product
import com.feliz.apps.domain.model.ProductCategory
import com.feliz.apps.domain.model.common.Result
import com.feliz.apps.domain.source.ProductDataSource

class ProductRepository(
    private val localeSource: ProductDataSource,
    private val remoteSource: ProductDataSource
) : ProductDataSource {
    override fun setProductCategoryName(categoryName: String) {
        return localeSource.setProductCategoryName(categoryName)
    }

    override fun getProductCategoryName(): String? {
        return localeSource.getProductCategoryName()
    }

    override suspend fun getProductCategoryList(): Result<List<ProductCategory>?> {
        return remoteSource.getProductCategoryList()
    }

    override suspend fun getProductCategoryById(categoryId: Long): Result<ProductCategory?> {
        return remoteSource.getProductCategoryById(categoryId)
    }

    override suspend fun getProductDetailById(productId: Long): Result<Product?> {
        return remoteSource.getProductDetailById(productId)
    }
}