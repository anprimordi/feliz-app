package com.feliz.apps.presentation.ui.home.portfolio.detail

import androidx.lifecycle.LiveData
import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.domain.model.common.Event
import com.google.android.material.appbar.MaterialToolbar

interface PortfolioContract {

    interface View {
        fun showPortfolio(portfolio: Portfolio)
        fun setupToolbar(toolbar: MaterialToolbar, title: String)
        fun showPortfolioList(list: List<Portfolio>)
        fun showLoadingIndicator(isActive: Boolean)
        fun showErrorMessage(message: String)
        fun openUpcomingEventPage(portfolio: Portfolio)
    }

    interface Presenter {
        val portfolioObservable: LiveData<Portfolio>
        val listPortfolioObservable: LiveData<List<Portfolio>>
        val stateLoadingObservable: LiveData<Boolean>
        val eventLoadingErrorObservable: LiveData<Event<String>>
        val eventOpenUpcomingEventObservable: LiveData<Event<Portfolio>>
        val eventOpenLocationDirectionPage: LiveData<Event<String>>

        fun loadPortfolio(id: Long)
        fun loadUpcomingEvent()

        fun triggerEventOpenLocationDirection()
    }
}