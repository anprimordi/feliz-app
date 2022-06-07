package com.feliz.apps.data.remote.model.response.mapper.form

import com.feliz.apps.data.remote.model.response.FormResponse
import com.feliz.apps.domain.model.common.Mapper
import com.feliz.apps.domain.model.form.supportvendor.Generator

object MapperResponseFormGenerator : Mapper<Generator, FormResponse.Support.SupportVendors> {
    override fun toDomain(model: FormResponse.Support.SupportVendors): Generator {
        return Generator(
            id = model.id ?: Generator.DEFAULT.id,
            name = model.name ?: Generator.DEFAULT.name,
            description = model.description ?: Generator.DEFAULT.description
        )
    }

    override fun fromDomain(model: Generator): FormResponse.Support.SupportVendors {
        throw UnsupportedOperationException()
    }
}