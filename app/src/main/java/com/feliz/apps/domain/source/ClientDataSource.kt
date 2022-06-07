package com.feliz.apps.domain.source

import com.feliz.apps.domain.model.common.Result
import com.feliz.apps.domain.model.form.personaldata.Client

interface ClientDataSource {

    suspend fun login(username: String, password: String): Result<Client?>
    suspend fun logout(): Result<Unit>
    suspend fun getClientDetailById(token: String, clientId: Long): Result<Client?>

    suspend fun setClientToken(token: String): Result<Unit>
    suspend fun getClientToken(): Result<String?>

    fun saveClientData(client: Client): Result<Unit>
    fun getClientDetailData(): Result<Client?>
}