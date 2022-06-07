package com.feliz.apps.data.remote.model.response.mapper.vendor

import com.feliz.apps.data.remote.model.response.VendorCategoryResponse
import com.feliz.apps.domain.model.Vendor
import com.feliz.apps.domain.model.VendorCategory
import com.feliz.apps.domain.model.common.Constants
import com.feliz.apps.domain.model.common.Mapper
import com.feliz.apps.domain.model.util.DateTimeUtils

object MapperResponseVendorCategoryDetailList : Mapper<VendorCategory, VendorCategoryResponse> {
    override fun toDomain(model: VendorCategoryResponse): VendorCategory {
        return VendorCategory(
            id = model.id ?: VendorCategory.DEFAULT.id,
            name = model.name ?: VendorCategory.DEFAULT.name,
            photoUrl = model.photoUrl ?: VendorCategory.DEFAULT.photoUrl,
            vendors = parseVendors(model.vendors)
        )
    }

    override fun fromDomain(model: VendorCategory): VendorCategoryResponse {
        throw UnsupportedOperationException()
    }

    private fun parseVendors(vendors: List<VendorCategoryResponse.Vendor>?): List<Vendor> {
        val list = arrayListOf<Vendor>()
        vendors?.forEach {
            list.add(
                Vendor(
                    id = it.id ?: Vendor.DEFAULT.id,
                    categoryId = it.categoryId ?: Vendor.DEFAULT.categoryId,
                    name = it.name ?: Vendor.DEFAULT.name,
                    thumbnail = parseVendorThumbnail(it.thumbnail) ?: Vendor.DEFAULT.thumbnail,
                    photoUrls = parseVendorPhotoUrls(it.photos) ?: emptyList(),
                    address = it.address ?: Vendor.DEFAULT.address,
                    facility = it.facility ?: Vendor.DEFAULT.facility,
                    capacity = it.capacity ?: Vendor.DEFAULT.capacity,
                    locationSpace = it.locationSpace ?: Vendor.DEFAULT.locationSpace,
                    room = it.room ?: Vendor.DEFAULT.room,
                    latitude = it.latitude ?: Vendor.DEFAULT.latitude,
                    longitude = it.longitude ?: Vendor.DEFAULT.longitude,
                    updatedAt = DateTimeUtils.parseServerDate(it.updatedAt)
                        ?: Vendor.DEFAULT.updatedAt,
                    category = Vendor.DEFAULT.category
                )
            )
        }
        return list
    }

    private fun parseVendorThumbnail(thumbnail: VendorCategoryResponse.Vendor.Thumbnail): Vendor.Thumbnail {
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

    private fun parseVendorPhotoUrls(photos: List<VendorCategoryResponse.Vendor.Photos>?): List<Vendor.Photos> {
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
}