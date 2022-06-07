package com.feliz.apps.data.remote.model.response.mapper.home

import com.feliz.apps.data.remote.model.response.HomeResponse
import com.feliz.apps.domain.model.ProductCategory
import com.feliz.apps.domain.model.common.Mapper

object MapperResponseHomeCategories : Mapper<ProductCategory, HomeResponse.ProductCategory> {
    override fun toDomain(model: HomeResponse.ProductCategory): ProductCategory {
        return ProductCategory(
            id = model.id ?: ProductCategory.DEFAULT.id,
            name = model.name ?: ProductCategory.DEFAULT.name,
            description = model.description ?: ProductCategory.DEFAULT.description,
            photoUrl = model.photo ?: ProductCategory.DEFAULT.photoUrl,
            products = ProductCategory.DEFAULT.products
        )
    }

    override fun fromDomain(model: ProductCategory): HomeResponse.ProductCategory {
        throw UnsupportedOperationException()
    }
}