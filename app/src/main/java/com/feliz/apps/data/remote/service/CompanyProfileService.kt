package com.feliz.apps.data.remote.service

import com.feliz.apps.data.remote.model.Wrapper
import com.feliz.apps.data.remote.model.response.CompanyProfileResponse
import retrofit2.http.GET

interface CompanyProfileService {
    @GET(value = "company-profile")
    suspend fun getCompanyProfile(): Wrapper<CompanyProfileResponse>
}