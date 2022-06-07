package com.feliz.apps.domain.model

import com.feliz.apps.domain.model.common.Constants
import com.feliz.apps.presentation.util.extensions.getRandomString
import java.util.*
import kotlin.random.Random

data class CompanyProfile(
    val name: String,
    val description: String,
    val bannerPhotoUrl: String,
    val instagram: String?,
    val whatsapp: String?,
    val youtube: String?,
    val email: String?,
    val updatedAt: Date,
    val visions: List<Vision>,
    val missions: List<Mission>,
    val teams: List<Team>
) {

    companion object {
        val DEFAULT = CompanyProfile(
            name = Constants.DEFAULT_NAME,
            description = Constants.DEFAULT_DESCRIPTION,
            bannerPhotoUrl = Constants.DEFAULT_PHOTO_URL,
            instagram = Constants.DEFAULT_URL,
            whatsapp = Constants.DEFAULT_PHONE.toString(),
            youtube = Constants.DEFAULT_URL,
            email = Constants.DEFAULT_EMAIL,
            updatedAt = Constants.DEFAULT_DATE,
            visions = emptyList(),
            missions = emptyList(),
            teams = emptyList()
        )

        fun dummy(): CompanyProfile {
            return CompanyProfile(
                "Feliz",
                "Berdiri pada Tahun 2014, Feliz\n" +
                        "merupakan sebuah Event Consultant dimana kami menawarkan layanan jasa\n" +
                        "seputar event dari event wedding, gathering, corporate, annual event dan\n" +
                        "lain sebagainya.",
                "https://nerdist.com/wp-content/uploads/2020/07/maxresdefault.jpg",
                "https://www.instagram.com/officialrickastley/",
                "6285214157693",
                "https://www.youtube.com/channel/UCuAXFkgsw1L7xaCfnd5JJOw",
                "adhilaga@gmail.com",
                Date(),
                listOf(
                    Vision.dummy(), Vision.dummy(), Vision.dummy()
                ),
                listOf(
                    Mission.dummy(), Mission.dummy(), Mission.dummy()
                ),
                teams = listOf(
                    Team.dummy(),
                    Team.dummy(),
                    Team.dummy()
                )
            )
        }
    }

    data class Mission(
        val id: Long,
        val description: String
    ) {
        companion object {
            val DEFAULT = Mission(
                id = Constants.DEFAULT_ID,
                description = Constants.DEFAULT_DESCRIPTION
            )

            fun dummy(): Mission {
                return Mission(
                    id = Random.nextLong(),
                    description = getRandomString()
                )
            }
        }
    }

    data class Vision(
        val id: Long,
        val description: String
    ) {
        companion object {
            val DEFAULT = Vision(
                id = Constants.DEFAULT_ID,
                description = Constants.DEFAULT_DESCRIPTION
            )

            fun dummy(): Vision {
                return Vision(
                    id = Random.nextLong(),
                    description = getRandomString()
                )
            }
        }
    }

    data class Team(
        val id: Long,
        val fullName: String,
        val nickname: String,
        val position: String,
        val photoUrl: String?
    ) {
        companion object {
            val DEFAULT = Team(
                id = Constants.DEFAULT_ID,
                fullName = Constants.DEFAULT_NAME,
                nickname = Constants.DEFAULT_NAME,
                position = Constants.DEFAULT_DESCRIPTION,
                photoUrl = Constants.DEFAULT_PHOTO_URL
            )

            fun dummy(): Team {
                return Team(
                    id = Random.nextLong(),
                    fullName = "John Doe",
                    nickname = "John",
                    position = getRandomString(),
                    photoUrl = "https://billatowing.com/wp-content/uploads/2016/06/team-1.jpg"
                )
            }
        }
    }
}
