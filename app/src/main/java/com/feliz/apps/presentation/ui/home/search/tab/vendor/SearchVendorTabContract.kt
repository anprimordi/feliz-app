package com.feliz.apps.presentation.ui.home.search.tab.vendor

import com.feliz.apps.domain.model.Vendor

interface SearchVendorTabContract {
    interface View {
        fun showSearchVendorPage(list: List<Vendor>)
        fun showSearchResult(vendorList: List<Vendor>, query: String)
        fun showLoadingIndicator(isActive: Boolean)
        fun showEmptyMessageDialog(query: String)
        fun showEmptyVendorListMessage(message: String)
        fun showErrorMessageDialog(message: String)
        fun openVendorPage(vendor: Vendor)
    }
}