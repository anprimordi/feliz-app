package com.feliz.apps.domain.source

import com.feliz.apps.domain.model.Vendor
import com.feliz.apps.domain.model.VendorCategory
import com.feliz.apps.domain.model.common.Result

interface VendorDataSource {

    suspend fun getVendorCategoryList(): Result<List<VendorCategory>>
    suspend fun getVendorCategoryDetailList(
        countCategory: Int,
        countVendor: Int
    ): Result<List<VendorCategory>>

    suspend fun getVendorListByCategoryId(categoryId: Long): Result<List<Vendor>>
    suspend fun getVendorById(vendorId: Long): Result<Vendor?>

}

/* Step implement data layer
1. Create domain - data source
2. Create function in domain - data source
3. Create repository - implement domain data source
4. Create remote data source - implement domain data source
5. Create api endpoint service (ex: VendorService.kt)
6. Create endpoint request model if exists with domain model-mapper (optional)
7. Create endpoint response model with domain model-mapper (optional)
8. Update endpoint service for using endpoint request & response model
9. Call api service in remote data source
10. Handle result from data source in view model
 */