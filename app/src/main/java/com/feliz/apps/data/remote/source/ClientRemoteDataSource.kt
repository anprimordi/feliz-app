package com.feliz.apps.data.remote.source

import com.feliz.apps.data.remote.AppRemoteClient
import com.feliz.apps.data.remote.model.request.mapper.MapperRequestClientLogin
import com.feliz.apps.data.remote.model.response.mapper.client.MapperResponseClientLogin
import com.feliz.apps.data.remote.service.ClientService
import com.feliz.apps.domain.model.common.Result
import com.feliz.apps.domain.model.common.UnsupportedError
import com.feliz.apps.domain.model.form.personaldata.Client
import com.feliz.apps.domain.source.ClientDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ClientRemoteDataSource(
    private val client: AppRemoteClient
) : ClientDataSource {

    override suspend fun login(username: String, password: String): Result<Client?> {
        return withContext(Dispatchers.IO) {
            client.execute {
                val service = client.create(ClientService::class.java)
                val request = MapperRequestClientLogin.fromDomain(username, password)
                service.login(request)
            }.map {
                val data = it.data
                if (data != null) MapperResponseClientLogin.toDomain(data)
                else null
            }
        }
    }

    override suspend fun logout(): Result<Unit> {
        return UnsupportedError(source = this)
    }

    override suspend fun getClientDetailById(token: String, clientId: Long): Result<Client?> {
        return withContext(Dispatchers.IO) {
            client.execute {
                val service = client.create(ClientService::class.java)
                service.getClientDetailById(token, clientId)
            }.map {
                val data = it.data
                if (data != null) MapperResponseClientLogin.toDomain(data)
                else null
            }
        }
    }

    override suspend fun setClientToken(token: String): Result<Unit> {
        return UnsupportedError(source = this)
    }

    override suspend fun getClientToken(): Result<String?> {
        return UnsupportedError(source = this)
    }

    override fun saveClientData(client: Client): Result<Unit> {
        return UnsupportedError(source = this)
    }

    override fun getClientDetailData(): Result<Client?> {
        return UnsupportedError(source = this)
    }

}