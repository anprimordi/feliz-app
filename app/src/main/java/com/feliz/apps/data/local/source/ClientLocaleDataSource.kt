package com.feliz.apps.data.local.source

import com.feliz.apps.data.local.AppPreference
import com.feliz.apps.data.local.AppPreference.Companion.KEY_CLIENT_APPROVAL_DATE
import com.feliz.apps.data.local.AppPreference.Companion.KEY_CLIENT_DESCRIPTION
import com.feliz.apps.data.local.AppPreference.Companion.KEY_CLIENT_FULLNAME
import com.feliz.apps.data.local.AppPreference.Companion.KEY_CLIENT_ID
import com.feliz.apps.data.local.AppPreference.Companion.KEY_CLIENT_PASSWORD
import com.feliz.apps.data.local.AppPreference.Companion.KEY_CLIENT_REPRESENTATIVE
import com.feliz.apps.data.local.AppPreference.Companion.KEY_CLIENT_TOKEN
import com.feliz.apps.data.local.AppPreference.Companion.KEY_CLIENT_UPDATED_AT
import com.feliz.apps.data.local.AppPreference.Companion.KEY_CLIENT_USERNAME
import com.feliz.apps.domain.model.common.AuthError
import com.feliz.apps.domain.model.common.Result
import com.feliz.apps.domain.model.common.Success
import com.feliz.apps.domain.model.common.UnsupportedError
import com.feliz.apps.domain.model.form.personaldata.Client
import com.feliz.apps.domain.model.util.DateTimeUtils
import com.feliz.apps.domain.source.ClientDataSource
import java.util.*

class ClientLocaleDataSource(
    private val appPreference: AppPreference
) : ClientDataSource {
    override suspend fun login(username: String, password: String): Result<Client?> {
        return UnsupportedError(source = this)
    }

    override suspend fun logout(): Result<Unit> {
        appPreference.editor().apply {
            remove(KEY_CLIENT_TOKEN)
            remove(KEY_CLIENT_ID)
            remove(KEY_CLIENT_FULLNAME)
            remove(KEY_CLIENT_DESCRIPTION)
            remove(KEY_CLIENT_USERNAME)
            remove(KEY_CLIENT_PASSWORD)
            remove(KEY_CLIENT_REPRESENTATIVE)
            remove(KEY_CLIENT_APPROVAL_DATE)
            remove(KEY_CLIENT_UPDATED_AT)
        }.apply()
        return Success(Unit)
    }

    override suspend fun getClientDetailById(token: String, clientId: Long): Result<Client?> {
        return UnsupportedError(source = this)
    }

    override suspend fun setClientToken(token: String): Result<Unit> {
        appPreference.editor().putString(KEY_CLIENT_TOKEN, token).apply()
        return Success(Unit)
    }

    override suspend fun getClientToken(): Result<String?> {
        val token = appPreference.get().getString(KEY_CLIENT_TOKEN, null)
        return if (token != null) Success(token) else AuthError("Mohon login terlebih dahulu")
    }

    override fun saveClientData(client: Client): Result<Unit> {

        appPreference.editor().apply {
            putLong(KEY_CLIENT_ID, client.id)
            putString(KEY_CLIENT_FULLNAME, client.fullname)
            putString(KEY_CLIENT_DESCRIPTION, client.description)
            putString(KEY_CLIENT_USERNAME, client.username)
            putString(KEY_CLIENT_PASSWORD, client.password)
            putString(KEY_CLIENT_TOKEN, client.token)
            putString(KEY_CLIENT_REPRESENTATIVE, client.felizRepresentativeName)
            putString(KEY_CLIENT_APPROVAL_DATE, DateTimeUtils.formatDateTime(client.approvalDate))
            putString(KEY_CLIENT_UPDATED_AT, DateTimeUtils.formatDateTime(client.updatedAt))
        }.apply()

        return Success(Unit)
    }

    override fun getClientDetailData(): Result<Client?> {

        val id = appPreference.get().getLong(KEY_CLIENT_ID, -1)
        val fullname = appPreference.get().getString(KEY_CLIENT_FULLNAME, null)
        val description = appPreference.get().getString(KEY_CLIENT_DESCRIPTION, null)
        val username = appPreference.get().getString(KEY_CLIENT_USERNAME, null)
        val password = appPreference.get().getString(KEY_CLIENT_PASSWORD, null)
        val token = appPreference.get().getString(KEY_CLIENT_TOKEN, null)
        val representativeName = appPreference.get().getString(KEY_CLIENT_REPRESENTATIVE, null)
        val approvalDate = appPreference.get().getString(KEY_CLIENT_APPROVAL_DATE, null)
        val updatedAt = appPreference.get().getString(KEY_CLIENT_UPDATED_AT, null)


        if (!id.equals(-1) && !fullname.isNullOrBlank()) {
            val client = Client(
                id = id,
                fullname = fullname,
                description = description ?: Client.DEFAULT.description,
                username = username ?: Client.DEFAULT.username,
                password = password ?: Client.DEFAULT.password,
                token = token ?: Client.DEFAULT.token,
                felizRepresentativeName = representativeName
                    ?: Client.DEFAULT.felizRepresentativeName,
                approvalDate = DateTimeUtils.parseServerDate(approvalDate) ?: Date(),
                updatedAt = DateTimeUtils.parseServerDate(updatedAt) ?: Date()
            )

            return Success(client)
        } else {
            return AuthError()
        }
    }
}