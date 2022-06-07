package com.feliz.apps.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class FormUpdateRequest(
    val personal: Personal,
    val reception: Reception,
    val marriage: Marriage,
    val vendor: Vendor,
    val support: Support
) {

    data class Personal(
        @SerializedName(value = "fullname") val fullName: String,
        @SerializedName(value = "description") val description: String,
        @SerializedName(value = "groom_name") val groomName: String,
        @SerializedName(value = "bride_name") val brideName: String,
        @SerializedName(value = "groom_father_name") val groomFatherName: String,
        @SerializedName(value = "groom_mother_name") val groomMotherName: String,
        @SerializedName(value = "bride_father_name") val brideFatherName: String,
        @SerializedName(value = "bride_mother_name") val brideMotherName: String,
        @SerializedName(value = "groom_family_name") val groomFamilyName: String,
        @SerializedName(value = "bride_family_name") val brideFamilyName: String
    )

    data class Reception(
        @SerializedName(value = "family_co_name") val familyCoName: String,
        @SerializedName(value = "family_co_phone") val familyCoPhone: String,
        @SerializedName(value = "guest_co_name") val guestCoName: String,
        @SerializedName(value = "guest_co_phone") val guestCoPhone: String,
        @SerializedName(value = "vip_guest_co_name") val vipGuestCoName: String,
        @SerializedName(value = "vip_guest_co_phone") val vipGuestCoPhone: String,
        @SerializedName(value = "gift_co_name") val giftCoName: String,
        @SerializedName(value = "gift_co_phone") val giftCoPhone: String,
        @SerializedName(value = "souvenir_co_name") val souvenirCoName: String,
        @SerializedName(value = "souvenir_co_phone") val souvenirCoPhone: String,
        @SerializedName(value = "guest_officer") val guestOfficer: String,
        @SerializedName(value = "guest_book_officer") val guestBookOfficer: String,
        @SerializedName(value = "souvenir_officer") val souvenirOfficer: String
    )

    data class Marriage(
        @SerializedName(value = "book_reader") val bookReader: String,
        @SerializedName(value = "groom_witness") val groomWitness: String,
        @SerializedName(value = "bride_witness") val brideWitness: String,
        @SerializedName(value = "master_ceremony") val masterCeremony: String,
        @SerializedName(value = "tausiyah_officer") val tausiyahOfficer: String,
        @SerializedName(value = "prayer_officer") val prayerOfficer: String
    )

    data class Vendor(
        @SerializedName(value = "venue_name") val venueName: String,
        @SerializedName(value = "decoration_name") val decorationName: String,
        @SerializedName(value = "decoration_desc") val decorationDesc: String,
        @SerializedName(value = "catering_name") val cateringName: String,
        @SerializedName(value = "catering_desc") val cateringDesc: String,
        @SerializedName(value = "makeup_name") val makeupName: String,
        @SerializedName(value = "makeup_desc") val makeupDesc: String,
        @SerializedName(value = "documentation_name") val documentationName: String,
        @SerializedName(value = "documentation_desc") val documentationDesc: String,
        @SerializedName(value = "entertaint_name") val entertainName: String,
        @SerializedName(value = "entertaint_desc") val entertainDesc: String,
        @SerializedName(value = "mc_show_name") val mcShowName: String,
        @SerializedName(value = "mc_show_desc") val mcShowDesc: String,
        @SerializedName(value = "wedding_dress_name") val weddingDressName: String,
        @SerializedName(value = "wedding_dress_desc") val weddingDressDesc: String,
        @SerializedName(value = "accessories_name") val accessoriesName: String,
        @SerializedName(value = "accessories_desc") val accessoriesDesc: String,
        @SerializedName(value = "family_dress_name") val familyDressName: String,
        @SerializedName(value = "family_dress_desc") val familyDressDesc: String
    )

    data class Support(
        @SerializedName(value = "lighting_effect_id") val lightingId: Long,
        @SerializedName(value = "generator_id") val generatorId: Long,
        @SerializedName(value = "sound_system_id") val soundSystemId: Long,
        @SerializedName(value = "multimedia_id") val multimediaId: Long,
        @SerializedName(value = "wedding_car") val weddingCar: String,
        @SerializedName(value = "usher_id") val usherId: Long,
        @SerializedName(value = "invitation_amount") val invitationAmount: Int,
        @SerializedName(value = "courier_id") val courierId: Long,
        @SerializedName(value = "etc") val etc: String
    )
}