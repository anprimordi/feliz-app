package com.feliz.apps.domain.model

import com.feliz.apps.domain.model.common.Constants
import com.feliz.apps.domain.model.common.Model
import com.feliz.apps.presentation.util.extensions.getRandomString
import kotlin.random.Random

data class VendorCategory(
    val id: Long,
    val name: String,
    val photoUrl: String,
    val vendors: List<Vendor>?
) : Model {
    companion object {
        val DEFAULT = VendorCategory(
            id = Constants.DEFAULT_ID,
            name = Constants.DEFAULT_NAME,
            photoUrl = Constants.DEFAULT_PHOTO_URL,
            vendors = emptyList()
        )

        fun dummy(): VendorCategory {
            return VendorCategory(
                id = Random.nextLong(),
                name = getRandomString(),
                photoUrl = "https://i.pinimg.com/originals/a1/ca/f7/a1caf722aea01592e3e24b43d561c51b.jpg",
                vendors = listOf(
                    Vendor.dummy(),
                    Vendor.dummy(),
                    Vendor.dummy()
                )
            )
        }
    }
}