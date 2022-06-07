package com.feliz.apps.domain.model

import com.feliz.apps.domain.model.common.Constants
import com.feliz.apps.domain.model.common.Model
import com.feliz.apps.presentation.util.extensions.getRandomString
import kotlin.random.Random

data class ProductCategory(
    val id: Long,
    val name: String,
    val description: String,
    val photoUrl: String,
    val products: List<Product>?
) : Model {
    companion object {
        val DEFAULT = ProductCategory(
            id = Constants.DEFAULT_ID,
            name = Constants.DEFAULT_NAME,
            description = Constants.DEFAULT_DESCRIPTION,
            photoUrl = Constants.DEFAULT_PHOTO_URL,
            products = emptyList()
        )

        fun dummy(): ProductCategory {
            return ProductCategory(
                id = Random.nextLong(),
                name = getRandomString(),
                description = getRandomString(),
                photoUrl = "https://cdn.sindonews.net/dyn/620/content/2020/02/15/34/1527796/hindari-penipuan-lima-cara-pilih-wedding-organizer-yang-resmi-6mG-thumb.jpg",
                products = listOf(Product.dummy(), Product.dummy(), Product.dummy())
            )
        }
    }
}