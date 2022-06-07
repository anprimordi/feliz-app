package com.feliz.apps.presentation.ui.home

import androidx.lifecycle.LiveData
import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.domain.model.ProductCategory
import com.feliz.apps.domain.model.Vendor
import com.feliz.apps.domain.model.VendorCategory
import com.feliz.apps.domain.model.common.Event

interface HomeContract {

    interface View {
        fun showBannerImageList(imageUrlList: List<String>)
        fun showProductCategoryList(categoryList: List<ProductCategory>)
        fun showUpcomingList(upcomingList: List<Portfolio>)
        fun showFinishedList(finishedList: List<Portfolio>)
        fun showVendorCategoryList(vendorCategoryList: List<VendorCategory>)
        fun showLoadingIndicator(isActive: Boolean)
        fun showErrorMessageDialog(message: String)
        fun openSearchPage()
        fun openCompanyProfilePage()
        fun openUpcomingEventPage()
        fun openFinishedEventPage()
        fun openProductCategoryPage(category: ProductCategory)
        fun openPortfolioPage(portfolio: Portfolio)
        fun openVendorPage(vendor: Vendor)
    }

    interface Presenter {
        val bannerImageListObservable: LiveData<List<String>>
        val productCategoryListObservable: LiveData<List<ProductCategory>>
        val upcomingListObservable: LiveData<List<Portfolio>>
        val finishedListObservable: LiveData<List<Portfolio>>
        val vendorCategoryListObservable: LiveData<List<VendorCategory>>
        val stateLoadingObservable: LiveData<Boolean>
        val eventShowErrorMessageObservable: LiveData<Event<String>>
        val eventShowEmptyMessageObservable: LiveData<Event<String>>
        val eventOpenProductCategoryPageObservable: LiveData<Event<ProductCategory>>
        val eventOpenUpcomingEventPageObservable: LiveData<Event<Unit>>
        val eventOpenFinishedEventPageObservable: LiveData<Event<Unit>>
        val eventOpenPortfolioPageObservable: LiveData<Event<Portfolio>>
        val eventOpenVendorPageObservable: LiveData<Event<Vendor>>

        fun loadBannerImageList()
        fun loadProductCategoryList()
        fun loadUpcomingList()
        fun loadFinishedList()
        fun loadVendorList()

//        fun triggerOpenCompanyProfilePage()
    }

}