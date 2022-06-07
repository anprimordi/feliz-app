package com.feliz.apps.data.local

import android.content.Context
import android.content.SharedPreferences
import com.feliz.apps.BuildConfig

class AppPreference private constructor(context: Context) {

    companion object {
        private const val PREFERENCE_NAME = BuildConfig.PREF_NAME

        //        client
        const val KEY_CLIENT_TOKEN = "key_string_client_token"
        const val KEY_CLIENT_ID = "key_long_client_id"
        const val KEY_CLIENT_FULLNAME = "key_string_client_fullname"
        const val KEY_CLIENT_DESCRIPTION = "key_string_client_description"
        const val KEY_CLIENT_USERNAME = "key_string_client_username"
        const val KEY_CLIENT_PASSWORD = "key_string_client_password"
        const val KEY_CLIENT_REPRESENTATIVE = "key_string_client_representative"
        const val KEY_CLIENT_APPROVAL_DATE = "key_string_client_approval_date"
        const val KEY_CLIENT_UPDATED_AT = "key_string_client_updated_at"

        //        product
        const val KEY_PRODUCT_CATEGORY_NAME = "key_string_product_category_name"

        @Volatile
        private var instance: AppPreference? = null

        fun getInstance(context: Context): AppPreference {
            return instance ?: synchronized(this) {
                instance ?: AppPreference(context).also { instance = it }
            }
        }
    }

    private val sharedPref = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun get(): SharedPreferences {
        return sharedPref
    }

    fun editor(): SharedPreferences.Editor {
        return sharedPref.edit()
    }

    fun clear() {
        editor().clear().apply()
    }

}