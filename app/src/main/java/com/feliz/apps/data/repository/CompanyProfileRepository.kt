package com.feliz.apps.data.repository

import com.feliz.apps.domain.model.CompanyProfile
import com.feliz.apps.domain.model.common.Result
import com.feliz.apps.domain.source.CompanyProfileDataSource

class CompanyProfileRepository(
    private val remoteSource: CompanyProfileDataSource
) : CompanyProfileDataSource {

    override suspend fun getCompanyProfile(): Result<CompanyProfile?> {
        return remoteSource.getCompanyProfile()
    }
}