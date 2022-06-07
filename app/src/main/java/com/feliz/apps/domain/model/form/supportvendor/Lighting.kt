package com.feliz.apps.domain.model.form.supportvendor

import com.feliz.apps.domain.model.common.Constants
import com.feliz.apps.presentation.util.extensions.getRandomString
import kotlin.random.Random

data class Lighting(
    val id: Long,
    val name: String,
    val description: String
) {
    companion object {

        val DEFAULT = Lighting(
            id = Constants.DEFAULT_ID,
            name = Constants.DEFAULT_NAME,
            description = Constants.DEFAULT_DESCRIPTION
        )

        fun dummy(): Lighting {
            return Lighting(
                id = Random.nextLong(),
                name = getRandomString(),
                description = getRandomString()
            )
        }
    }
}