package com.feliz.apps.data.remote.model.response.mapper.form

import com.feliz.apps.data.remote.model.response.FormResponse
import com.feliz.apps.domain.model.common.Mapper
import com.feliz.apps.domain.model.form.supportvendor.Courier

object MapperResponseFormCourier : Mapper<Courier, FormResponse.Support.SupportVendors> {
    override fun toDomain(model: FormResponse.Support.SupportVendors): Courier {
        return Courier(
            id = model.id ?: Courier.DEFAULT.id,
            name = model.name ?: Courier.DEFAULT.name,
            description = model.description ?: Courier.DEFAULT.description
        )
    }

    override fun fromDomain(model: Courier): FormResponse.Support.SupportVendors {
        throw UnsupportedOperationException()
    }
}