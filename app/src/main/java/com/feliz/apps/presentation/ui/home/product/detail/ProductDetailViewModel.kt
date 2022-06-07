package com.feliz.apps.presentation.ui.home.product.detail

import androidx.lifecycle.*
import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.domain.model.Product
import com.feliz.apps.domain.model.ProductCategory
import com.feliz.apps.domain.model.common.Event
import com.feliz.apps.domain.model.common.Success
import com.feliz.apps.domain.source.ProductDataSource
import kotlinx.coroutines.launch

class ProductDetailViewModel private constructor(
    private val productDataSource: ProductDataSource
) : ViewModel(),
    ProductDetailContract.Presenter {

    private val _productObservable = MutableLiveData<Product>()
    private val _productCategoryName = MutableLiveData<String>()
    private val _moreProductObservable = MutableLiveData<List<Product>>()
    private val _stateLoadingObservable = MutableLiveData<Boolean>()

    private val _eventLoadingErrorObservable = MutableLiveData<Event<String>>()
    private val _eventOpenPortfolioObservable = MutableLiveData<Event<Portfolio>>()
    private val _eventOpenMoreProductObservable = MutableLiveData<Event<Product>>()

    override val productObservable: LiveData<Product>
        get() = _productObservable
    override val productCategoryName: LiveData<String>
        get() = _productCategoryName
    override val moreProductListObservable: LiveData<List<Product>>
        get() = _moreProductObservable
    override val stateLoadingObservable: LiveData<Boolean>
        get() = _stateLoadingObservable
    override val eventLoadingErrorObservable: LiveData<Event<String>>
        get() = _eventLoadingErrorObservable
    override val eventOpenPortfolioPageObservable: LiveData<Event<Portfolio>>
        get() = _eventOpenPortfolioObservable
    override val eventOpenMoreProductPageObservable: LiveData<Event<Product>>
        get() = _eventOpenMoreProductObservable

    private var product: Product? = Product.dummy()
    private var moreProductList: List<Product>? =
        listOf(Product.dummy(), Product.dummy(), Product.dummy())

    override fun loadProduct(id: Long) {
        viewModelScope.launch {
            _stateLoadingObservable.postValue(true)

            val result = productDataSource.getProductDetailById(productId = id)
            if (result is Success) {
                val data = result.data ?: Product.DEFAULT
                val categorySnapshot = productDataSource.getProductCategoryById(data.categoryId)
                if (categorySnapshot is Success) {
                    val category = categorySnapshot.data ?: ProductCategory.DEFAULT

                    _productCategoryName.postValue(category.name)
                }
                _productObservable.postValue(data)
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

    override fun loadMoreProductList(productCategoryId: Long) {
        viewModelScope.launch {
            val result = productDataSource.getProductCategoryById(productCategoryId)

            if (result is Success)
                _moreProductObservable.postValue(
                    result.data?.products ?: ProductCategory.DEFAULT.products
                )

        }
    }

    class Factory(
        private val productDataSource: ProductDataSource
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ProductDetailViewModel(productDataSource) as T
        }
    }
}