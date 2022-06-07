package com.feliz.apps.domain.model

import com.feliz.apps.domain.model.common.Constants
import com.feliz.apps.domain.model.common.Model
import com.feliz.apps.presentation.util.extensions.getRandomString
import kotlin.random.Random

data class Thumbnail(
    val id: Long,
    val name: String,
    val url: String
) : Model {
    companion object {
        val DEFAULT = Thumbnail(
            id = Constants.DEFAULT_ID,
            name = Constants.DEFAULT_NAME,
            url = Constants.DEFAULT_PHOTO_URL
        )

        fun dummy(): Thumbnail {
            return Thumbnail(
                id = Random.nextLong(),
                name = getRandomString(),
                url = getRandomString()
            )
        }
    }
}
