package com.feliz.apps.presentation.ui.home.product.list

import androidx.lifecycle.*
import com.feliz.apps.domain.model.Product
import com.feliz.apps.domain.model.ProductCategory
import com.feliz.apps.domain.model.common.Event
import com.feliz.apps.domain.model.common.Success
import com.feliz.apps.domain.source.ProductDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProductListViewModel private constructor(
    private val productDataSource: ProductDataSource
) : ViewModel(),
    ProductListContract.Presenter {
    private val _productCategoryNameObservable = MutableLiveData<String>()
    private val _productCategoryObservable = MutableLiveData<ProductCategory>()
    private val _moreProductCategoryListObservable = MutableLiveData<List<ProductCategory>>()
    private val _stateLoadingObservable = MutableLiveData<Boolean>()

    private val _eventLoadingErrorObservable = MutableLiveData<Event<String>>()
    private val _eventOpenProductPageObservable = MutableLiveData<Event<Product>>()
    private val _eventOpenMoreProductCategoryObservable = MutableLiveData<Event<ProductCategory>>()
    override val productCategoryNameObservable: LiveData<String>
        get() = _productCategoryNameObservable

    override val productCategoryObservable: LiveData<ProductCategory>
        get() = _productCategoryObservable
    override val moreProductCategoryListObservable: LiveData<List<ProductCategory>>
        get() = _moreProductCategoryListObservable
    override val stateLoadingObservable: LiveData<Boolean>
        get() = _stateLoadingObservable
    override val eventLoadingErrorObservable: LiveData<Event<String>>
        get() = _eventLoadingErrorObservable
    override val eventOpenProductPageObservable: LiveData<Event<Product>>
        get() = _eventOpenProductPageObservable
    override val eventOpenMoreProductCategoryObservable: LiveData<Event<ProductCategory>>
        get() = _eventOpenMoreProductCategoryObservable

    private var productCategory: ProductCategory? = ProductCategory.dummy()
    private var moreProductCategoryList: List<ProductCategory>? =
        listOf(ProductCategory.dummy(), ProductCategory.dummy(), ProductCategory.dummy())

    override fun loadProductCategory(id: Long) {
        viewModelScope.launch {
            _stateLoadingObservable.postValue(true)
            delay(1000)
            val result = productDataSource.getProductCategoryById(categoryId = id)
            if (result is Success) {
                val data = result.data ?: ProductCategory.DEFAULT
                _productCategoryObservable.postValue(data)
                _productCategoryNameObservable.postValue(data.name)
                _stateLoadingObservable.postValue(false)
            } else {
                _eventLoadingErrorObservable.postValue(
                    Event(
                        result.message ?: "gagal memuat konten"
                    )
                )
                _stateLoadingObservable.postValue(false)
            }
        }
    }

    override fun loadMoreProductCategoryList() {
        viewModelScope.launch {
            val result = productDataSource.getProductCategoryList()
            if (result is Success) {
                val data = result.data ?: emptyList()
                _moreProductCategoryListObservable.postValue(data)
            }
        }
    }

    class Factory(private val productDataSource: ProductDataSource) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ProductListViewModel(productDataSource) as T
        }
    }
}