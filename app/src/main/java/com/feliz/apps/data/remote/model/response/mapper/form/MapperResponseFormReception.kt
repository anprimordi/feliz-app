package com.feliz.apps.data.remote.model.response.mapper.form

import com.feliz.apps.data.remote.model.response.FormResponse
import com.feliz.apps.domain.model.common.Mapper
import com.feliz.apps.domain.model.form.vendors.ReceptionOfficer

object MapperResponseFormReception : Mapper<ReceptionOfficer, FormResponse.Reception> {
    override fun toDomain(model: FormResponse.Reception): ReceptionOfficer {
        return ReceptionOfficer(
            id = model.id ?: ReceptionOfficer.DEFAULT.id,
            clientId = model.clientId ?: ReceptionOfficer.DEFAULT.clientId,
            familyCoName = model.familyCoName ?: ReceptionOfficer.DEFAULT.familyCoName,
            familyCoPhone = model.familyCoPhone?.toLong() ?: ReceptionOfficer.DEFAULT.familyCoPhone,
            guestCoName = model.guestCoName ?: ReceptionOfficer.DEFAULT.guestCoName,
            guestCoPhone = model.guestCoPhone?.toLong() ?: ReceptionOfficer.DEFAULT.guestCoPhone,
            vipGuestCoName = model.vipGuestCoName ?: ReceptionOfficer.DEFAULT.vipGuestCoName,
            vipGuestCoPhone = model.vipGuestCoPhone?.toLong()
                ?: ReceptionOfficer.DEFAULT.vipGuestCoPhone,
            giftCoName = model.giftCoName ?: ReceptionOfficer.DEFAULT.giftCoName,
            giftCoPhone = model.giftCoPhone?.toLong() ?: ReceptionOfficer.DEFAULT.giftCoPhone,
            souvenirCoName = model.souvenirCoName ?: ReceptionOfficer.DEFAULT.souvenirCoName,
            souvenirCoPhone = model.souvenirCoPhone?.toLong()
                ?: ReceptionOfficer.DEFAULT.souvenirCoPhone,
            guestOfficer = model.guestOfficer ?: ReceptionOfficer.DEFAULT.guestOfficer,
            guestBookOfficer = model.guestBookOfficer ?: ReceptionOfficer.DEFAULT.guestBookOfficer,
            souvenirOfficer = model.souvenirOfficer ?: ReceptionOfficer.DEFAULT.souvenirOfficer
        )
    }

    override fun fromDomain(model: ReceptionOfficer): FormResponse.Reception {
        throw UnsupportedOperationException()
    }
}