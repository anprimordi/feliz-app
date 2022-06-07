package com.feliz.apps.presentation.ui.vendor.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.feliz.apps.domain.model.Vendor
import com.feliz.apps.domain.model.common.Event

interface VendorListContract {
    interface View {
        fun showVendorCategoryPage(categoryName: String)
        fun showVendorListPage(list: List<Vendor>)
        fun showSearchResult(list: List<Vendor>, query: String)
        fun showEmptyResult(query: String)
        fun showLoadingIndicator(isActive: Boolean)
        fun showEmptyMessage(message: String)
        fun showErrorMessage(message: String)
        fun openVendorDetailPage(vendor: Vendor)
    }

    interface Presenter {
        val vendorCategoryObservable: LiveData<String>
        val vendorListObservable: LiveData<List<Vendor>>
        val queryObservable: MutableLiveData<String>
        val stateLoadingObservable: LiveData<Boolean>
        val eventShowEmptyMessageObservable: LiveData<Event<String>>
        val eventLoadingErrorObservable: LiveData<Event<String>>
        val eventOpenVendorDetailPageObservable: LiveData<Event<Vendor>>

        fun loadVendorCategory(name: String)
        fun loadVendorList(id: Long)
    }
}