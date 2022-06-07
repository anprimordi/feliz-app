package com.feliz.apps.data.remote.source

import com.feliz.apps.data.remote.AppRemoteClient
import com.feliz.apps.data.remote.model.request.mapper.MapperRequestFormUpdate
import com.feliz.apps.data.remote.model.response.mapper.form.*
import com.feliz.apps.data.remote.service.FormService
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FormRemoteDataSource(
    private val client: AppRemoteClient
) : FormDataSource {
    override suspend fun getClientToken(): Result<String?> {
        return UnsupportedError(source = this)
    }

    override suspend fun getSoundSystemList(token: String): Result<List<SoundSystem>> {
        return withContext(Dispatchers.IO) {
            client.execute {
                val service = client.create(FormService::class.java)
                service.getSoundSystemList(token)
            }.mapTo {
                Success(MapperResponseFormSoundSystem.toDomainList(it.data!!))
            }
        }
    }

    override suspend fun getGeneratorList(token: String): Result<List<Generator>> {
        return withContext(Dispatchers.IO) {
            client.execute {
                val service = client.create(FormService::class.java)
                service.getGeneratorList(token)
            }.mapTo {
                Success(MapperResponseFormGenerator.toDomainList(it.data!!))
            }
        }
    }

    override suspend fun getLightingList(token: String): Result<List<Lighting>> {
        return withContext(Dispatchers.IO) {
            client.execute {
                val service = client.create(FormService::class.java)
                service.getLightingList(token)
            }.mapTo {
                Success(MapperResponseFormLighting.toDomainList(it.data!!))
            }
        }
    }

    override suspend fun getCourierList(token: String): Result<List<Courier>> {
        return withContext(Dispatchers.IO) {
            client.execute {
                val service = client.create(FormService::class.java)
                service.getCourierList(token)
            }.mapTo {
                Success(MapperResponseFormCourier.toDomainList(it.data!!))
            }
        }
    }

    override suspend fun getUsherList(token: String): Result<List<Usher>> {
        return withContext(Dispatchers.IO) {
            client.execute {
                val service = client.create(FormService::class.java)
                service.getUsherList(token)
            }.mapTo {
                Success(MapperResponseFormUsher.toDomainList(it.data!!))
            }
        }
    }

    override suspend fun getMultimediaList(token: String): Result<List<Multimedia>> {
        return withContext(Dispatchers.IO) {
            client.execute {
                val service = client.create(FormService::class.java)
                service.getMultimediaList(token)
            }.mapTo {
                Success(MapperResponseFormMultimedia.toDomainList(it.data!!))
            }
        }
    }

    override suspend fun getFormDetailById(token: String, formId: Long): Result<Form?> {
        return withContext(Dispatchers.IO) {
            client.execute {
                val service = client.create(FormService::class.java)
                service.getFormDetailById(token, formId)
            }.map {
                val data = it.data
                if (data != null) MapperResponseForm.toDomain(data)
                else null
            }
        }
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
        return withContext(Dispatchers.IO) {
            client.execute {
                val service = client.create(FormService::class.java)
                val request = MapperRequestFormUpdate.fromDomain(
                    personal,
                    receptionOfficer,
                    marriageOfficer,
                    vendors,
                    supportVendor
                )
                service.updateFormById(token, request, formId)
            }
        }
    }
}