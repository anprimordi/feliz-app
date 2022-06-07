package com.feliz.apps.data.remote.model.response.mapper.home

import com.feliz.apps.data.remote.model.response.HomeResponse
import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.domain.model.common.Mapper
import com.feliz.apps.domain.model.util.DateTimeUtils

object MapperResponseHomePortfolio : Mapper<Portfolio, HomeResponse.Portfolio> {
    override fun toDomain(model: HomeResponse.Portfolio): Portfolio {
        return Portfolio(
            id = model.id ?: Portfolio.DEFAULT.id,
            name = model.name ?: Portfolio.DEFAULT.name,
            description = model.description ?: Portfolio.DEFAULT.description,
            location = model.address ?: Portfolio.DEFAULT.location,
            dateTime = DateTimeUtils.parseServerDate(model.dateTime) ?: Portfolio.DEFAULT.dateTime,
            photoUrl = model.photo ?: Portfolio.DEFAULT.photoUrl,
            product = Portfolio.DEFAULT.product
        )
    }

    override fun fromDomain(model: Portfolio): HomeResponse.Portfolio {
        throw UnsupportedOperationException()
    }
}