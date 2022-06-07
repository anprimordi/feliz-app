package com.feliz.apps.data.remote.model.response.mapper.form

import com.feliz.apps.data.remote.model.response.FormResponse
import com.feliz.apps.domain.model.common.Mapper
import com.feliz.apps.domain.model.form.supportvendor.Multimedia

object MapperResponseFormMultimedia : Mapper<Multimedia, FormResponse.Support.SupportVendors> {
    override fun toDomain(model: FormResponse.Support.SupportVendors): Multimedia {
        return Multimedia(
            id = model.id ?: Multimedia.DEFAULT.id,
            name = model.name ?: Multimedia.DEFAULT.name,
            description = model.description ?: Multimedia.DEFAULT.description
        )
    }

    override fun fromDomain(model: Multimedia): FormResponse.Support.SupportVendors {
        throw UnsupportedOperationException()
    }
}