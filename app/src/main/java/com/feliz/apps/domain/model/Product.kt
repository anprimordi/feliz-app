package com.feliz.apps.domain.model

import com.feliz.apps.domain.model.common.Constants
import com.feliz.apps.domain.model.common.Model
import com.feliz.apps.presentation.util.extensions.getRandomString
import java.util.*
import kotlin.random.Random

data class Product(
    val id: Long,
    val categoryId: Long,
    val name: String,
    val price: Long,
    val detail: String,
    val createdAt: Date,
    val thumbnailUrl: String,
    val photoUrls: List<Photos>,
    val category: ProductCategory,
    val portfolio: List<Portfolio>?
) : Model {
    data class Photos(
        val id: Long,
        val productId: Long,
        val photoUrl: String
    ) {
        companion object {
            val DEFAULT = Photos(
                id = Constants.DEFAULT_ID,
                productId = Constants.DEFAULT_ID,
                photoUrl = Constants.DEFAULT_PHOTO_URL
            )
        }

        fun dummy(): Photos {
            return Photos(
                id = Random.nextLong(),
                productId = Random.nextLong(),
                photoUrl = "https://www.acf.hhs.gov/sites/default/files/images/ofa/shutterstock_201735029.jpg"
            )
        }
    }

    companion object {
        val DEFAULT = Product(
            id = Constants.DEFAULT_ID,
            categoryId = Constants.DEFAULT_ID,
            name = Constants.DEFAULT_NAME,
            price = Constants.DEFAULT_PRICE,
            detail = Constants.DEFAULT_DESCRIPTION,
            createdAt = Constants.DEFAULT_DATE,
            thumbnailUrl = Constants.DEFAULT_PHOTO_URL,
            photoUrls = emptyList(),
            category = ProductCategory.DEFAULT,
            portfolio = emptyList()
        )

        fun dummy(): Product {
            return Product(
                id = Random.nextLong(),
                categoryId = Random.nextLong(),
                name = getRandomString(),
                price = Random.nextLong(),
                detail = getRandomString(),
                createdAt = Date(),
                thumbnailUrl = "https://cdn.sindonews.net/dyn/620/content/2020/02/15/34/1527796/hindari-penipuan-lima-cara-pilih-wedding-organizer-yang-resmi-6mG-thumb.jpg",
                photoUrls = listOf(),
                category = ProductCategory(
                    id = Random.nextLong(),
                    name = getRandomString(),
                    description = getRandomString(),
                    photoUrl = "https://cdn.sindonews.net/dyn/620/content/2020/02/15/34/1527796/hindari-penipuan-lima-cara-pilih-wedding-organizer-yang-resmi-6mG-thumb.jpg",
                    products = emptyList()
                ),
                portfolio = listOf(
                    Portfolio.dummy(),
                    Portfolio.dummy(),
                    Portfolio.dummy()
                )
            )
        }
    }
}
