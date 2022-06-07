package com.feliz.apps.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class CompanyProfileResponse(
    @SerializedName(value = "company") val company: Company? = null,
    @SerializedName(value = "visi") val visions: List<Vision>? = null,
    @SerializedName(value = "misi") val missions: List<Mission>? = null,
    @SerializedName(value = "team") val teams: List<Team>? = null

) {
    data class Company(
        @SerializedName(value = "id") val id: Long? = null,
        @SerializedName(value = "name") val name: String? = null,
        @SerializedName(value = "description") val description: String? = null,
        @SerializedName(value = "instagram") val instagram: String? = null,
        @SerializedName(value = "youtube") val youtube: String? = null,
        @SerializedName(value = "email") val email: String? = null,
        @SerializedName(value = "whatsapp") val whatsapp: String? = null,
        @SerializedName(value = "updated_at") val updatedAt: String? = null
    )

    data class Vision(
        @SerializedName(value = "id") val id: Long? = null,
        @SerializedName(value = "description") val description: String? = null
    )

    data class Mission(
        @SerializedName(value = "id") val id: Long? = null,
        @SerializedName(value = "description") val description: String? = null
    )

    data class Team(
        @SerializedName(value = "id") val id: Long? = null,
        @SerializedName(value = "fullname") val fullname: String? = null,
        @SerializedName(value = "nickname") val nickname: String? = null,
        @SerializedName(value = "position") val position: String? = null,
        @SerializedName(value = "photo") val photo: String? = null
    )
}
