package com.feliz.apps.presentation.ui.home.product.detail

import androidx.lifecycle.LiveData
import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.domain.model.Product
import com.feliz.apps.domain.model.common.Event

interface ProductDetailContract {

    interface View {
        fun showProduct(product: Product, name: String)
        fun showMoreProductList(listProduct: List<Product>)
        fun showLoadingIndicator(isActive: Boolean)
        fun showErrorMessage(message: String)
        fun openPortfolioPage(portfolio: Portfolio)
        fun openMoreProductPage(product: Product)
    }

    interface Presenter {
        val productObservable: LiveData<Product>
        val productCategoryName: LiveData<String>
        val moreProductListObservable: LiveData<List<Product>>
        val stateLoadingObservable: LiveData<Boolean>
        val eventLoadingErrorObservable: LiveData<Event<String>>
        val eventOpenPortfolioPageObservable: LiveData<Event<Portfolio>>
        val eventOpenMoreProductPageObservable: LiveData<Event<Product>>

        fun loadProduct(id: Long)
        fun loadMoreProductList(productCategoryId: Long)
    }
}