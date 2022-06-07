package com.feliz.apps.data.remote.source

import com.feliz.apps.data.remote.AppRemoteClient
import com.feliz.apps.data.remote.model.response.mapper.company.MapperResponseCompanyProfile
import com.feliz.apps.data.remote.service.CompanyProfileService
import com.feliz.apps.domain.model.CompanyProfile
import com.feliz.apps.domain.model.common.Result
import com.feliz.apps.domain.source.CompanyProfileDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CompanyProfileRemoteDataSource(
    private val client: AppRemoteClient
) : CompanyProfileDataSource {

    override suspend fun getCompanyProfile(): Result<CompanyProfile?> {
        return withContext(Dispatchers.IO) {
            client.execute {
                val service = client.create(CompanyProfileService::class.java)
                service.getCompanyProfile()
            }.map {
                val data = it.data
                if (data != null) MapperResponseCompanyProfile.toDomain(data)
                else null
            }
        }
    }

}