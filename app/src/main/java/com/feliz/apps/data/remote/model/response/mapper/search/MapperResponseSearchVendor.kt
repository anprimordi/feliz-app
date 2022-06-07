package com.feliz.apps.data.remote.model.response.mapper.search

import com.feliz.apps.data.remote.model.response.SearchResponse
import com.feliz.apps.domain.model.Vendor
import com.feliz.apps.domain.model.common.Mapper
import com.feliz.apps.domain.model.util.DateTimeUtils

object MapperResponseSearchVendor : Mapper<Vendor, SearchResponse.Vendor> {
    override fun toDomain(model: SearchResponse.Vendor): Vendor {
        return Vendor(
            id = model.id ?: Vendor.DEFAULT.id,
            categoryId = model.categoryVendorId ?: Vendor.DEFAULT.categoryId,
            name = model.name ?: Vendor.DEFAULT.name,
            thumbnail =
            if (model.thumbnail != null) parseSearchVendorThumbnail(model.thumbnail)
            else Vendor.DEFAULT.thumbnail,
            photoUrls = Vendor.DEFAULT.photoUrls,
            address = model.address ?: Vendor.DEFAULT.address,
            facility = model.facility ?: Vendor.DEFAULT.facility,
            capacity = model.capacity ?: Vendor.DEFAULT.capacity,
            locationSpace = model.locationArea ?: Vendor.DEFAULT.locationSpace,
            room = model.room ?: Vendor.DEFAULT.room,
            latitude = model.latitude!!.toDouble() ?: Vendor.DEFAULT.latitude,
            longitude = model.longitude!!.toDouble() ?: Vendor.DEFAULT.longitude,
            updatedAt = DateTimeUtils.parseServerDate(model.updatedAt) ?: Vendor.DEFAULT.updatedAt,
            category = Vendor.DEFAULT.category
        )
    }

    override fun fromDomain(model: Vendor): SearchResponse.Vendor {
        throw UnsupportedOperationException()
    }

    private fun parseSearchVendorThumbnail(thumbnail: SearchResponse.Vendor.Thumbnail): Vendor.Thumbnail {
        return Vendor.Thumbnail(
            id = thumbnail.id ?: Vendor.Thumbnail.DEFAULT.id,
            vendorId = thumbnail.vendorId ?: Vendor.Thumbnail.DEFAULT.vendorId,
            url = thumbnail.url ?: Vendor.Thumbnail.DEFAULT.url,
            createdAt = DateTimeUtils.parseServerDate(thumbnail.createdAt)
                ?: Vendor.Thumbnail.DEFAULT.createdAt,
            updatedAt = DateTimeUtils.parseServerDate(thumbnail.updatedAt)
                ?: Vendor.Thumbnail.DEFAULT.updatedAt
        )
    }
}