package com.feliz.apps.presentation.util.provider

import android.content.Context
import com.feliz.apps.data.local.AppPreference
import com.feliz.apps.data.remote.AppRemoteClient

object AppProvider {

    fun preference(context: Context): AppPreference {
        return AppPreference.getInstance(context)
    }

    fun remoteClient(context: Context): AppRemoteClient {
        return AppRemoteClient.getInstance(context)
    }

}