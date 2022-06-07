package com.feliz.apps.domain.model.form.supportvendor

import com.feliz.apps.domain.model.common.Constants
import com.feliz.apps.presentation.util.extensions.getRandomString
import java.util.*
import kotlin.random.Random

data class SupportVendor(
    val id: Long,
    val clientId: Long,
    val lightingId: Long,
    val generatorId: Long,
    val soundSystemId: Long,
    val multimediaId: Long,
    val weddingCar: String?,
    val usherId: Long,
    val invitationAmount: Int?,
    val courierId: Long,
    val etc: String?,
    val createdAt: Date,
    val updatedAt: Date,
    val lighting: Lighting?,
    val generator: Generator?,
    val soundSystem: SoundSystem?,
    val usher: Usher?,
    val multimedia: Multimedia?,
    val courier: Courier?
) {
    companion object {

        val DEFAULT = SupportVendor(
            id = Constants.DEFAULT_ID,
            clientId = Constants.DEFAULT_ID,
            lightingId = Constants.DEFAULT_ID,
            generatorId = Constants.DEFAULT_ID,
            soundSystemId = Constants.DEFAULT_ID,
            multimediaId = Constants.DEFAULT_ID,
            weddingCar = Constants.DEFAULT_NAME,
            usherId = Constants.DEFAULT_ID,
            invitationAmount = Constants.DEFAULT_COUNT,
            courierId = Constants.DEFAULT_ID,
            etc = Constants.DEFAULT_DESCRIPTION,
            createdAt = Constants.DEFAULT_DATE,
            updatedAt = Constants.DEFAULT_DATE,
            lighting = Lighting.DEFAULT,
            generator = Generator.DEFAULT,
            soundSystem = SoundSystem.DEFAULT,
            multimedia = Multimedia.DEFAULT,
            usher = Usher.DEFAULT,
            courier = Courier.DEFAULT
        )

        fun dummy(): SupportVendor {
            return SupportVendor(
                id = Random.nextLong(),
                clientId = Random.nextLong(),
                lightingId = Random.nextLong(),
                generatorId = Random.nextLong(),
                soundSystemId = Random.nextLong(),
                multimediaId = Random.nextLong(),
                weddingCar = getRandomString(),
                usherId = Random.nextLong(),
                invitationAmount = Random.nextInt(),
                courierId = Random.nextLong(),
                etc = getRandomString(),
                createdAt = Date(),
                updatedAt = Date(),
                lighting = Lighting.dummy(),
                generator = Generator.dummy(),
                soundSystem = SoundSystem.dummy(),
                multimedia = Multimedia.dummy(),
                usher = Usher.dummy(),
                courier = Courier.dummy()
            )
        }
    }
}
