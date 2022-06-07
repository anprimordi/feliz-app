package com.feliz.apps.data.remote.model.response.mapper.form

import com.feliz.apps.data.remote.model.response.FormResponse
import com.feliz.apps.domain.model.common.Mapper
import com.feliz.apps.domain.model.form.personaldata.Personal

object MapperResponseFormPersonal : Mapper<Personal, FormResponse.Personal> {
    override fun toDomain(model: FormResponse.Personal): Personal {
        return Personal(
            id = model.id ?: Personal.DEFAULT.id,
            clientId = model.clientId ?: Personal.DEFAULT.clientId,
            fullname = model.fullname ?: Personal.DEFAULT.fullname,
            description = model.description ?: Personal.DEFAULT.description,
            groomName = model.groomName ?: Personal.DEFAULT.groomName,
            brideName = model.brideName ?: Personal.DEFAULT.brideName,
            groomFatherName = model.groomFatherName ?: Personal.DEFAULT.groomFatherName,
            groomMotherName = model.groomMotherName ?: Personal.DEFAULT.groomMotherName,
            brideFatherName = model.brideFatherName ?: Personal.DEFAULT.brideFatherName,
            brideMotherName = model.brideMotherName ?: Personal.DEFAULT.brideMotherName,
            groomFamilyName = model.groomFamilyName ?: Personal.DEFAULT.groomFamilyName,
            brideFamilyName = model.brideFamilyName ?: Personal.DEFAULT.brideFamilyName
        )
    }

    override fun fromDomain(model: Personal): FormResponse.Personal {
        throw UnsupportedOperationException()
    }
}