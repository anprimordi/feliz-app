package com.feliz.apps.domain.model

import com.feliz.apps.domain.model.common.Constants

data class VendorCount(
    val countCategory: Int?,
    val countVendor: Int?
) {
    companion object {
        val DEFAULT = VendorCount(
            countCategory = Constants.DEFAULT_COUNT,
            countVendor = Constants.DEFAULT_COUNT
        )

        fun dummy(): VendorCount {
            return VendorCount(
                countVendor = 5,
                countCategory = 5
            )
        }
    }


}
