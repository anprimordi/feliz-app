package com.feliz.apps.data.remote.model.response.mapper.search

import com.feliz.apps.data.remote.model.response.SearchResponse
import com.feliz.apps.domain.model.Product
import com.feliz.apps.domain.model.common.Mapper
import com.feliz.apps.domain.model.util.DateTimeUtils

object MapperResponseSearchProduct : Mapper<Product, SearchResponse.Product> {
    override fun toDomain(model: SearchResponse.Product): Product {
        return Product(
            id = model.id ?: Product.DEFAULT.id,
            categoryId = model.categoryProductId ?: Product.DEFAULT.categoryId,
            name = model.name ?: Product.DEFAULT.name,
            price = model.price ?: Product.DEFAULT.price,
            detail = model.detail ?: Product.DEFAULT.detail,
            photoUrls = Product.DEFAULT.photoUrls,
            category = Product.DEFAULT.category,
            thumbnailUrl = model.thumbnail?.url ?: Product.DEFAULT.thumbnailUrl,
            portfolio = Product.DEFAULT.portfolio,
            createdAt = DateTimeUtils.parseServerDate(model.createdAt) ?: Product.DEFAULT.createdAt
        )
    }

    override fun fromDomain(model: Product): SearchResponse.Product {
        throw UnsupportedOperationException()
    }
}