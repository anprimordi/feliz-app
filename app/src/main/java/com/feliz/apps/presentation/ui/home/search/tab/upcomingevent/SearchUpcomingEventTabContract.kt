package com.feliz.apps.presentation.ui.home.search.tab.upcomingevent

import com.feliz.apps.domain.model.Portfolio

interface SearchUpcomingEventTabContract {
    interface View {
        fun showSearchUpcomingEvtPage(list: List<Portfolio>)
        fun showSearchResult(upcomingEventList: List<Portfolio>, query: String)
        fun showLoadingIndicator(isActive: Boolean)
        fun showErrorMessageDialog(message: String)
        fun showEmptyUpcomingEventListMessage(message: String)
        fun showEmptySearchResult(query: String)
        fun openPortfolioPage(portfolio: Portfolio)
    }
}