package com.feliz.apps.domain.source

import com.feliz.apps.domain.model.common.Result
import com.feliz.apps.domain.model.form.Form
import com.feliz.apps.domain.model.form.personaldata.Personal
import com.feliz.apps.domain.model.form.supportvendor.*
import com.feliz.apps.domain.model.form.vendors.MarriageOfficer
import com.feliz.apps.domain.model.form.vendors.ReceptionOfficer
import com.feliz.apps.domain.model.form.vendors.Vendors

interface FormDataSource {
    suspend fun getClientToken(): Result<String?>
    suspend fun getSoundSystemList(token: String): Result<List<SoundSystem>>
    suspend fun getGeneratorList(token: String): Result<List<Generator>>
    suspend fun getLightingList(token: String): Result<List<Lighting>>
    suspend fun getCourierList(token: String): Result<List<Courier>>
    suspend fun getUsherList(token: String): Result<List<Usher>>
    suspend fun getMultimediaList(token: String): Result<List<Multimedia>>
    suspend fun getFormDetailById(token: String, formId: Long): Result<Form?>
    suspend fun updateFormById(
        token: String,
        formId: Long,
        personal: Personal,
        marriageOfficer: MarriageOfficer,
        receptionOfficer: ReceptionOfficer,
        vendors: Vendors,
        supportVendor: SupportVendor
    ): Result<Unit>
}