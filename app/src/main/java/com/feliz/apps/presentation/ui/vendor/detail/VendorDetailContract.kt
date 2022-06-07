package com.feliz.apps.presentation.ui.vendor.detail

import androidx.lifecycle.LiveData
import com.feliz.apps.domain.model.Vendor
import com.feliz.apps.domain.model.common.Event

interface VendorDetailContract {

    interface View {
        fun showVendorDetailPage(vendor: Vendor)
        fun showMoreVendorList(vendorList: List<Vendor>)
        fun showVendorDetailBanner(vendor: Vendor)
        fun showLoadingIndicator(isActive: Boolean)
        fun showErrorMessage(message: String)
        fun openMoreVendorPage(vendor: Vendor)
        fun openDirection(location: Pair<Double, Double>)
    }

    interface Presenter {
        val vendorObservable: LiveData<Vendor>
        val moreVendorObservable: LiveData<List<Vendor>>
        val stateLoadingObservable: LiveData<Boolean>
        val eventLoadingErrorObservable: LiveData<Event<String>>
        val eventOpenMoreVendorPageObservable: LiveData<Event<Vendor>>
        val eventOpenDirectionObservable: LiveData<Event<Pair<Double, Double>>>

        fun loadDetailVendor(vendorId: Long)
        fun loadMoreVendor(categoryId: Long)

        fun triggerOpenDirection()
    }
}