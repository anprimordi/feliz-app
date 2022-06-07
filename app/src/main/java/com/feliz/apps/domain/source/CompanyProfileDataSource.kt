package com.feliz.apps.domain.source

import com.feliz.apps.domain.model.CompanyProfile
import com.feliz.apps.domain.model.common.Result

interface CompanyProfileDataSource {
    suspend fun getCompanyProfile(): Result<CompanyProfile?>
}