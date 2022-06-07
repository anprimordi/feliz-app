package com.feliz.apps.data.remote.model.response.mapper.search

import com.feliz.apps.data.remote.model.response.SearchResponse
import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.domain.model.common.Mapper
import com.feliz.apps.domain.model.util.DateTimeUtils

object MapperResponseSearchPortfolio : Mapper<Portfolio, SearchResponse.Portfolio> {
    override fun toDomain(model: SearchResponse.Portfolio): Portfolio {
        return Portfolio(
            id = model.id ?: Portfolio.DEFAULT.id,
            name = model.name ?: Portfolio.DEFAULT.name,
            description = model.description ?: Portfolio.DEFAULT.description,
            location = model.address ?: Portfolio.DEFAULT.location,
            dateTime = DateTimeUtils.parseServerDate(dateString = model.dateTime)
                ?: Portfolio.DEFAULT.dateTime,
            photoUrl = model.photo ?: Portfolio.DEFAULT.photoUrl,
            product = Portfolio.DEFAULT.product
        )
    }

    override fun fromDomain(model: Portfolio): SearchResponse.Portfolio {
        throw UnsupportedOperationException()
    }
}