package com.feliz.apps.data.remote.model.response.mapper.home

import com.feliz.apps.data.remote.model.response.HomeResponse
import com.feliz.apps.domain.model.Home
import com.feliz.apps.domain.model.common.Mapper

object MapperResponseHome : Mapper<Home, HomeResponse> {
    override fun toDomain(model: HomeResponse): Home {
        return Home(
            thumbnails =
            if (model.thumbnail != null) MapperResponseHomeThumbnail.toDomainList(model.thumbnail)
            else emptyList(),
            productCategories =
            if (model.categories != null) MapperResponseHomeCategories.toDomainList(model.categories)
            else emptyList(),
            upcomingEvents =
            if (model.upcomingEvent != null) MapperResponseHomePortfolio.toDomainList(model.upcomingEvent)
            else emptyList(),
            finishedEvents =
            if (model.finishedEvent != null) MapperResponseHomePortfolio.toDomainList(model.finishedEvent)
            else emptyList(),
            vendorCategories =
            if (model.dataVendors != null) MapperResponseHomeVendorCategory.toDomainList(model.dataVendors)
            else emptyList()
        )
    }

    override fun fromDomain(model: Home): HomeResponse {
        throw UnsupportedOperationException()
    }

}