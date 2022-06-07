package com.feliz.apps.domain.model

import com.feliz.apps.domain.model.common.Constants
import com.feliz.apps.domain.model.common.Model
import com.feliz.apps.presentation.util.extensions.getRandomString
import java.util.*
import kotlin.random.Random

data class Vendor(
    val id: Long,
    val categoryId: Long,
    val name: String,
    val photoUrls: List<Photos>,
    val thumbnail: Thumbnail,
    val address: String?,
    val facility: String?,
    val capacity: String?,
    val locationSpace: String?,
    val room: String?,
    val latitude: Double,
    val longitude: Double,
    val updatedAt: Date,
    val category: VendorCategory
) : Model {
    data class Thumbnail(
        val id: Long,
        val vendorId: Long,
        val url: String,
        val createdAt: Date,
        val updatedAt: Date
    ) {
        companion object {
            val DEFAULT = Thumbnail(
                id = Constants.DEFAULT_ID,
                vendorId = Constants.DEFAULT_ID,
                url = Constants.DEFAULT_PHOTO_URL,
                createdAt = Constants.DEFAULT_DATE,
                updatedAt = Constants.DEFAULT_DATE
            )

            fun dummy(): Thumbnail {
                return Thumbnail(
                    id = Random.nextLong(),
                    vendorId = Random.nextLong(),
                    url = "https://cache.marriott.com/marriottassets/marriott/MESMC/mesmc-weddings-8062-hor-feat.jpg",
                    createdAt = Constants.DEFAULT_DATE,
                    updatedAt = Constants.DEFAULT_DATE
                )
            }
        }
    }

    data class Photos(
        val id: Long,
        val vendorId: Long,
        val url: String
    ) {
        companion object {
            val DEFAULT = Photos(
                id = Constants.DEFAULT_ID,
                vendorId = Constants.DEFAULT_ID,
                url = Constants.DEFAULT_PHOTO_URL
            )

            fun dummy(): Photos {
                return Photos(
                    id = Random.nextLong(),
                    vendorId = Random.nextLong(),
                    url = "https://cache.marriott.com/marriottassets/marriott/MESMC/mesmc-weddings-8062-hor-feat.jpg"
                )
            }
        }
    }

    companion object {
        val DEFAULT = Vendor(
            id = Constants.DEFAULT_ID,
            categoryId = Constants.DEFAULT_ID,
            name = Constants.DEFAULT_NAME,
            photoUrls = listOf(Photos.DEFAULT),
            thumbnail = Thumbnail.DEFAULT,
            address = Constants.DEFAULT_ADDRESS,
            facility = Constants.DEFAULT_DESCRIPTION,
            capacity = Constants.DEFAULT_CAPACITY,
            locationSpace = Constants.DEFAULT_LOCATION_SPACE,
            room = Constants.DEFAULT_ROOM,
            latitude = Constants.DEFAULT_COORDINATE,
            longitude = Constants.DEFAULT_COORDINATE,
            updatedAt = Constants.DEFAULT_DATE,
            category = VendorCategory(
                id = Constants.DEFAULT_ID,
                name = Constants.DEFAULT_NAME,
                photoUrl = Constants.DEFAULT_PHOTO_URL,
                vendors = emptyList()
            )
        )

        fun dummy(): Vendor {
            return Vendor(
                id = Random.nextLong(),
                categoryId = Random.nextLong(),
                name = getRandomString(),
                thumbnail = Thumbnail.dummy(),
                photoUrls = listOf(
                    Photos.dummy(), Photos.dummy(), Photos.dummy()
                ),
                address = getRandomString(),
                facility = getRandomString(),
                capacity = getRandomString(),
                locationSpace = getRandomString(),
                room = getRandomString(),
                latitude = 37.7749,
                longitude = -122.4194,
                updatedAt = Date(),
                category = VendorCategory(
                    id = Random.nextLong(),
                    name = getRandomString(),
                    photoUrl = "https://static.thehoneycombers.com/wp-content/uploads/sites/4/2017/05/Bisma-Eight-wedding-venue-Bali.jpg",
                    vendors = emptyList()
                )
            )
        }
    }
}
