package com.feliz.apps.presentation.ui.home.portfolio.list.upcoming

import androidx.lifecycle.LiveData
import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.domain.model.common.Event
import com.google.android.material.appbar.MaterialToolbar

interface UpcomingEventListContract {

    interface View {
        fun showUpcomingEventListPage(list: List<Portfolio>)
        fun showLoadingIndicator(isActive: Boolean)
        fun showErrorMessage(message: String)
        fun showEmptyPage(message: String)
        fun openPortfolioPage(id: Long)
        fun setupToolbar(toolbar: MaterialToolbar)
    }

    interface Presenter {
        val portfolioListObservable: LiveData<List<Portfolio>>
        val stateLoadingObservable: LiveData<Boolean>
        val eventLoadingErrorObservable: LiveData<Event<String>>
        val eventShowEmptyPageObservable: LiveData<Event<String>>
        val eventOpenPortfolioPageObservable: LiveData<Event<Portfolio>>

        fun loadPortfolioList()
    }
}