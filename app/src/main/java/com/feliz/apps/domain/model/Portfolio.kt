package com.feliz.apps.domain.model

import com.feliz.apps.domain.model.common.Constants
import com.feliz.apps.domain.model.common.Model
import com.feliz.apps.presentation.util.extensions.getRandomString
import java.util.*
import kotlin.random.Random

data class Portfolio(
    val id: Long,
    val name: String,
    val description: String,
    val location: String,
    val dateTime: Date,
    val photoUrl: String,
    val product: Product
) : Model {
    companion object {
        val DEFAULT = Portfolio(
            id = Constants.DEFAULT_ID,
            name = Constants.DEFAULT_NAME,
            description = Constants.DEFAULT_DESCRIPTION,
            location = Constants.DEFAULT_ADDRESS,
            dateTime = Constants.DEFAULT_DATE,
            photoUrl = Constants.DEFAULT_PHOTO_URL,
            product = Product.DEFAULT
        )

        fun dummy(): Portfolio {
            return Portfolio(
                id = Random.nextLong(),
                name = getRandomString(),
                description = getRandomString(),
                location = getRandomString(),
                dateTime = Date(),
                photoUrl = "https://www.acf.hhs.gov/sites/default/files/images/ofa/shutterstock_201735029.jpg",
                product = Product(
                    id = Random.nextLong(),
                    categoryId = Random.nextLong(),
                    name = getRandomString(),
                    price = Random.nextLong(),
                    detail = getRandomString(),
                    createdAt = Date(),
                    thumbnailUrl = "https://www.acf.hhs.gov/sites/default/files/images/ofa/shutterstock_201735029.jpg",
                    photoUrls = listOf(),
                    category = ProductCategory(
                        id = Random.nextLong(),
                        name = getRandomString(),
                        description = getRandomString(),
                        photoUrl = "https://www.acf.hhs.gov/sites/default/files/images/ofa/shutterstock_201735029.jpg",
                        products = emptyList()
                    ),
                    portfolio = emptyList()
                )
            )
        }
    }
}