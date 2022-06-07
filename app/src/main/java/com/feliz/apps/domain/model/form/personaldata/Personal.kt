package com.feliz.apps.domain.model.form.personaldata

import com.feliz.apps.domain.model.common.Constants
import com.feliz.apps.presentation.util.extensions.getRandomString
import kotlin.random.Random

data class Personal(
    val id: Long,
    val clientId: Long,
    val fullname: String,
    val description: String?,
    val groomName: String,
    val brideName: String,
    val groomFatherName: String,
    val groomMotherName: String,
    val brideFatherName: String,
    val brideMotherName: String,
    val groomFamilyName: String?,
    val brideFamilyName: String?
) {
    companion object {
        val DEFAULT = Personal(
            id = Constants.DEFAULT_ID,
            clientId = Constants.DEFAULT_ID,
            fullname = Constants.DEFAULT_NAME,
            description = Constants.DEFAULT_DESCRIPTION,
            groomName = Constants.DEFAULT_NAME,
            brideName = Constants.DEFAULT_NAME,
            groomFatherName = Constants.DEFAULT_NAME,
            groomMotherName = Constants.DEFAULT_NAME,
            brideFatherName = Constants.DEFAULT_NAME,
            brideMotherName = Constants.DEFAULT_NAME,
            groomFamilyName = Constants.DEFAULT_NAME,
            brideFamilyName = Constants.DEFAULT_NAME
        )

        fun dummy(): Personal {
            return Personal(
                id = Random.nextLong(),
                clientId = Random.nextLong(),
                fullname = getRandomString(),
                description = getRandomString(),
                groomName = getRandomString(),
                brideName = getRandomString(),
                groomFatherName = getRandomString(),
                groomMotherName = getRandomString(),
                brideFatherName = getRandomString(),
                brideMotherName = getRandomString(),
                groomFamilyName = getRandomString(),
                brideFamilyName = getRandomString()
            )
        }
    }
}
