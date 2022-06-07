package com.feliz.apps.data.remote.service

import com.feliz.apps.data.remote.model.Wrapper
import com.feliz.apps.data.remote.model.request.FormUpdateRequest
import com.feliz.apps.data.remote.model.response.FormResponse
import retrofit2.http.*

interface FormService {

    @GET(value = "form/sound-system-list")
    suspend fun getSoundSystemList(
        @Header(value = "token_api") token: String
    ): Wrapper<List<FormResponse.Support.SupportVendors>>

    @GET(value = "form/generator-list")
    suspend fun getGeneratorList(
        @Header(value = "token_api") token: String
    ): Wrapper<List<FormResponse.Support.SupportVendors>>

    @GET(value = "form/lighting-effect-list")
    suspend fun getLightingList(
        @Header(value = "token_api") token: String
    ): Wrapper<List<FormResponse.Support.SupportVendors>>

    @GET(value = "form/courier-list")
    suspend fun getCourierList(
        @Header(value = "token_api") token: String
    ): Wrapper<List<FormResponse.Support.SupportVendors>>

    @GET(value = "form/usher-list")
    suspend fun getUsherList(
        @Header(value = "token_api") token: String
    ): Wrapper<List<FormResponse.Support.SupportVendors>>

    @GET(value = "form/multimedia-list")
    suspend fun getMultimediaList(
        @Header(value = "token_api") token: String
    ): Wrapper<List<FormResponse.Support.SupportVendors>>

    @GET(value = "form/{client_id}")
    suspend fun getFormDetailById(
        @Header(value = "token_api") token: String,
        @Path(value = "client_id") clientId: Long
    ): Wrapper<FormResponse>

    @POST(value = "form/{client_id}")
    suspend fun updateFormById(
        @Header(value = "token_api") token: String,
        @Body body: FormUpdateRequest,
        @Path(value = "client_id") clientId: Long
    )
}