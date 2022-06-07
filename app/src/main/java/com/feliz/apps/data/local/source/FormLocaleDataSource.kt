package com.feliz.apps.data.local.source

import com.feliz.apps.data.local.AppPreference
import com.feliz.apps.data.local.AppPreference.Companion.KEY_CLIENT_TOKEN
import com.feliz.apps.domain.model.common.AuthError
import com.feliz.apps.domain.model.common.Result
import com.feliz.apps.domain.model.common.Success
import com.feliz.apps.domain.model.common.UnsupportedError
import com.feliz.apps.domain.model.form.Form
import com.feliz.apps.domain.model.form.personaldata.Personal
import com.feliz.apps.domain.model.form.supportvendor.*
import com.feliz.apps.domain.model.form.vendors.MarriageOfficer
import com.feliz.apps.domain.model.form.vendors.ReceptionOfficer
import com.feliz.apps.domain.model.form.vendors.Vendors
import com.feliz.apps.domain.source.FormDataSource

class FormLocaleDataSource(
    private val appPreference: AppPreference
) : FormDataSource {
    override suspend fun getClientToken(): Result<String?> {
        val token = appPreference.get().getString(KEY_CLIENT_TOKEN, null)
        return if (token != null) Success(token) else AuthError("Mohon login terlebih dahulu!")
    }

    override suspend fun getSoundSystemList(token: String): Result<List<SoundSystem>> {
        return UnsupportedError(source = this)
    }

    override suspend fun getGeneratorList(token: String): Result<List<Generator>> {
        return UnsupportedError(source = this)
    }

    override suspend fun getLightingList(token: String): Result<List<Lighting>> {
        return UnsupportedError(source = this)
    }

    override suspend fun getCourierList(token: String): Result<List<Courier>> {
        return UnsupportedError(source = this)
    }

    override suspend fun getUsherList(token: String): Result<List<Usher>> {
        return UnsupportedError(source = this)
    }

    override suspend fun getMultimediaList(token: String): Result<List<Multimedia>> {
        return UnsupportedError(source = this)
    }

    override suspend fun getFormDetailById(token: String, formId: Long): Result<Form?> {
        return UnsupportedError(source = this)
    }

    override suspend fun updateFormById(
        token: String,
        formId: Long,
        personal: Personal,
        marriageOfficer: MarriageOfficer,
        receptionOfficer: ReceptionOfficer,
        vendors: Vendors,
        supportVendor: SupportVendor
    ): Result<Unit> {
        return UnsupportedError(source = this)
    }
}