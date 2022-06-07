package com.feliz.apps.data.remote.model.response.mapper.form

import com.feliz.apps.data.remote.model.response.FormResponse
import com.feliz.apps.domain.model.common.Mapper
import com.feliz.apps.domain.model.form.supportvendor.SoundSystem

object MapperResponseFormSoundSystem : Mapper<SoundSystem, FormResponse.Support.SupportVendors> {
    override fun toDomain(model: FormResponse.Support.SupportVendors): SoundSystem {
        return SoundSystem(
            id = model.id ?: SoundSystem.DEFAULT.id,
            name = model.name ?: SoundSystem.DEFAULT.name,
            description = model.description ?: SoundSystem.DEFAULT.description
        )
    }

    override fun fromDomain(model: SoundSystem): FormResponse.Support.SupportVendors {
        throw UnsupportedOperationException()
    }
}