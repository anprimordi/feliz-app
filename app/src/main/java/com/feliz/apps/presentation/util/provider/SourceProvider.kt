package com.feliz.apps.presentation.util.provider

import android.content.Context
import com.feliz.apps.data.local.source.ClientLocaleDataSource
import com.feliz.apps.data.local.source.FormLocaleDataSource
import com.feliz.apps.data.local.source.ProductLocaleDataSource
import com.feliz.apps.data.remote.AppRemoteClient
import com.feliz.apps.data.remote.source.*
import com.feliz.apps.data.repository.*
import com.feliz.apps.domain.source.*

object SourceProvider {

    fun client(context: Context): ClientDataSource {
        return ClientRepository(
            localeDataSource = Locale.client(context),
            remoteDataSource = Remote.client(context)
        )
    }

    fun companyProfile(context: Context): CompanyProfileDataSource {
        return CompanyProfileRepository(
            remoteSource = Remote.companyProfile(context)
        )
    }

    fun form(context: Context): FormDataSource {
        return FormRepository(
            localeSource = Locale.form(context),
            remoteSource = Remote.form(context)
        )
    }

    fun home(context: Context): HomeDataSource {
        return HomeRepository(
            remoteDataSource = Remote.home(context)
        )
    }

    fun portfolio(context: Context): PortfolioDataSource {
        return PortfolioRepository(
            remoteSource = Remote.portfolio(context)
        )
    }

    fun product(context: Context): ProductDataSource {
        return ProductRepository(
            localeSource = Locale.product(context),
            remoteSource = Remote.product(context)
        )
    }

    fun search(context: Context): SearchDataSource {
        return SearchRepository(
            remoteSource = Remote.search(context)
        )
    }

    fun vendor(context: Context): VendorDataSource {
        return VendorRepository(
            remoteSource = Remote.vendor(context)
        )
    }

    private object Remote {

        fun client(context: Context): ClientDataSource {
            return ClientRemoteDataSource(AppProvider.remoteClient(context))
        }

        fun form(context: Context): FormDataSource {
            return FormRemoteDataSource(AppProvider.remoteClient(context))
        }

        fun companyProfile(context: Context): CompanyProfileDataSource {
            return CompanyProfileRemoteDataSource(AppProvider.remoteClient(context))
        }

        fun home(context: Context): HomeDataSource {
            return HomeRemoteDataSource(AppRemoteClient.getInstance(context))
        }

        fun portfolio(context: Context): PortfolioDataSource {
            return PortfolioRemoteDataSource(AppProvider.remoteClient(context))
        }

        fun product(context: Context): ProductDataSource {
            return ProductRemoteDataSource(AppProvider.remoteClient(context))
        }

        fun search(context: Context): SearchDataSource {
            return SearchRemoteDataSource(AppProvider.remoteClient(context))
        }

        fun vendor(context: Context): VendorDataSource {
            return VendorRemoteDataSource(AppProvider.remoteClient(context))
        }

    }

    private object Locale {

        fun client(context: Context): ClientDataSource {
            return ClientLocaleDataSource(AppProvider.preference(context))
        }

        fun form(context: Context): FormDataSource {
            return FormLocaleDataSource(AppProvider.preference(context))
        }

        fun product(context: Context): ProductDataSource {
            return ProductLocaleDataSource(AppProvider.preference(context))
        }
    }
}