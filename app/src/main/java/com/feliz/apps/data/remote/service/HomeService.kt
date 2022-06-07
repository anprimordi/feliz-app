package com.feliz.apps.data.remote.service

import com.feliz.apps.data.remote.model.Wrapper
import com.feliz.apps.data.remote.model.response.HomeResponse
import retrofit2.http.GET

interface HomeService {

    @GET(value = "home")
    suspend fun getHome(): Wrapper<HomeResponse>
}