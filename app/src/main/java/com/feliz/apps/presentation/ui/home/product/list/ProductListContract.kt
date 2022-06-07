package com.feliz.apps.presentation.ui.home.product.list

import androidx.lifecycle.LiveData
import com.feliz.apps.domain.model.Product
import com.feliz.apps.domain.model.ProductCategory
import com.feliz.apps.domain.model.common.Event

interface ProductListContract {
    interface View {
        fun showProductCategory(productCategory: ProductCategory)
        fun showMoreProductCategoryList(listProduct: List<ProductCategory>)
        fun showErrorMessageView(message: String)
        fun showLoadingIndicator(isActive: Boolean)
        fun openProductPage(product: Product, categoryName: String)
        fun openMoreProductCategory(category: ProductCategory)
    }

    interface Presenter {
        val productCategoryNameObservable: LiveData<String>
        val productCategoryObservable: LiveData<ProductCategory>
        val moreProductCategoryListObservable: LiveData<List<ProductCategory>>
        val stateLoadingObservable: LiveData<Boolean>
        val eventLoadingErrorObservable: LiveData<Event<String>>
        val eventOpenProductPageObservable: LiveData<Event<Product>>
        val eventOpenMoreProductCategoryObservable: LiveData<Event<ProductCategory>>

        fun loadProductCategory(id: Long)
        fun loadMoreProductCategoryList()
    }
}