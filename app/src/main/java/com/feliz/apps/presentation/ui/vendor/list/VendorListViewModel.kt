package com.feliz.apps.presentation.ui.vendor.list

import androidx.lifecycle.*
import com.feliz.apps.domain.model.Vendor
import com.feliz.apps.domain.model.common.Event
import com.feliz.apps.domain.model.common.Success
import com.feliz.apps.domain.source.VendorDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class VendorListViewModel private constructor(
    private val vendorDataSource: VendorDataSource
) : ViewModel(), VendorListContract.Presenter {
    private val _vendorCategoryObservable = MutableLiveData<String>()
    private val _vendorListObservable = MutableLiveData<List<Vendor>>()
    private val _filteredVendorListObservable = MediatorLiveData<List<Vendor>>()
    private val _queryObservable = MutableLiveData<String>()
    private val _stateLoadingObservable = MutableLiveData<Boolean>()
    private val _eventShowEmptyMessage = MutableLiveData<Event<String>>()
    private val _eventLoadingErrorObservable = MutableLiveData<Event<String>>()
    private val _eventOpenVendorDetailPage = MutableLiveData<Event<Vendor>>()

    override val vendorCategoryObservable: LiveData<String>
        get() = _vendorCategoryObservable
    override val vendorListObservable: LiveData<List<Vendor>>
        get() = _filteredVendorListObservable
    override val queryObservable: MutableLiveData<String>
        get() = _queryObservable
    override val stateLoadingObservable: LiveData<Boolean>
        get() = _stateLoadingObservable
    override val eventShowEmptyMessageObservable: LiveData<Event<String>>
        get() = _eventShowEmptyMessage
    override val eventLoadingErrorObservable: LiveData<Event<String>>
        get() = _eventLoadingErrorObservable
    override val eventOpenVendorDetailPageObservable: LiveData<Event<Vendor>>
        get() = _eventOpenVendorDetailPage

    init {
        _filteredVendorListObservable.apply {
            addSource(_vendorListObservable) { data ->
                val list = data ?: emptyList()
                val query = queryObservable.value ?: ""
                postValue(list.filter {
                    it.name.lowercase().contains(query.lowercase(), ignoreCase = true)
                })
            }
            addSource(queryObservable) { data ->
                val list = _vendorListObservable.value ?: emptyList()
                val query = data ?: ""
                postValue(list.filter {
                    it.name.lowercase().contains(query.lowercase(), ignoreCase = true)
                })
            }
        }
    }

    override fun loadVendorCategory(name: String) {
//        val category = VendorCategory.dummy()
        viewModelScope.launch {
            _stateLoadingObservable.postValue(true)
            delay(1000)
            if (name.isNotEmpty()) {
                _vendorCategoryObservable.postValue(name)
                _stateLoadingObservable.postValue(false)
            } else {
                _stateLoadingObservable.postValue(false)
                _eventLoadingErrorObservable.postValue(Event("gagal memuat konten"))
            }
        }
    }

    override fun loadVendorList(id: Long) {
//        val list = listOf(Vendor.dummy(), Vendor.dummy(), Vendor.dummy())
        viewModelScope.launch {
            _stateLoadingObservable.postValue(true)
            delay(1000)
            val result = vendorDataSource.getVendorListByCategoryId(categoryId = id)
            if (result is Success) {
                val data = result.data ?: emptyList()
                _vendorListObservable.postValue(data)
                _stateLoadingObservable.postValue(false)
            } else {
                _stateLoadingObservable.postValue(false)
                _eventLoadingErrorObservable.postValue(
                    Event(
                        result.message ?: "gagal memuat konten"
                    )
                )
            }
        }
    }

    class Factory(private val vendorDataSource: VendorDataSource) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return VendorListViewModel(vendorDataSource) as T
        }
    }

}