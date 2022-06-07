package com.feliz.apps.data.remote.source

import com.feliz.apps.data.remote.AppRemoteClient
import com.feliz.apps.data.remote.model.response.mapper.portfolio.MapperResponsePortfolio
import com.feliz.apps.data.remote.service.PortfolioService
import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.domain.model.common.Result
import com.feliz.apps.domain.model.common.Success
import com.feliz.apps.domain.source.PortfolioDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PortfolioRemoteDataSource(
    private val client: AppRemoteClient
) : PortfolioDataSource {
    override suspend fun getUpcomingEventList(): Result<List<Portfolio>> {
        return withContext(Dispatchers.IO) {
            client.execute {
                val service = client.create(PortfolioService::class.java)
                service.getUpcomingEventList()
            }.mapTo {
                Success(MapperResponsePortfolio.toDomainList(it.data!!))
            }
        }
    }

    override suspend fun getFinishedEventList(): Result<List<Portfolio>> {
        return withContext(Dispatchers.IO) {
            client.execute {
                val service = client.create(PortfolioService::class.java)
                service.getFinishedEventList()
            }.mapTo {
                Success(MapperResponsePortfolio.toDomainList(it.data!!))
            }
        }
    }

    override suspend fun getPortfolioById(portfolioId: Long): Result<Portfolio?> {
        return withContext(Dispatchers.IO) {
            client.execute {
                val service = client.create(PortfolioService::class.java)
                service.getPortfolioById(portfolioId)
            }.map {
                val data = it.data
                if (data != null) MapperResponsePortfolio.toDomain(data)
                else null
            }
        }
    }
}