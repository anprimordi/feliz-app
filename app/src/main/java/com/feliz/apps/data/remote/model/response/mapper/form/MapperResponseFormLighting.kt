package com.feliz.apps.data.remote.model.response.mapper.form

import com.feliz.apps.data.remote.model.response.FormResponse
import com.feliz.apps.domain.model.common.Mapper
import com.feliz.apps.domain.model.form.supportvendor.Lighting

object MapperResponseFormLighting : Mapper<Lighting, FormResponse.Support.SupportVendors> {
    override fun toDomain(model: FormResponse.Support.SupportVendors): Lighting {
        return Lighting(
            id = model.id ?: Lighting.DEFAULT.id,
            name = model.name ?: Lighting.DEFAULT.name,
            description = model.description ?: Lighting.DEFAULT.description
        )
    }

    override fun fromDomain(model: Lighting): FormResponse.Support.SupportVendors {
        throw UnsupportedOperationException()
    }
}