package com.feliz.apps.data.remote.model.response.mapper.form

import com.feliz.apps.data.remote.model.response.FormResponse
import com.feliz.apps.domain.model.common.Mapper
import com.feliz.apps.domain.model.form.vendors.MarriageOfficer

object MapperResponseFormMarriage : Mapper<MarriageOfficer, FormResponse.Marriage> {
    override fun toDomain(model: FormResponse.Marriage): MarriageOfficer {
        return MarriageOfficer(
            id = model.id ?: MarriageOfficer.DEFAULT.id,
            clientId = model.clientId ?: MarriageOfficer.DEFAULT.clientId,
            bookReader = model.bookReader ?: MarriageOfficer.DEFAULT.bookReader,
            groomWitness = model.groomWitness ?: MarriageOfficer.DEFAULT.groomWitness,
            brideWitness = model.brideWitness ?: MarriageOfficer.DEFAULT.brideWitness,
            masterCeremony = model.masterCeremony ?: MarriageOfficer.DEFAULT.masterCeremony,
            tausyiah = model.tausiyahOfficer ?: MarriageOfficer.DEFAULT.tausyiah,
            prayerOfficer = model.prayerOfficer ?: MarriageOfficer.DEFAULT.prayerOfficer
        )
    }

    override fun fromDomain(model: MarriageOfficer): FormResponse.Marriage {
        throw UnsupportedOperationException()
    }
}