package com.feliz.apps.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class FormResponse(
    @SerializedName(value = "id") val id: Long? = null,
    @SerializedName(value = "username") val username: String? = null,
    @SerializedName(value = "representative_name") val representativeName: String? = null,
    @SerializedName(value = "date_of_approval") val dateOfApproval: String? = null,
    @SerializedName(value = "created_at") val createdAt: String? = null,
    @SerializedName(value = "updated_at") val updatedAt: String? = null,
    @SerializedName(value = "personal") val personal: Personal? = null,
    @SerializedName(value = "reception") val reception: Reception? = null,
    @SerializedName(value = "marriage") val marriage: Marriage? = null,
    @SerializedName(value = "vendor") val vendor: Vendors? = null,
    @SerializedName(value = "support") val support: Support? = null
) {

    data class Personal(
        @SerializedName(value = "id") val id: Long? = null,
        @SerializedName(value = "client_id") val clientId: Long? = null,
        @SerializedName(value = "fullname") val fullname: String? = null,
        @SerializedName(value = "description") val description: String? = null,
        @SerializedName(value = "groom_name") val groomName: String? = null,
        @SerializedName(value = "bride_name") val brideName: String? = null,
        @SerializedName(value = "groom_father_name") val groomFatherName: String? = null,
        @SerializedName(value = "groom_mother_name") val groomMotherName: String? = null,
        @SerializedName(value = "bride_father_name") val brideFatherName: String? = null,
        @SerializedName(value = "bride_mother_name") val brideMotherName: String? = null,
        @SerializedName(value = "groom_family_name") val groomFamilyName: String? = null,
        @SerializedName(value = "bride_family_name") val brideFamilyName: String? = null,
        @SerializedName(value = "created_at") val createdAt: String? = null,
        @SerializedName(value = "updated_at") val updatedAt: String? = null
    )

    data class Reception(
        @SerializedName(value = "id") val id: Long? = null,
        @SerializedName(value = "client_id") val clientId: Long? = null,
        @SerializedName(value = "family_co_name") val familyCoName: String? = null,
        @SerializedName(value = "family_co_phone") val familyCoPhone: String? = null,
        @SerializedName(value = "guest_co_name") val guestCoName: String? = null,
        @SerializedName(value = "guest_co_phone") val guestCoPhone: String? = null,
        @SerializedName(value = "vip_guest_co_name") val vipGuestCoName: String? = null,
        @SerializedName(value = "vip_guest_co_phone") val vipGuestCoPhone: String? = null,
        @SerializedName(value = "gift_co_name") val giftCoName: String? = null,
        @SerializedName(value = "gift_co_phone") val giftCoPhone: String? = null,
        @SerializedName(value = "souvenir_co_name") val souvenirCoName: String? = null,
        @SerializedName(value = "souvenir_co_phone") val souvenirCoPhone: String? = null,
        @SerializedName(value = "guest_officer") val guestOfficer: String? = null,
        @SerializedName(value = "guest_book_officer") val guestBookOfficer: String? = null,
        @SerializedName(value = "souvenir_officer") val souvenirOfficer: String? = null,
        @SerializedName(value = "created_at") val createdAt: String? = null,
        @SerializedName(value = "updated_at") val updatedAt: String? = null,
    )

    data class Marriage(
        @SerializedName(value = "id") val id: Long? = null,
        @SerializedName(value = "client_id") val clientId: Long? = null,
        @SerializedName(value = "book_reader") val bookReader: String? = null,
        @SerializedName(value = "groom_witness") val groomWitness: String? = null,
        @SerializedName(value = "bride_witness") val brideWitness: String? = null,
        @SerializedName(value = "master_ceremony") val masterCeremony: String? = null,
        @SerializedName(value = "tausiyah_officer") val tausiyahOfficer: String? = null,
        @SerializedName(value = "prayer_officer") val prayerOfficer: String? = null,
        @SerializedName(value = "created_at") val createdAt: String? = null,
        @SerializedName(value = "updated_at") val updatedAt: String? = null
    )

    data class Vendors(
        @SerializedName(value = "id") val id: Long? = null,
        @SerializedName(value = "client_id") val clientId: Long? = null,
        @SerializedName(value = "venue_name") val venueName: String? = null,
        @SerializedName(value = "decoration_name") val decorationName: List<String>? = null,
        @SerializedName(value = "decoration_desc") val decorationDesc: List<String>? = null,
        @SerializedName(value = "catering_name") val cateringName: List<String>? = null,
        @SerializedName(value = "catering_desc") val cateringDesc: List<String>? = null,
        @SerializedName(value = "makeup_name") val makeupName: List<String>? = null,
        @SerializedName(value = "makeup_desc") val makeupDesc: List<String>? = null,
        @SerializedName(value = "documentation_name") val documentationName: List<String>? = null,
        @SerializedName(value = "documentation_desc") val documentationDesc: List<String>? = null,
        @SerializedName(value = "entertaint_name") val entertainName: List<String>? = null,
        @SerializedName(value = "entertaint_desc") val entertainDesc: List<String>? = null,
        @SerializedName(value = "mc_show_name") val mcShowName: List<String>? = null,
        @SerializedName(value = "mc_show_desc") val mcShowDesc: List<String>? = null,
        @SerializedName(value = "wedding_dress_name") val weddingDressName: List<String>? = null,
        @SerializedName(value = "wedding_dress_desc") val weddingDressDesc: List<String>? = null,
        @SerializedName(value = "accessories_name") val accessoriesName: List<String>? = null,
        @SerializedName(value = "accessories_desc") val accessoriesDesc: List<String>? = null,
        @SerializedName(value = "family_dress_name") val familyDressName: List<String>? = null,
        @SerializedName(value = "family_dress_desc") val familyDressDesc: List<String>? = null,
        @SerializedName(value = "created_at") val createdAt: String? = null,
        @SerializedName(value = "updated_at") val updatedAt: String? = null
    )

    data class Support(
        @SerializedName(value = "id") val id: Long? = null,
        @SerializedName(value = "client_id") val clientId: Long? = null,
        @SerializedName(value = "lighting_effect_id") val lightingId: Long? = null,
        @SerializedName(value = "generator_id") val generatorId: Long? = null,
        @SerializedName(value = "sound_system_id") val soundSystemId: Long? = null,
        @SerializedName(value = "multimedia_id") val multimediaId: Long? = null,
        @SerializedName(value = "wedding_car") val weddingCar: String? = null,
        @SerializedName(value = "usher_id") val usherId: Long? = null,
        @SerializedName(value = "invitation_amount") val invitationAmount: Long? = null,
        @SerializedName(value = "courier_id") val courierId: Long? = null,
        @SerializedName(value = "etc") val etc: String? = null,
        @SerializedName(value = "created_at") val createdAt: String? = null,
        @SerializedName(value = "updated_at") val updatedAt: String? = null,
        @SerializedName(value = "sound") val soundSystem: SupportVendors? = null,
        @SerializedName(value = "generator") val generator: SupportVendors? = null,
        @SerializedName(value = "lighting") val lighting: SupportVendors? = null,
        @SerializedName(value = "courier") val courier: SupportVendors? = null,
        @SerializedName(value = "usher") val usher: SupportVendors? = null,
        @SerializedName(value = "multimedia") val multimedia: SupportVendors? = null
    ) {
        data class SupportVendors(
            @SerializedName(value = "id") val id: Long? = null,
            @SerializedName(value = "name") val name: String? = null,
            @SerializedName(value = "description") val description: String? = null,
            @SerializedName(value = "created_at") val createdAt: String? = null,
            @SerializedName(value = "updated_at") val updatedAt: String? = null
        )
    }


}