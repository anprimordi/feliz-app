package com.feliz.apps.data.remote.model.response.mapper.company

import com.feliz.apps.data.remote.model.response.CompanyProfileResponse
import com.feliz.apps.domain.model.CompanyProfile
import com.feliz.apps.domain.model.common.Mapper
import com.feliz.apps.domain.model.util.DateTimeUtils

object MapperResponseCompanyProfile : Mapper<CompanyProfile, CompanyProfileResponse> {
    override fun toDomain(model: CompanyProfileResponse): CompanyProfile {
        return CompanyProfile(
            name = model.company?.name ?: CompanyProfile.DEFAULT.name,
            description = model.company?.description ?: CompanyProfile.DEFAULT.description,
            bannerPhotoUrl = CompanyProfile.DEFAULT.bannerPhotoUrl,
            instagram = model.company?.instagram ?: CompanyProfile.DEFAULT.instagram,
            youtube = model.company?.youtube ?: CompanyProfile.DEFAULT.youtube,
            whatsapp = model.company?.whatsapp ?: CompanyProfile.DEFAULT.whatsapp,
            email = model.company?.email ?: CompanyProfile.DEFAULT.email,
            updatedAt = DateTimeUtils.parseServerDate(model.company?.updatedAt)
                ?: CompanyProfile.DEFAULT.updatedAt,
            visions = parseCompanyProfileVision(model.visions) ?: emptyList(),
            missions = parseCompanyProfileMission(model.missions) ?: emptyList(),
            teams = parseCompanyProfileTeam(model.teams) ?: emptyList()
        )
    }

    override fun fromDomain(model: CompanyProfile): CompanyProfileResponse {
        throw UnsupportedOperationException()
    }

    private fun parseCompanyProfileVision(visions: List<CompanyProfileResponse.Vision>?): List<CompanyProfile.Vision> {
        val list = arrayListOf<CompanyProfile.Vision>()
        visions?.forEach {
            list.add(
                CompanyProfile.Vision(
                    id = it.id ?: CompanyProfile.Vision.DEFAULT.id,
                    description = it.description ?: CompanyProfile.Vision.DEFAULT.description
                )
            )
        }
        return list
    }

    private fun parseCompanyProfileMission(missions: List<CompanyProfileResponse.Mission>?): List<CompanyProfile.Mission> {
        val list = arrayListOf<CompanyProfile.Mission>()
        missions?.forEach {
            list.add(
                CompanyProfile.Mission(
                    id = it.id ?: CompanyProfile.Mission.DEFAULT.id,
                    description = it.description ?: CompanyProfile.Vision.DEFAULT.description
                )
            )
        }
        return list
    }

    private fun parseCompanyProfileTeam(teams: List<CompanyProfileResponse.Team>?): List<CompanyProfile.Team> {
        val list = arrayListOf<CompanyProfile.Team>()
        teams?.forEach {
            list.add(
                CompanyProfile.Team(
                    id = it.id ?: CompanyProfile.Team.DEFAULT.id,
                    fullName = it.fullname ?: CompanyProfile.Team.DEFAULT.fullName,
                    nickname = it.nickname ?: CompanyProfile.Team.DEFAULT.nickname,
                    photoUrl = it.photo ?: CompanyProfile.Team.DEFAULT.photoUrl,
                    position = it.position ?: CompanyProfile.Team.DEFAULT.position
                )
            )
        }
        return list
    }
}