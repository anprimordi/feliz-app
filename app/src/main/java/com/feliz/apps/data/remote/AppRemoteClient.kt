package com.feliz.apps.data.remote

import android.content.Context
import com.feliz.apps.BuildConfig
import com.feliz.apps.data.local.AppPreference.Companion.KEY_CLIENT_TOKEN
import com.feliz.apps.data.remote.model.interceptor.Authorization
import com.feliz.apps.domain.model.common.*
import com.feliz.apps.presentation.util.provider.AppProvider
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.IOException
import java.util.concurrent.TimeUnit

class AppRemoteClient private constructor(context: Context) {

    companion object {
        const val API_URL = "${BuildConfig.SERVER_URL}/api/"

        @Volatile
        private var sInstance: AppRemoteClient? = null

        fun getInstance(context: Context): AppRemoteClient {
            return sInstance ?: synchronized(this) {
                sInstance ?: AppRemoteClient(context).also { sInstance = it }
            }
        }
    }

    private val preference = AppProvider.preference(context)
    private val chuck = ChuckInterceptor(context)
    private val gson = GsonBuilder().setLenient().create()

    fun <T> create(clazz: Class<T>): T {
        return Retrofit.Builder()
            .baseUrl(API_URL)
            .client(buildClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(clazz)
    }

    suspend fun <T> execute(call: suspend () -> T): Result<T> {
        return try {
            val response = call()
            Success(response)
        } catch (ex: IOException) {
            Timber.e(ex)
            NetworkError()
        } catch (ex: HttpException) {
            Timber.e(ex)
            NotFoundError()
        } catch (ex: Exception) {
            Timber.e(ex)
            Error.general(ex)
        }
    }

    private fun buildClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.BASIC

        val builder = OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor(chuck)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)

        val token = preference.get().getString(KEY_CLIENT_TOKEN, null)
        if (token != null) builder.addInterceptor(Authorization(token))

        return builder.build()
    }

}