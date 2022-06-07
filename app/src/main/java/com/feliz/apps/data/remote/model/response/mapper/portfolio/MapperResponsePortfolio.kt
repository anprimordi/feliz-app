package com.feliz.apps.data.remote.model.response.mapper.portfolio

import com.feliz.apps.data.remote.model.response.PortfolioResponse
import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.domain.model.common.Mapper
import com.feliz.apps.domain.model.util.DateTimeUtils

object MapperResponsePortfolio : Mapper<Portfolio, PortfolioResponse> {
    override fun toDomain(model: PortfolioResponse): Portfolio {
        return Portfolio(
            id = model.id ?: Portfolio.DEFAULT.id,
            name = model.name ?: Portfolio.DEFAULT.name,
            description = model.description ?: Portfolio.DEFAULT.description,
            location = model.address ?: Portfolio.DEFAULT.location,
            dateTime = DateTimeUtils.parseServerDate(model.dateTime) ?: Portfolio.DEFAULT.dateTime,
            photoUrl = model.photoUrl ?: Portfolio.DEFAULT.photoUrl,
            product = model.product ?: Portfolio.DEFAULT.product
        )
    }

    override fun fromDomain(model: Portfolio): PortfolioResponse {
        throw UnsupportedOperationException()
    }

}