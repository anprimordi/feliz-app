package com.feliz.apps.data.remote.model.response.mapper.home

import com.feliz.apps.data.remote.model.response.HomeResponse
import com.feliz.apps.domain.model.Vendor
import com.feliz.apps.domain.model.common.Mapper
import com.feliz.apps.domain.model.util.DateTimeUtils

object MapperResponseHomeVendor : Mapper<Vendor, HomeResponse.VendorCategory.Vendor> {
    override fun toDomain(model: HomeResponse.VendorCategory.Vendor): Vendor {
        return Vendor(
            id = model.id ?: Vendor.DEFAULT.id,
            categoryId = model.categoryVendorId ?: Vendor.DEFAULT.categoryId,
            name = model.name ?: Vendor.DEFAULT.name,
            thumbnail =
            if (model.thumbnail != null) parseVendorThumbnail(model.thumbnail)
            else Vendor.DEFAULT.thumbnail,
            photoUrls = Vendor.DEFAULT.photoUrls,
            address = model.address ?: Vendor.DEFAULT.address,
            facility = model.facility ?: Vendor.DEFAULT.facility,
            capacity = model.capacity ?: Vendor.DEFAULT.capacity,
            locationSpace = model.locationSpace ?: Vendor.DEFAULT.locationSpace,
            room = model.room ?: Vendor.DEFAULT.room,
            latitude = model.latitude ?: Vendor.DEFAULT.latitude,
            longitude = model.longitude ?: Vendor.DEFAULT.longitude,
            updatedAt = DateTimeUtils.parseServerDate(model.updatedAt)
                ?: Vendor.DEFAULT.updatedAt,
            category = Vendor.DEFAULT.category
        )
    }

    override fun fromDomain(model: Vendor): HomeResponse.VendorCategory.Vendor {
        throw UnsupportedOperationException()
    }

    private fun parseVendorThumbnail(thumbnail: HomeResponse.VendorCategory.Vendor.VendorThumbnail): Vendor.Thumbnail {
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