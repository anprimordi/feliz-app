package com.feliz.apps.data.remote.model.response.mapper.vendor

import com.feliz.apps.data.remote.model.response.VendorResponse
import com.feliz.apps.domain.model.Vendor
import com.feliz.apps.domain.model.VendorCategory
import com.feliz.apps.domain.model.common.Constants
import com.feliz.apps.domain.model.common.Mapper
import com.feliz.apps.domain.model.util.DateTimeUtils

object MapperResponseVendor : Mapper<Vendor, VendorResponse> {
    override fun toDomain(model: VendorResponse): Vendor {
        return Vendor(
            id = model.id ?: Vendor.DEFAULT.id,
            categoryId = model.categoryId ?: Vendor.DEFAULT.categoryId,
            name = model.name ?: Vendor.DEFAULT.name,
            thumbnail =
            if (model.thumbnail != null) parseVendorThumbnail(model.thumbnail)
            else Vendor.DEFAULT.thumbnail,
            photoUrls = parseVendorPhotoUrls(model.photos) ?: emptyList(),
            address = model.address ?: Vendor.DEFAULT.address,
            facility = model.facility ?: Vendor.DEFAULT.facility,
            capacity = model.capacity ?: Vendor.DEFAULT.capacity,
            locationSpace = model.locationSpace ?: Vendor.DEFAULT.locationSpace,
            room = model.room ?: Vendor.DEFAULT.room,
            latitude = model.latitude ?: Vendor.DEFAULT.latitude,
            longitude = model.longitude ?: Vendor.DEFAULT.longitude,
            updatedAt = DateTimeUtils.parseServerDate(model.updatedAt) ?: Vendor.DEFAULT.updatedAt,
            category = parseVendorCategory(model.category)
        )
    }

    override fun fromDomain(model: Vendor): VendorResponse {
        throw UnsupportedOperationException()
    }

    private fun parseVendorThumbnail(thumbnail: VendorResponse.Thumbnail): Vendor.Thumbnail {
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

    private fun parseVendorPhotoUrls(photos: List<VendorResponse.Photos>?): List<Vendor.Photos> {
        val list = arrayListOf<Vendor.Photos>()
        photos?.forEach {
            list.add(
                Vendor.Photos(
                    id = it.id ?: Constants.DEFAULT_ID,
                    vendorId = it.vendorId ?: Constants.DEFAULT_ID,
                    url = it.url ?: Constants.DEFAULT_PHOTO_URL
                )
            )
        }
        return list
    }

    private fun parseVendorCategory(category: VendorResponse.Category?): VendorCategory {
        return VendorCategory(
            id = category?.id ?: VendorCategory.DEFAULT.id,
            name = category?.name ?: VendorCategory.DEFAULT.name,
            photoUrl = category?.photo ?: VendorCategory.DEFAULT.photoUrl,
            vendors = emptyList()
        )
    }
}