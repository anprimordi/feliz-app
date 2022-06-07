package com.feliz.apps.domain.model.form.vendors

import com.feliz.apps.domain.model.common.Constants
import com.feliz.apps.presentation.util.extensions.getRandomString
import kotlin.random.Random

data class MarriageOfficer(
    val id: Long,
    val clientId: Long,
    val bookReader: String?,
    val groomWitness: String?,
    val brideWitness: String?,
    val masterCeremony: String?,
    val tausyiah: String?,
    val prayerOfficer: String?
) {
    companion object {

        val DEFAULT = MarriageOfficer(
            id = Constants.DEFAULT_ID,
            clientId = Constants.DEFAULT_ID,
            bookReader = Constants.DEFAULT_NAME,
            groomWitness = Constants.DEFAULT_NAME,
            brideWitness = Constants.DEFAULT_NAME,
            masterCeremony = Constants.DEFAULT_NAME,
            tausyiah = Constants.DEFAULT_NAME,
            prayerOfficer = Constants.DEFAULT_NAME
        )

        fun dummy(): MarriageOfficer {
            return MarriageOfficer(
                id = Random.nextLong(),
                clientId = Random.nextLong(),
                bookReader = getRandomString(),
                groomWitness = getRandomString(),
                brideWitness = getRandomString(),
                masterCeremony = getRandomString(),
                tausyiah = getRandomString(),
                prayerOfficer = getRandomString()
            )
        }
    }
}
