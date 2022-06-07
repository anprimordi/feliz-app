package com.feliz.apps.domain.model

import com.feliz.apps.domain.model.common.Model

data class Home(
    val thumbnails: List<Thumbnail>,
    val productCategories: List<ProductCategory>,
    val upcomingEvents: List<Portfolio>,
    val finishedEvents: List<Portfolio>,
    val vendorCategories: List<VendorCategory>
) : Model {
    companion object {
        val DEFAULT = Home(
            thumbnails = emptyList(),
            productCategories = emptyList(),
            upcomingEvents = emptyList(),
            finishedEvents = emptyList(),
            vendorCategories = emptyList()
        )

        fun dummy(): Home {
            return Home(
                thumbnails = listOf(Thumbnail.dummy(), Thumbnail.dummy(), Thumbnail.dummy()),
                productCategories = listOf(
                    ProductCategory.dummy(),
                    ProductCategory.dummy(),
                    ProductCategory.dummy()
                ),
                upcomingEvents = listOf(Portfolio.dummy(), Portfolio.dummy(), Portfolio.dummy()),
                finishedEvents = listOf(Portfolio.dummy(), Portfolio.dummy(), Portfolio.dummy()),
                vendorCategories = listOf(
                    VendorCategory.dummy(),
                    VendorCategory.dummy(),
                    VendorCategory.dummy()
                )
            )
        }
    }
}
