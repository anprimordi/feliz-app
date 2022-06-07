package com.feliz.apps.presentation.ui.home.search.tab.product

import com.feliz.apps.domain.model.Product

interface SearchProductTabContract {
    interface View {
        fun showSearchProductPage(list: List<Product>)
        fun showSearchResult(productList: List<Product>, query: String)
        fun showLoadingIndicator(isActive: Boolean)
        fun showErrorMessageDialog(message: String)
        fun showEmptyProductListMessage(message: String)
        fun showEmptySearchResult(query: String)
        fun openSearchResultItem(product: Product)
    }
}