package com.feliz.apps.domain.source

import com.feliz.apps.domain.model.Home
import com.feliz.apps.domain.model.common.Result

interface HomeDataSource {
    suspend fun getHome(): Result<Home?>
}