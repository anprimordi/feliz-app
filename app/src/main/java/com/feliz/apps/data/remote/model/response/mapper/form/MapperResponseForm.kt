package com.feliz.apps.data.remote.model.response.mapper.form

import com.feliz.apps.data.remote.model.response.FormResponse
import com.feliz.apps.domain.model.common.Mapper
import com.feliz.apps.domain.model.form.Form
import com.feliz.apps.domain.model.form.personaldata.Personal
import com.feliz.apps.domain.model.form.supportvendor.SupportVendor
import com.feliz.apps.domain.model.form.vendors.MarriageOfficer
import com.feliz.apps.domain.model.form.vendors.ReceptionOfficer
import com.feliz.apps.domain.model.form.vendors.Vendors
import com.feliz.apps.domain.model.util.DateTimeUtils

object MapperResponseForm : Mapper<Form, FormResponse> {
    override fun toDomain(model: FormResponse): Form {
        return Form(
            id = model.id ?: Form.DEFAULT.id,
            personal = parsePersonal(model = model.personal!!),
            username = model.username ?: Form.DEFAULT.username,
            representativeName = model.representativeName ?: Form.DEFAULT.representativeName,
            dateOfApproval = DateTimeUtils.parseServerDate(model.dateOfApproval)
                ?: Form.DEFAULT.dateOfApproval,
            createdAt = DateTimeUtils.parseServerDate(model.createdAt) ?: Form.DEFAULT.createdAt,
            updatedAt = DateTimeUtils.parseServerDate(model.updatedAt) ?: Form.DEFAULT.updatedAt,
            receptionOfficer = parseReceptionOfficer(model = model.reception!!),
            marriageOfficer = parseMarriageOfficer(model = model.marriage!!),
            vendors = parseVendors(model = model.vendor!!),
            supportVendor = parseSupport(model = model.support!!)
        )
    }

    override fun fromDomain(model: Form): FormResponse {
        throw UnsupportedOperationException()
    }

    private fun parsePersonal(model: FormResponse.Personal): Personal {
        return MapperResponseFormPersonal.toDomain(model)
    }

    private fun parseReceptionOfficer(model: FormResponse.Reception): ReceptionOfficer {
        return MapperResponseFormReception.toDomain(model)
    }

    private fun parseMarriageOfficer(model: FormResponse.Marriage): MarriageOfficer {
        return MapperResponseFormMarriage.toDomain(model)
    }

    private fun parseVendors(model: FormResponse.Vendors): Vendors {
        return MapperResponseFormVendor.toDomain(model)
    }

    private fun parseSupport(model: FormResponse.Support): SupportVendor {
        return MapperResponseFormSupport.toDomain(model)
    }
}