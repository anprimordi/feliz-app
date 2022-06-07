package com.feliz.apps.data.remote.source

import com.feliz.apps.data.remote.AppRemoteClient
import com.feliz.apps.data.remote.model.response.mapper.home.MapperResponseHome
import com.feliz.apps.data.remote.service.HomeService
import com.feliz.apps.domain.model.Home
import com.feliz.apps.domain.model.common.Result
import com.feliz.apps.domain.source.HomeDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRemoteDataSource(private val client: AppRemoteClient) : HomeDataSource {
    override suspend fun getHome(): Result<Home?> {
        return withContext(Dispatchers.IO) {
            client.execute {
                val service = client.create(HomeService::class.java)
                service.getHome()
            }.map {
                val data = it.data
                if (data != null) MapperResponseHome.toDomain(data)
                else null
            }
        }
    }

}