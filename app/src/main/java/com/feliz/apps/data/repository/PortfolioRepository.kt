package com.feliz.apps.data.repository

import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.domain.model.common.Result
import com.feliz.apps.domain.source.PortfolioDataSource

class PortfolioRepository(
    private val remoteSource: PortfolioDataSource
) : PortfolioDataSource {
    override suspend fun getUpcomingEventList(): Result<List<Portfolio>> {
        return remoteSource.getUpcomingEventList()
    }

    override suspend fun getFinishedEventList(): Result<List<Portfolio>> {
        return remoteSource.getFinishedEventList()
    }

    override suspend fun getPortfolioById(portfolioId: Long): Result<Portfolio?> {
        return remoteSource.getPortfolioById(portfolioId)
    }
}