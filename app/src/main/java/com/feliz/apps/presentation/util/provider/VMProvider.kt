package com.feliz.apps.presentation.util.provider

import android.content.Context
import com.feliz.apps.presentation.ui.form.detail.FormDetailViewModel
import com.feliz.apps.presentation.ui.form.edit.FormEditViewModel
import com.feliz.apps.presentation.ui.form.login.FormLoginViewModel
import com.feliz.apps.presentation.ui.home.HomeViewModel
import com.feliz.apps.presentation.ui.home.companyprofile.CompanyProfileViewModel
import com.feliz.apps.presentation.ui.home.portfolio.detail.PortfolioViewModel
import com.feliz.apps.presentation.ui.home.portfolio.list.finished.FinishedEventListViewModel
import com.feliz.apps.presentation.ui.home.portfolio.list.upcoming.UpcomingEventListViewModel
import com.feliz.apps.presentation.ui.home.product.detail.ProductDetailViewModel
import com.feliz.apps.presentation.ui.home.product.list.ProductListViewModel
import com.feliz.apps.presentation.ui.home.search.SearchViewModel
import com.feliz.apps.presentation.ui.vendor.VendorViewModel
import com.feliz.apps.presentation.ui.vendor.detail.VendorDetailViewModel
import com.feliz.apps.presentation.ui.vendor.list.VendorListViewModel

object VMProvider {

    fun home(context: Context): HomeViewModel.Factory {
        return HomeViewModel.Factory(
            SourceProvider.home(context = context)
        )
    }

    fun homeSearch(context: Context): SearchViewModel.Factory {
        return SearchViewModel.Factory(SourceProvider.search(context = context))
    }

    fun companyProfile(context: Context): CompanyProfileViewModel.Factory {
        return CompanyProfileViewModel.Factory(SourceProvider.companyProfile(context = context))
    }

    fun productCategory(context: Context): ProductListViewModel.Factory {
        return ProductListViewModel.Factory(SourceProvider.product(context = context))
    }

    fun product(context: Context): ProductDetailViewModel.Factory {
        return ProductDetailViewModel.Factory(SourceProvider.product(context = context))
    }

    fun portfolio(context: Context): PortfolioViewModel.Factory {
        return PortfolioViewModel.Factory(SourceProvider.portfolio(context = context))
    }

    fun upcomingEvent(context: Context): UpcomingEventListViewModel.Factory {
        return UpcomingEventListViewModel.Factory(SourceProvider.portfolio(context = context))
    }

    fun finishedEvent(context: Context): FinishedEventListViewModel.Factory {
        return FinishedEventListViewModel.Factory(SourceProvider.portfolio(context = context))
    }

    fun vendor(context: Context): VendorViewModel.Factory {
        return VendorViewModel.Factory(SourceProvider.vendor(context = context))
    }

    fun vendorList(context: Context): VendorListViewModel.Factory {
        return VendorListViewModel.Factory(SourceProvider.vendor(context = context))
    }

    fun vendorDetail(context: Context): VendorDetailViewModel.Factory {
        return VendorDetailViewModel.Factory(SourceProvider.vendor(context = context))
    }

    fun formDetail(context: Context): FormDetailViewModel.Factory {
        return FormDetailViewModel.Factory(
            SourceProvider.form(context = context),
            SourceProvider.client(context = context)
        )
    }

    fun formLogin(context: Context): FormLoginViewModel.Factory {
        return FormLoginViewModel.Factory(SourceProvider.client(context = context))
    }

    fun formEdit(context: Context): FormEditViewModel.Factory {
        return FormEditViewModel.Factory(SourceProvider.form(context = context))
    }

}