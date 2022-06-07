package com.feliz.apps.presentation.ui.home

import androidx.lifecycle.*
import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.domain.model.ProductCategory
import com.feliz.apps.domain.model.Vendor
import com.feliz.apps.domain.model.VendorCategory
import com.feliz.apps.domain.model.common.Event
import com.feliz.apps.domain.model.common.Success
import com.feliz.apps.domain.source.HomeDataSource
import kotlinx.coroutines.launch

class HomeViewModel private constructor(
    private val homeDataSource: HomeDataSource
) : ViewModel(), HomeContract.Presenter {

    private val _bannerImageListObservable = MutableLiveData<List<String>>()
    private val _productCategoryListObservable = MutableLiveData<List<ProductCategory>>()
    private val _upcomingListObservable = MutableLiveData<List<Portfolio>>()
    private val _finishedListObservable = MutableLiveData<List<Portfolio>>()
    private val _vendorCategoryListObservable = MutableLiveData<List<VendorCategory>>()
    private val _stateLoadingObservable = MutableLiveData<Boolean>()
    private val _showErrorMessage = MutableLiveData<Event<String>>()
    private val _showEmptyMessage = MutableLiveData<Event<String>>()
    private val _eventOpenUpcomingEventPage = MutableLiveData<Event<Unit>>()
    private val _eventOpenFinishedEventPage = MutableLiveData<Event<Unit>>()
    private val _eventOpenProductCategoryPage = MutableLiveData<Event<ProductCategory>>()
    private val _eventOpenPortfolioPage = MutableLiveData<Event<Portfolio>>()
    private val _eventOpenVendorPage = MutableLiveData<Event<Vendor>>()

    override val bannerImageListObservable: LiveData<List<String>>
        get() = _bannerImageListObservable
    override val productCategoryListObservable: LiveData<List<ProductCategory>>
        get() = _productCategoryListObservable
    override val upcomingListObservable: LiveData<List<Portfolio>>
        get() = _upcomingListObservable
    override val finishedListObservable: LiveData<List<Portfolio>>
        get() = _finishedListObservable
    override val vendorCategoryListObservable: LiveData<List<VendorCategory>>
        get() = _vendorCategoryListObservable
    override val stateLoadingObservable: LiveData<Boolean>
        get() = _stateLoadingObservable
    override val eventShowErrorMessageObservable: LiveData<Event<String>>
        get() = _showErrorMessage
    override val eventShowEmptyMessageObservable: LiveData<Event<String>>
        get() = _showEmptyMessage
    override val eventOpenProductCategoryPageObservable: LiveData<Event<ProductCategory>>
        get() = _eventOpenProductCategoryPage
    override val eventOpenUpcomingEventPageObservable: LiveData<Event<Unit>>
        get() = _eventOpenUpcomingEventPage
    override val eventOpenFinishedEventPageObservable: LiveData<Event<Unit>>
        get() = _eventOpenFinishedEventPage
    override val eventOpenPortfolioPageObservable: LiveData<Event<Portfolio>>
        get() = _eventOpenPortfolioPage
    override val eventOpenVendorPageObservable: LiveData<Event<Vendor>>
        get() = _eventOpenVendorPage

    override fun loadBannerImageList() {
//        val bannerImageList = listOf(
//            "https://www.acf.hhs.gov/sites/default/files/images/ofa/shutterstock_201735029.jpg",
//            "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/portrait-of-happy-birthday-boy-royalty-free-image-1623417101.jpg?crop=1.00xw:0.775xh;0,0.121xh&resize=1200:*",
//            "https://ichef.bbci.co.uk/news/976/cpsprodpb/BE8F/production/_117738784_tv066462884.jpg"
//        )
        viewModelScope.launch {
            val result = homeDataSource.getHome()
            _stateLoadingObservable.postValue(true)
            if (result is Success) {
                val data = result.data?.thumbnails ?: emptyList()
                val photoUrls = arrayListOf<String>()

                data.forEach {
                    photoUrls.add(it.url)
                }

                _bannerImageListObservable.postValue(photoUrls)
                _stateLoadingObservable.postValue(false)
            } else {
                _showErrorMessage.postValue(Event("gagal memuat konten"))
                _stateLoadingObservable.postValue(false)
            }
        }
    }

    override fun loadProductCategoryList() {
        val list = listOf(ProductCategory.dummy(), ProductCategory.dummy(), ProductCategory.dummy())
        viewModelScope.launch {
            _stateLoadingObservable.postValue(true)
            val result = homeDataSource.getHome()
            if (result is Success) {
                val data = result.data?.productCategories ?: emptyList()
                _productCategoryListObservable.postValue(data)
                _stateLoadingObservable.postValue(false)
            } else {
                _showErrorMessage.postValue(Event(result.message ?: "gagal memuat konten"))
                _stateLoadingObservable.postValue(false)
            }
        }
    }

    override fun loadUpcomingList() {
        val list = listOf(Portfolio.dummy(), Portfolio.dummy(), Portfolio.dummy())
        viewModelScope.launch {
            _stateLoadingObservable.postValue(true)
            when (val result = homeDataSource.getHome()) {
                is Success -> {
                    val data = result.data?.upcomingEvents ?: emptyList()
                    _upcomingListObservable.postValue(data.take(5))
                    _stateLoadingObservable.postValue(false)
                }
/*                list.isEmpty() -> {
                    _showEmptyMessage.postValue(Event("Empty Content"))
                    _stateLoadingObservable.postValue(false)
                }*/
                else -> {
                    _showErrorMessage.postValue(Event("gagal memuat konten"))
                    _stateLoadingObservable.postValue(false)
                }
            }
        }
    }

    override fun loadFinishedList() {
        val list = listOf(Portfolio.dummy(), Portfolio.dummy(), Portfolio.dummy())
        viewModelScope.launch {
            _stateLoadingObservable.postValue(true)
            when (val result = homeDataSource.getHome()) {
                is Success -> {
                    val data = result.data?.finishedEvents ?: emptyList()
                    _finishedListObservable.postValue(data.take(5))
                    _stateLoadingObservable.postValue(false)
                }
/*                list.isEmpty() -> {
                    _showEmptyMessage.postValue(Event("Empty Content"))
                    _stateLoadingObservable.postValue(false)
                }*/
                else -> {
                    _showErrorMessage.postValue(Event("gagal memuat konten"))
                    _stateLoadingObservable.postValue(false)
                }
            }
        }
    }

    override fun loadVendorList() {
        val vendors = listOf(VendorCategory.dummy(), VendorCategory.dummy(), VendorCategory.dummy())
        viewModelScope.launch {
            _stateLoadingObservable.postValue(true)
            val result = homeDataSource.getHome()
            if (result is Success) {
                val data = result.data?.vendorCategories ?: emptyList()
                _vendorCategoryListObservable.postValue(data)
                _stateLoadingObservable.postValue(false)
            } else {
                _showErrorMessage.postValue(Event("gagal memuat konten"))
                _stateLoadingObservable.postValue(false)
            }
        }
    }

    class Factory(
        private val homeDataSource: HomeDataSource
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(homeDataSource) as T
        }
    }

}