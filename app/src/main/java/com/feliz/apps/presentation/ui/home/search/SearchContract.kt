package com.feliz.apps.presentation.ui.home.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.domain.model.Product
import com.feliz.apps.domain.model.Vendor
import com.feliz.apps.domain.model.common.Event

interface SearchContract {
    interface View {
        fun showHomeSearchPage(query: String)
        fun setupAdapter()
        fun toggleSoftKeyboard(view: android.view.View)
    }

    interface Presenter {
        val queryObservable: MutableLiveData<String>
        val stateLoadingObservable: LiveData<Boolean>
        val eventLoadingErrorProductObservable: LiveData<Event<String>>
        val eventLoadingErrorVendorObservable: LiveData<Event<String>>
        val eventLoadingErrorUpcomingEventObservable: LiveData<Event<String>>
        val eventLoadingErrorFinishedEventObservable: LiveData<Event<String>>
        val eventEmptyProductObservable: LiveData<Event<String>>
        val eventEmptyVendorObservable: LiveData<Event<String>>
        val eventEmptyUpcomingEventObservable: LiveData<Event<String>>
        val eventEmptyFinishedEventObservable: LiveData<Event<String>>
        val productListObservable: LiveData<List<Product>>
        val vendorListObservable: LiveData<List<Vendor>>
        val upcomingEventListObservable: LiveData<List<Portfolio>>
        val finishedEventListObservable: LiveData<List<Portfolio>>

        fun loadHomeSearchProductTabPage()
        fun loadHomeSearchVendorTabPage()
        fun loadHomeSearchUpcomingEventTab()
        fun loadHomeSearchFinishedEventTab()

        fun loadSearchResult()
    }
}