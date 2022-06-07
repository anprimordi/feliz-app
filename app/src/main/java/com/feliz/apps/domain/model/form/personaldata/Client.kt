package com.feliz.apps.domain.model.form.personaldata

import com.feliz.apps.domain.model.common.Constants
import com.feliz.apps.presentation.util.extensions.getRandomString
import java.util.*

data class Client(
    val id: Long,
    val fullname: String,
    val description: String,
    val username: String,
    val password: String,
    val token: String,
    val felizRepresentativeName: String,
    val approvalDate: Date,
    val updatedAt: Date
) {
    companion object {
        val DEFAULT = Client(
            id = Constants.DEFAULT_ID,
            fullname = Constants.DEFAULT_NAME,
            description = Constants.DEFAULT_DESCRIPTION,
            username = Constants.DEFAULT_NAME,
            password = Constants.DEFAULT_PASSWORD,
            token = Constants.DEFAULT_TOKEN,
            felizRepresentativeName = Constants.DEFAULT_NAME,
            approvalDate = Constants.DEFAULT_DATE,
            updatedAt = Constants.DEFAULT_DATE
        )

        fun dummy(): Client {
            return Client(
                id = 1111,
                fullname = getRandomString(),
                description = getRandomString(),
                username = "qwerty",
                password = "asdf",
                token = getRandomString(8),
                felizRepresentativeName = getRandomString(),
                approvalDate = Date(),
                updatedAt = Date()
            )
        }
    }
}