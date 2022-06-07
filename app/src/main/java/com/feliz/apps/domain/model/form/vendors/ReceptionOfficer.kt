package com.feliz.apps.domain.model.form.vendors

import com.feliz.apps.domain.model.common.Constants
import com.feliz.apps.presentation.util.extensions.getRandomString
import kotlin.random.Random

data class ReceptionOfficer(
    val id: Long,
    val clientId: Long,
    val familyCoName: String,
    val familyCoPhone: Long,
    val guestCoName: String,
    val guestCoPhone: Long,
    val vipGuestCoName: String?,
    val vipGuestCoPhone: Long?,
    val giftCoName: String?,
    val giftCoPhone: Long?,
    val souvenirCoName: String?,
    val souvenirCoPhone: Long?,
    val guestOfficer: String?,
    val guestBookOfficer: String?,
    val souvenirOfficer: String?
) {
    companion object {

        val DEFAULT = ReceptionOfficer(
            id = Constants.DEFAULT_ID,
            clientId = Constants.DEFAULT_ID,
            familyCoName = Constants.DEFAULT_NAME,
            familyCoPhone = Constants.DEFAULT_PHONE,
            guestCoName = Constants.DEFAULT_NAME,
            guestCoPhone = Constants.DEFAULT_PHONE,
            vipGuestCoName = Constants.DEFAULT_NAME,
            vipGuestCoPhone = Constants.DEFAULT_PHONE,
            giftCoName = Constants.DEFAULT_NAME,
            giftCoPhone = Constants.DEFAULT_PHONE,
            souvenirCoName = Constants.DEFAULT_NAME,
            souvenirCoPhone = Constants.DEFAULT_PHONE,
            guestOfficer = Constants.DEFAULT_NAME,
            guestBookOfficer = Constants.DEFAULT_NAME,
            souvenirOfficer = Constants.DEFAULT_NAME
        )

        fun dummy(): ReceptionOfficer {
            return ReceptionOfficer(
                id = Random.nextLong(),
                clientId = Random.nextLong(),
                familyCoName = getRandomString(),
                familyCoPhone = Random.nextLong(11),
                guestCoName = getRandomString(),
                guestCoPhone = Random.nextLong(11),
                vipGuestCoName = getRandomString(),
                vipGuestCoPhone = Random.nextLong(11),
                giftCoName = getRandomString(),
                giftCoPhone = Random.nextLong(11),
                souvenirCoName = getRandomString(),
                souvenirCoPhone = Random.nextLong(11),
                guestOfficer = getRandomString(),
                guestBookOfficer = getRandomString(),
                souvenirOfficer = getRandomString()
            )
        }
    }
}
