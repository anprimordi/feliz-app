package com.feliz.apps.data.local.source

import com.feliz.apps.data.local.AppPreference
import com.feliz.apps.data.local.AppPreference.Companion.KEY_PRODUCT_CATEGORY_NAME
import com.feliz.apps.domain.model.Product
import com.feliz.apps.domain.model.ProductCategory
import com.feliz.apps.domain.model.common.Result
import com.feliz.apps.domain.model.common.UnsupportedError
import com.feliz.apps.domain.source.ProductDataSource

class ProductLocaleDataSource(
    private val appPreference: AppPreference
) : ProductDataSource {
    override fun setProductCategoryName(categoryName: String) {
        appPreference.editor().remove(KEY_PRODUCT_CATEGORY_NAME).apply()
        appPreference.editor().putString(KEY_PRODUCT_CATEGORY_NAME, categoryName)
    }

    override fun getProductCategoryName(): String? {
        return appPreference.get().getString(KEY_PRODUCT_CATEGORY_NAME, null)
    }

    override suspend fun getProductCategoryList(): Result<List<ProductCategory>?> {
        return UnsupportedError(source = this)
    }

    override suspend fun getProductCategoryById(categoryId: Long): Result<ProductCategory?> {
        return UnsupportedError(source = this)
    }

    override suspend fun getProductDetailById(productId: Long): Result<Product?> {
        return UnsupportedError(source = this)
    }

}