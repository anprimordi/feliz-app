package com.feliz.apps.data.remote.model.response.mapper.home

import com.feliz.apps.data.remote.model.response.HomeResponse
import com.feliz.apps.domain.model.Thumbnail
import com.feliz.apps.domain.model.common.Mapper

object MapperResponseHomeThumbnail : Mapper<Thumbnail, HomeResponse.Thumbnail> {
    override fun toDomain(model: HomeResponse.Thumbnail): Thumbnail {
        return Thumbnail(
            id = model.id ?: Thumbnail.DEFAULT.id,
            name = model.name ?: Thumbnail.DEFAULT.name,
            url = model.url ?: Thumbnail.DEFAULT.url
        )
    }

    override fun fromDomain(model: Thumbnail): HomeResponse.Thumbnail {
        throw UnsupportedOperationException()
    }
}