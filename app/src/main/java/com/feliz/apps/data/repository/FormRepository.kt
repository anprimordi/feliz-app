package com.feliz.apps.data.repository

import com.feliz.apps.domain.model.common.Result
import com.feliz.apps.domain.model.form.Form
import com.feliz.apps.domain.model.form.personaldata.Personal
import com.feliz.apps.domain.model.form.supportvendor.*
import com.feliz.apps.domain.model.form.vendors.MarriageOfficer
import com.feliz.apps.domain.model.form.vendors.ReceptionOfficer
import com.feliz.apps.domain.model.form.vendors.Vendors
import com.feliz.apps.domain.source.FormDataSource

class FormRepository(
    private val localeSource: FormDataSource,
    private val remoteSource: FormDataSource
) : FormDataSource {
    override suspend fun getClientToken(): Result<String?> {
        return localeSource.getClientToken()
    }

    override suspend fun getSoundSystemList(token: String): Result<List<SoundSystem>> {
        return remoteSource.getSoundSystemList(token)
    }

    override suspend fun getGeneratorList(token: String): Result<List<Generator>> {
        return remoteSource.getGeneratorList(token)
    }

    override suspend fun getLightingList(token: String): Result<List<Lighting>> {
        return remoteSource.getLightingList(token)
    }

    override suspend fun getCourierList(token: String): Result<List<Courier>> {
        return remoteSource.getCourierList(token)
    }

    override suspend fun getUsherList(token: String): Result<List<Usher>> {
        return remoteSource.getUsherList(token)
    }

    override suspend fun getMultimediaList(token: String): Result<List<Multimedia>> {
        return remoteSource.getMultimediaList(token)
    }

    override suspend fun getFormDetailById(token: String, formId: Long): Result<Form?> {
        return remoteSource.getFormDetailById(token, formId)
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
        return remoteSource.updateFormById(
            token, formId, personal, marriageOfficer, receptionOfficer, vendors, supportVendor
        )
    }

}