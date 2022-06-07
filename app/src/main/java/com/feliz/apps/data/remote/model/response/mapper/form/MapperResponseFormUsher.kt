package com.feliz.apps.data.remote.model.response.mapper.form

import com.feliz.apps.data.remote.model.response.FormResponse
import com.feliz.apps.domain.model.common.Mapper
import com.feliz.apps.domain.model.form.supportvendor.Usher

object MapperResponseFormUsher : Mapper<Usher, FormResponse.Support.SupportVendors> {
    override fun toDomain(model: FormResponse.Support.SupportVendors): Usher {
        return Usher(
            id = model.id ?: Usher.DEFAULT.id,
            name = model.name ?: Usher.DEFAULT.name,
            description = model.description ?: Usher.DEFAULT.description
        )
    }

    override fun fromDomain(model: Usher): FormResponse.Support.SupportVendors {
        throw UnsupportedOperationException()
    }
}