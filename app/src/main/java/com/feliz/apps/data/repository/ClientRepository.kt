package com.feliz.apps.data.repository

import com.feliz.apps.domain.model.common.Result
import com.feliz.apps.domain.model.form.personaldata.Client
import com.feliz.apps.domain.source.ClientDataSource

class ClientRepository(
    private val localeDataSource: ClientDataSource,
    private val remoteDataSource: ClientDataSource
) : ClientDataSource {
    override suspend fun login(username: String, password: String): Result<Client?> {
        return remoteDataSource.login(username, password)
    }

    override suspend fun logout(): Result<Unit> {
        return localeDataSource.logout()
    }

    override suspend fun getClientDetailById(token: String, clientId: Long): Result<Client?> {
        return remoteDataSource.getClientDetailById(token, clientId)
    }

    override suspend fun setClientToken(token: String): Result<Unit> {
        return localeDataSource.setClientToken(token)
    }

    override suspend fun getClientToken(): Result<String?> {
        return localeDataSource.getClientToken()
    }

    override fun saveClientData(client: Client): Result<Unit> {
        return localeDataSource.saveClientData(client)
    }

    override fun getClientDetailData(): Result<Client?> {
        return localeDataSource.getClientDetailData()
    }
}