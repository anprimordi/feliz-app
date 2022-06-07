package com.feliz.apps.domain.source

import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.domain.model.common.Result

interface PortfolioDataSource {
    suspend fun getUpcomingEventList(): Result<List<Portfolio>>
    suspend fun getFinishedEventList(): Result<List<Portfolio>>
    suspend fun getPortfolioById(portfolioId: Long): Result<Portfolio?>
}