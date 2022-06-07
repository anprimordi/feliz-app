package com.feliz.apps.data.remote.model.request.mapper

import com.feliz.apps.data.remote.model.request.ClientRequest

object MapperRequestClientLogin {

    fun fromDomain(
        username: String,
        password: String
    ): ClientRequest.Login {
        return ClientRequest.Login(
            username = username,
            password = password
        )
    }
}