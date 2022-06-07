package com.feliz.apps.domain.model.form.vendors

import com.feliz.apps.domain.model.common.Constants
import com.feliz.apps.presentation.util.extensions.getRandomString
import kotlin.random.Random

data class Vendors(
    val id: Long,
    val clientId: Long,
    val venueName: String,
    val decorationList: ArrayList<Pair<String, String>>,
    val cateringList: ArrayList<Pair<String, String>>,
    val makeupList: ArrayList<Pair<String, String>>,
    val documentationList: ArrayList<Pair<String, String>>,
    val entertainList: ArrayList<Pair<String, String>>,
    val mcShowList: ArrayList<Pair<String, String>>,
    val weddingDressList: ArrayList<Pair<String, String>>,
    val accessoriesList: ArrayList<Pair<String, String>>,
    val staffDressList: ArrayList<Pair<String, String>>
) {
    companion object {

        val DEFAULT = Vendors(
            id = Constants.DEFAULT_ID,
            clientId = Constants.DEFAULT_ID,
            venueName = Constants.DEFAULT_NAME,
            decorationList = arrayListOf(),
            cateringList = arrayListOf(),
            makeupList = arrayListOf(),
            documentationList = arrayListOf(),
            entertainList = arrayListOf(),
            mcShowList = arrayListOf(),
            weddingDressList = arrayListOf(),
            accessoriesList = arrayListOf(),
            staffDressList = arrayListOf()
        )

        fun dummy(): Vendors {
            return Vendors(
                id = Random.nextLong(),
                clientId = Random.nextLong(),
                venueName = getRandomString(),
                decorationList = arrayListOf(
                    Pair(getRandomString(), getRandomString()),
                    Pair(getRandomString(), getRandomString()),
                    Pair(getRandomString(), getRandomString())
                ),
                cateringList = arrayListOf(
                    Pair(getRandomString(), getRandomString()),
                    Pair(getRandomString(), getRandomString()),
                    Pair(getRandomString(), getRandomString())
                ),
                makeupList = arrayListOf(
                    Pair(getRandomString(), getRandomString()),
                    Pair(getRandomString(), getRandomString()),
                    Pair(getRandomString(), getRandomString())
                ),
                documentationList = arrayListOf(
                    Pair(getRandomString(), getRandomString()),
                    Pair(getRandomString(), getRandomString()),
                    Pair(getRandomString(), getRandomString())
                ),
                entertainList = arrayListOf(
                    Pair(getRandomString(), getRandomString()),
                    Pair(getRandomString(), getRandomString()),
                    Pair(getRandomString(), getRandomString())
                ),
                mcShowList = arrayListOf(
                    Pair(getRandomString(), getRandomString()),
                    Pair(getRandomString(), getRandomString()),
                    Pair(getRandomString(), getRandomString())
                ),
                weddingDressList = arrayListOf(
                    Pair(getRandomString(), getRandomString()),
                    Pair(getRandomString(), getRandomString()),
                    Pair(getRandomString(), getRandomString())
                ),
                accessoriesList = arrayListOf(
                    Pair(getRandomString(), getRandomString()),
                    Pair(getRandomString(), getRandomString()),
                    Pair(getRandomString(), getRandomString())
                ),
                staffDressList = arrayListOf(
                    Pair(getRandomString(), getRandomString()),
                    Pair(getRandomString(), getRandomString()),
                    Pair(getRandomString(), getRandomString())
                )
            )
        }
    }
}