package com.feliz.apps.data.remote.service

import com.feliz.apps.data.remote.model.Wrapper
import com.feliz.apps.data.remote.model.request.ClientRequest
import com.feliz.apps.data.remote.model.response.ClientResponse
import retrofit2.http.*

interface ClientService {

    @POST(value = "client/login")
    suspend fun login(
        @Body body: ClientRequest.Login
    ): Wrapper<ClientResponse>

    @GET(value = "client/{client_id}")
    suspend fun getClientDetailById(
        @Header(value = "token_api") token: String,
        @Path(value = "client_id") clientId: Long
    ): Wrapper<ClientResponse>
}