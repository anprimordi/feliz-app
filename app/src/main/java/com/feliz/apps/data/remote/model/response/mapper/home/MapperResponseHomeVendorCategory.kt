package com.feliz.apps.data.remote.model.response.mapper.home

import com.feliz.apps.data.remote.model.response.HomeResponse
import com.feliz.apps.domain.model.Vendor
import com.feliz.apps.domain.model.VendorCategory
import com.feliz.apps.domain.model.common.Mapper
import com.feliz.apps.domain.model.util.DateTimeUtils

object MapperResponseHomeVendorCategory : Mapper<VendorCategory, HomeResponse.VendorCategory> {
    override fun toDomain(model: HomeResponse.VendorCategory): VendorCategory {
        return VendorCategory(
            id = model.id ?: VendorCategory.DEFAULT.id,
            name = model.name ?: VendorCategory.DEFAULT.name,
            photoUrl = model.photoUrl ?: VendorCategory.DEFAULT.photoUrl,
            vendors =
            if (model.vendors != null) MapperResponseHomeVendor.toDomainList(model.vendors)
            else VendorCategory.DEFAULT.vendors
        )
    }

    override fun fromDomain(model: VendorCategory): HomeResponse.VendorCategory {
        throw UnsupportedOperationException()
    }

    private fun parseVendors(vendors: List<HomeResponse.VendorCategory.Vendor>?): List<Vendor> {
        val list = arrayListOf<Vendor>()
        vendors?.forEach {
            list.add(
                Vendor(
                    id = it.id ?: Vendor.DEFAULT.id,
                    name = it.name ?: Vendor.DEFAULT.name,
                    categoryId = it.categoryVendorId ?: Vendor.DEFAULT.categoryId,
                    thumbnail = parseVendorThumbnail(it.thumbnail),
                    photoUrls = Vendor.DEFAULT.photoUrls,
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