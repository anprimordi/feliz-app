package com.feliz.apps.data.remote.service

import com.feliz.apps.data.remote.model.Wrapper
import com.feliz.apps.data.remote.model.response.PortfolioResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PortfolioService {

    @GET(value = "event/get-upcoming-list")
    suspend fun getUpcomingEventList(): Wrapper<List<PortfolioResponse>>

    @GET(value = "event/get-finished-list")
    suspend fun getFinishedEventList(): Wrapper<List<PortfolioResponse>>

    @GET(value = "event/{portfolio_id}")
    suspend fun getPortfolioById(@Path(value = "portfolio_id") portfolioId: Long): Wrapper<PortfolioResponse>
}