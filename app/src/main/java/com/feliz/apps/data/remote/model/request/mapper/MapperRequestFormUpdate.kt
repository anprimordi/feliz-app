package com.feliz.apps.data.remote.model.request.mapper

import com.feliz.apps.data.remote.model.request.FormUpdateRequest
import com.feliz.apps.domain.model.form.personaldata.Personal
import com.feliz.apps.domain.model.form.supportvendor.SupportVendor
import com.feliz.apps.domain.model.form.vendors.MarriageOfficer
import com.feliz.apps.domain.model.form.vendors.ReceptionOfficer
import com.feliz.apps.domain.model.form.vendors.Vendors

object MapperRequestFormUpdate {
    fun fromDomain(
        personal: Personal,
        receptionOfficer: ReceptionOfficer,
        marriageOfficer: MarriageOfficer,
        vendors: Vendors,
        supportVendor: SupportVendor
    ): FormUpdateRequest {
        return FormUpdateRequest(
            personal = parsePersonal(personal),
            reception = parseReception(receptionOfficer),
            marriage = parseMarriage(marriageOfficer),
            vendor = parseVendor(vendors),
            support = parseSupport(supportVendor)
        )
    }

    private fun parsePersonal(model: Personal): FormUpdateRequest.Personal {
        return FormUpdateRequest.Personal(
            fullName = model.fullname,
            description = model.description!!,
            groomName = model.groomName,
            brideName = model.brideName,
            groomFatherName = model.groomFatherName,
            groomMotherName = model.groomMotherName,
            brideFatherName = model.brideFatherName,
            brideMotherName = model.brideMotherName,
            groomFamilyName = model.groomFamilyName!!,
            brideFamilyName = model.brideFamilyName!!
        )
    }

    private fun parseReception(model: ReceptionOfficer): FormUpdateRequest.Reception {
        return FormUpdateRequest.Reception(
            familyCoName = model.familyCoName,
            familyCoPhone = model.familyCoPhone.toString(),
            guestCoName = model.guestCoName,
            guestCoPhone = model.guestCoPhone.toString(),
            vipGuestCoName = model.vipGuestCoName!!,
            vipGuestCoPhone = model.vipGuestCoPhone.toString(),
            giftCoName = model.giftCoName!!,
            giftCoPhone = model.giftCoPhone.toString(),
            souvenirCoName = model.souvenirCoName!!,
            souvenirCoPhone = model.souvenirCoPhone.toString(),
            guestOfficer = model.guestOfficer!!,
            guestBookOfficer = model.guestBookOfficer!!,
            souvenirOfficer = model.souvenirOfficer!!
        )
    }

    private fun parseMarriage(model: MarriageOfficer): FormUpdateRequest.Marriage {
        return FormUpdateRequest.Marriage(
            bookReader = model.bookReader!!,
            groomWitness = model.groomWitness!!,
            brideWitness = model.brideWitness!!,
            masterCeremony = model.masterCeremony!!,
            tausiyahOfficer = model.tausyiah!!,
            prayerOfficer = model.prayerOfficer!!
        )
    }

    private fun parseVendor(model: Vendors): FormUpdateRequest.Vendor {
        return FormUpdateRequest.Vendor(
            venueName = model.venueName,
            decorationName = parseVendorName(model.decorationList),
            decorationDesc = parseVendorDesc(model.decorationList),
            cateringName = parseVendorName(model.cateringList),
            cateringDesc = parseVendorDesc(model.cateringList),
            makeupName = parseVendorName(model.makeupList),
            makeupDesc = parseVendorDesc(model.makeupList),
            documentationName = parseVendorName(model.documentationList),
            documentationDesc = parseVendorDesc(model.documentationList),
            entertainName = parseVendorName(model.entertainList),
            entertainDesc = parseVendorDesc(model.entertainList),
            mcShowName = parseVendorName(model.mcShowList),
            mcShowDesc = parseVendorDesc(model.mcShowList),
            weddingDressName = parseVendorName(model.weddingDressList),
            weddingDressDesc = parseVendorDesc(model.weddingDressList),
            accessoriesName = parseVendorName(model.accessoriesList),
            accessoriesDesc = parseVendorDesc(model.accessoriesList),
            familyDressName = parseVendorName(model.staffDressList),
            familyDressDesc = parseVendorDesc(model.staffDressList)
        )
    }

    private fun parseSupport(model: SupportVendor): FormUpdateRequest.Support {
        return FormUpdateRequest.Support(
            lightingId = model.lightingId,
            generatorId = model.generatorId,
            soundSystemId = model.soundSystemId,
            multimediaId = model.multimediaId,
            weddingCar = model.weddingCar!!,
            usherId = model.usherId,
            invitationAmount = model.invitationAmount!!,
            courierId = model.courierId,
            etc = model.etc!!
        )
    }

    private fun parseVendorName(model: ArrayList<Pair<String, String>>): String {
        val list = arrayListOf<String>()

        if (!model.isNullOrEmpty()) {
            model.forEach {
                list.add(it.first)
            }
        }

        return list.joinToString(",")
    }

    private fun parseVendorDesc(model: ArrayList<Pair<String, String>>): String {
        val list = arrayListOf<String>()

        if (!model.isNullOrEmpty()) {
            model.forEach {
                list.add(it.second)
            }
        }

        return list.joinToString(",")
    }
}