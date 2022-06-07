package com.feliz.apps.presentation.ui.vendor

import androidx.lifecycle.LiveData
import com.feliz.apps.domain.model.VendorCategory
import com.feliz.apps.domain.model.common.Event

interface VendorContract {

    interface View {
        fun showVendorCategoryList(categoryList: List<VendorCategory>)
        fun showLoadingIndicator(isActive: Boolean)
        fun showErrorMessageDialog(message: String)
        fun openVendorListPage(category: VendorCategory)
    }

    interface Presenter {
        //        val searchQueryObservable: MutableLiveData<String>
        val vendorCategoryListObservable: LiveData<List<VendorCategory>>
        val stateLoadingObservable: LiveData<Boolean>
        val eventLoadingErrorObservable: LiveData<Event<String>>
        val eventOpenVendorListPageObservable: LiveData<Event<VendorCategory>>

        fun loadVendorCategoryList()

//        fun searchVendor() {
//            val query = searchQueryObservable.value
//            val list = vendorCategoryListObservable.value
//            vendorCategoryListObservable.value = list?.filter { it.name == query } ?: emptyList()
//        }
    }

}