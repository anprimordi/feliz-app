package com.feliz.apps.data.remote.model.response.mapper.product

import com.feliz.apps.data.remote.model.response.ProductResponse
import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.domain.model.Product
import com.feliz.apps.domain.model.common.Constants
import com.feliz.apps.domain.model.common.Mapper
import com.feliz.apps.domain.model.util.DateTimeUtils

object MapperResponseProduct : Mapper<Product, ProductResponse> {
    override fun toDomain(model: ProductResponse): Product {
        return Product(
            id = model.id ?: Product.DEFAULT.id,
            categoryId = model.categoryId ?: Product.DEFAULT.categoryId,
            name = model.name ?: Product.DEFAULT.name,
            detail = model.detail ?: Product.DEFAULT.detail,
            price = model.price ?: Product.DEFAULT.price,
            createdAt = DateTimeUtils.parseServerDate(model.createdAt) ?: Product.DEFAULT.createdAt,
            thumbnailUrl = parseProductThumbnail(model.thumbnail!!),
            photoUrls = parseProductPhotoUrls(model.photos) ?: emptyList(),
            category = Product.DEFAULT.category,
            portfolio = parseProductEvents(model.portfolio)
        )
    }

    override fun fromDomain(model: Product): ProductResponse {
        throw UnsupportedOperationException()
    }

    private fun parseProductPhotoUrls(photos: List<ProductResponse.Photos>?): List<Product.Photos> {
        val list = arrayListOf<Product.Photos>()
        photos?.forEach {
            list.add(
                Product.Photos(
                    id = it.id ?: Constants.DEFAULT_ID,
                    productId = it.productId ?: Constants.DEFAULT_ID,
                    photoUrl = it.url ?: Constants.DEFAULT_PHOTO_URL
                )
            )
        }
        return list
    }

    private fun parseProductThumbnail(thumbnail: ProductResponse.Thumbnail): String {
        return thumbnail.url ?: Product.DEFAULT.thumbnailUrl
    }

    private fun parseProductEvents(model: List<ProductResponse.Portfolio>?): List<Portfolio> {
        val list = arrayListOf<Portfolio>()
        model?.forEach {
            list.add(
                Portfolio(
                    id = it.id ?: Portfolio.DEFAULT.id,
                    name = it.name ?: Portfolio.DEFAULT.name,
                    description = it.description ?: Portfolio.DEFAULT.description,
                    location = it.address ?: Portfolio.DEFAULT.location,
                    dateTime = DateTimeUtils.parseServerDate(it.dateTime)
                        ?: Portfolio.DEFAULT.dateTime,
                    photoUrl = it.photo ?: Portfolio.DEFAULT.photoUrl,
                    product = Portfolio.DEFAULT.product
                )
            )
        }
        return list
    }

}