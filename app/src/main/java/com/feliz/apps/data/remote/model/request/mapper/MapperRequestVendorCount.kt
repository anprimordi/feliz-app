package com.feliz.apps.data.remote.model.request.mapper

import com.feliz.apps.data.remote.model.request.VendorCountRequest
import com.feliz.apps.domain.model.VendorCount

object MapperRequestVendorCount {

    fun fromDomain(model: VendorCount): VendorCountRequest {
        return VendorCountRequest(
            countCategory = model.countCategory,
            countVendor = model.countVendor
        )
    }
}