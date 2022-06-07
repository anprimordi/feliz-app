package com.feliz.apps.presentation.ui.home.search.tab.finishedevent

import com.feliz.apps.domain.model.Portfolio

interface SearchFinishedEventTabContract {
    interface View {
        fun showSearchFinishedEvtPage(list: List<Portfolio>)
        fun showSearchResult(finishedEventList: List<Portfolio>, query: String)
        fun showLoadingIndicator(isActive: Boolean)
        fun showErrorMessageDialog(message: String)
        fun showEmptyFinishedEventListMessage(message: String)
        fun showEmptySearchResult(query: String)
        fun openPortfolioPage(portfolio: Portfolio)
    }
}