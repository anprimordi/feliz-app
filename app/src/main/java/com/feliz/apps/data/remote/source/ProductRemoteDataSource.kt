package com.feliz.apps.data.remote.source

import com.feliz.apps.data.remote.AppRemoteClient
import com.feliz.apps.data.remote.model.response.mapper.product.MapperResponseProduct
import com.feliz.apps.data.remote.model.response.mapper.product.MapperResponseProductCategory
import com.feliz.apps.data.remote.service.ProductService
import com.feliz.apps.domain.model.Product
import com.feliz.apps.domain.model.ProductCategory
import com.feliz.apps.domain.model.common.NotFoundError
import com.feliz.apps.domain.model.common.Result
import com.feliz.apps.domain.model.common.Success
import com.feliz.apps.domain.source.ProductDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRemoteDataSource(
    private val client: AppRemoteClient
) : ProductDataSource {
    override fun setProductCategoryName(categoryName: String) {
        throw UnsupportedOperationException()
    }

    override fun getProductCategoryName(): String? {
        throw UnsupportedOperationException()
    }

    override suspend fun getProductCategoryList(): Result<List<ProductCategory>?> {
        return withContext(Dispatchers.IO) {
            client.execute {
                val service = client.create(ProductService::class.java)
                service.getProductCategoryList()
            }.mapTo {
                if (it != null) Success(MapperResponseProductCategory.toDomainList(it.data!!))
                else NotFoundError()
            }
        }
    }

    override suspend fun getProductCategoryById(categoryId: Long): Result<ProductCategory?> {
        return withContext(Dispatchers.IO) {
            client.execute {
                val service = client.create(ProductService::class.java)
                service.getProductCategoryById(categoryId)
            }.map {
                val data = it.data
                if (data != null) MapperResponseProductCategory.toDomain(data)
                else null
            }
        }
    }

    override suspend fun getProductDetailById(productId: Long): Result<Product?> {
        return withContext(Dispatchers.IO) {
            client.execute {
                val service = client.create(ProductService::class.java)
                service.getProductDetailById(productId)
            }.map {
                val data = it.data
                if (data != null) MapperResponseProduct.toDomain(data)
                else null
            }
        }
    }
}