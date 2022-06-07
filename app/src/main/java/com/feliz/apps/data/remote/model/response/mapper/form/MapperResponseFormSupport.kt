package com.feliz.apps.data.remote.model.response.mapper.form

import com.feliz.apps.data.remote.model.response.FormResponse
import com.feliz.apps.domain.model.common.Mapper
import com.feliz.apps.domain.model.form.supportvendor.SupportVendor
import com.feliz.apps.domain.model.util.DateTimeUtils

object MapperResponseFormSupport : Mapper<SupportVendor, FormResponse.Support> {
    override fun toDomain(model: FormResponse.Support): SupportVendor {
        return SupportVendor(
            id = model.id ?: SupportVendor.DEFAULT.id,
            clientId = model.clientId ?: SupportVendor.DEFAULT.clientId,
            lightingId = model.lightingId ?: SupportVendor.DEFAULT.lightingId,
            generatorId = model.generatorId ?: SupportVendor.DEFAULT.generatorId,
            soundSystemId = model.soundSystemId ?: SupportVendor.DEFAULT.soundSystemId,
            multimediaId = model.multimediaId ?: SupportVendor.DEFAULT.multimediaId,
            weddingCar = model.weddingCar ?: SupportVendor.DEFAULT.weddingCar,
            usherId = model.usherId ?: SupportVendor.DEFAULT.id,
            invitationAmount = model.invitationAmount?.toInt()
                ?: SupportVendor.DEFAULT.invitationAmount,
            courierId = model.courierId ?: SupportVendor.DEFAULT.courierId,
            etc = model.etc ?: SupportVendor.DEFAULT.etc,
            createdAt = DateTimeUtils.parseServerDate(model.createdAt)
                ?: SupportVendor.DEFAULT.createdAt,
            updatedAt = DateTimeUtils.parseServerDate(model.updatedAt)
                ?: SupportVendor.DEFAULT.updatedAt,
            lighting = MapperResponseFormLighting.toDomain(model.lighting!!),
            generator = MapperResponseFormGenerator.toDomain(model.generator!!),
            soundSystem = MapperResponseFormSoundSystem.toDomain(model.soundSystem!!),
            multimedia = MapperResponseFormMultimedia.toDomain(model.multimedia!!),
            usher = MapperResponseFormUsher.toDomain(model.usher!!),
            courier = MapperResponseFormCourier.toDomain(model.courier!!)
        )
    }

    override fun fromDomain(model: SupportVendor): FormResponse.Support {
        throw UnsupportedOperationException()
    }
}