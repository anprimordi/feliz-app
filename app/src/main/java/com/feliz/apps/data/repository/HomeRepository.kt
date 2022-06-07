package com.feliz.apps.data.repository

import com.feliz.apps.domain.model.Home
import com.feliz.apps.domain.model.common.Result
import com.feliz.apps.domain.source.HomeDataSource

class HomeRepository(private val remoteDataSource: HomeDataSource) : HomeDataSource {
    override suspend fun getHome(): Result<Home?> {
        return remoteDataSource.getHome()
    }

}