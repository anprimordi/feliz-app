package com.feliz.apps.domain.model.form

import com.feliz.apps.domain.model.common.Constants
import com.feliz.apps.domain.model.form.personaldata.Personal
import com.feliz.apps.domain.model.form.supportvendor.SupportVendor
import com.feliz.apps.domain.model.form.vendors.MarriageOfficer
import com.feliz.apps.domain.model.form.vendors.ReceptionOfficer
import com.feliz.apps.domain.model.form.vendors.Vendors
import com.feliz.apps.presentation.util.extensions.getRandomString
import java.util.*
import kotlin.random.Random

data class Form(
    val id: Long,
    val username: String,
    val representativeName: String,
    val dateOfApproval: Date,
    val createdAt: Date,
    val updatedAt: Date,
    val personal: Personal,
    val receptionOfficer: ReceptionOfficer,
    val marriageOfficer: MarriageOfficer,
    val vendors: Vendors,
    val supportVendor: SupportVendor
) {
    companion object {

        val DEFAULT = Form(
            id = Constants.DEFAULT_ID,
            username = Constants.DEFAULT_NAME,
            representativeName = Constants.DEFAULT_NAME,
            dateOfApproval = Constants.DEFAULT_DATE,
            createdAt = Constants.DEFAULT_DATE,
            updatedAt = Constants.DEFAULT_DATE,
            personal = Personal.DEFAULT,
            receptionOfficer = ReceptionOfficer.DEFAULT,
            marriageOfficer = MarriageOfficer.DEFAULT,
            vendors = Vendors.DEFAULT,
            supportVendor = SupportVendor.DEFAULT
        )

        fun dummy(): Form {
            return Form(
                id = Random.nextLong(),
                username = getRandomString(),
                representativeName = getRandomString(),
                dateOfApproval = Date(),
                createdAt = Date(),
                updatedAt = Date(),
                personal = Personal.dummy(),
                receptionOfficer = ReceptionOfficer.dummy(),
                marriageOfficer = MarriageOfficer.dummy(),
                vendors = Vendors.dummy(),
                supportVendor = SupportVendor.dummy()
            )
        }
    }
}
