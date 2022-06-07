package com.feliz.apps.data.remote.model.response.mapper.client

import com.feliz.apps.data.remote.model.response.ClientResponse
import com.feliz.apps.domain.model.common.Mapper
import com.feliz.apps.domain.model.form.personaldata.Client
import com.feliz.apps.domain.model.util.DateTimeUtils

object MapperResponseClientLogin : Mapper<Client, ClientResponse> {

    override fun toDomain(model: ClientResponse): Client {
        return Client(
            id = model.id ?: Client.DEFAULT.id,
            fullname = Client.DEFAULT.fullname,
            description = Client.DEFAULT.description,
            username = model.username ?: Client.DEFAULT.username,
            password = model.password ?: Client.DEFAULT.password,
            token = model.token ?: Client.DEFAULT.token,
            felizRepresentativeName = model.representative
                ?: Client.DEFAULT.felizRepresentativeName,
            approvalDate = DateTimeUtils.parseServerDate(model.approvalDate)
                ?: Client.DEFAULT.approvalDate,
            updatedAt = DateTimeUtils.parseServerDate(model.updatedAt) ?: Client.DEFAULT.updatedAt
        )
    }

    override fun fromDomain(model: Client): ClientResponse {
        throw UnsupportedOperationException()
    }
}