package com.feliz.apps.data.remote.model.response.mapper.product

import com.feliz.apps.data.remote.model.response.ProductCategoryResponse
import com.feliz.apps.domain.model.Product
import com.feliz.apps.domain.model.ProductCategory
import com.feliz.apps.domain.model.common.Constants
import com.feliz.apps.domain.model.common.Mapper
import com.feliz.apps.domain.model.util.DateTimeUtils

object MapperResponseProductCategory : Mapper<ProductCategory, ProductCategoryResponse> {
    override fun toDomain(model: ProductCategoryResponse): ProductCategory {
        return ProductCategory(
            id = model.id ?: ProductCategory.DEFAULT.id,
            name = model.name ?: ProductCategory.DEFAULT.name,
            description = model.description ?: ProductCategory.DEFAULT.description,
            photoUrl = model.photo ?: ProductCategory.DEFAULT.photoUrl,
            products = parseProductCategoryProductList(model.products)
                ?: ProductCategory.DEFAULT.products
        )
    }

    override fun fromDomain(model: ProductCategory): ProductCategoryResponse {
        throw UnsupportedOperationException()
    }

    private fun parseProductCategoryProductList(products: List<ProductCategoryResponse.Product>?): List<Product> {
        val list = arrayListOf<Product>()
        products?.forEach {
            list.add(
                Product(
                    id = it.id ?: Product.DEFAULT.id,
                    categoryId = it.categoryId ?: Product.DEFAULT.categoryId,
                    name = it.name ?: Product.DEFAULT.name,
                    detail = it.detail ?: Product.DEFAULT.detail,
                    price = it.price ?: Product.DEFAULT.price,
                    createdAt = DateTimeUtils.parseServerDate(it.createdAt)
                        ?: Product.DEFAULT.createdAt,
                    thumbnailUrl = parseProductCategoryProductThumbnail(it.thumbnail),
                    photoUrls = parseProductCategoryProductPhotoUrls(it.photos) ?: emptyList(),
                    category = Product.DEFAULT.category,
                    portfolio = Product.DEFAULT.portfolio
                )
            )
        }
        return list
    }

    private fun parseProductCategoryProductPhotoUrls(photos: List<ProductCategoryResponse.Product.Photos>?): List<Product.Photos> {
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

    private fun parseProductCategoryProductThumbnail(thumbnail: ProductCategoryResponse.Product.Thumbnail): String {
        return thumbnail.url ?: Product.DEFAULT.thumbnailUrl
    }
}