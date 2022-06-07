package com.feliz.apps.presentation.ui.home.search

import androidx.lifecycle.*
import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.domain.model.Product
import com.feliz.apps.domain.model.Vendor
import com.feliz.apps.domain.model.common.Event
import com.feliz.apps.domain.model.common.Success
import com.feliz.apps.domain.source.SearchDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchViewModel private constructor(
    private val searchDataSource: SearchDataSource
) : ViewModel(), SearchContract.Presenter {
    private val _queryObservable = MutableLiveData<String>()
    private val _filteredProductListObservable = MediatorLiveData<List<Product>>()
    private val _filteredVendorListObservable = MediatorLiveData<List<Vendor>>()
    private val _filteredUpcomingListObservable = MediatorLiveData<List<Portfolio>>()
    private val _filteredFinishedListObservable = MediatorLiveData<List<Portfolio>>()
    private val _stateLoadingObservable = MutableLiveData<Boolean>()
    private val _eventLoadingErrorProductObservable = MutableLiveData<Event<String>>()
    private val _eventLoadingErrorVendorObservable = MutableLiveData<Event<String>>()
    private val _eventLoadingErrorUpcomingEventObservable = MutableLiveData<Event<String>>()
    private val _eventLoadingErrorFinishedEventObservable = MutableLiveData<Event<String>>()
    private val _eventEmptyProductObservable = MutableLiveData<Event<String>>()
    private val _eventEmptyVendorObservable = MutableLiveData<Event<String>>()
    private val _eventEmptyUpcomingEventObservable = MutableLiveData<Event<String>>()
    private val _eventEmptyFinishedEventObservable = MutableLiveData<Event<String>>()
    private val _productListObservable = MutableLiveData<List<Product>>()
    private val _vendorListObservable = MutableLiveData<List<Vendor>>()
    private val _upcomingEventListObservable = MutableLiveData<List<Portfolio>>()
    private val _finishedEventListObservable = MutableLiveData<List<Portfolio>>()

    override val queryObservable: MutableLiveData<String>
        get() = _queryObservable
    override val stateLoadingObservable: LiveData<Boolean>
        get() = _stateLoadingObservable
    override val eventLoadingErrorProductObservable: LiveData<Event<String>>
        get() = _eventLoadingErrorProductObservable
    override val eventLoadingErrorVendorObservable: LiveData<Event<String>>
        get() = _eventLoadingErrorVendorObservable
    override val eventLoadingErrorUpcomingEventObservable: LiveData<Event<String>>
        get() = _eventLoadingErrorUpcomingEventObservable
    override val eventLoadingErrorFinishedEventObservable: LiveData<Event<String>>
        get() = _eventLoadingErrorFinishedEventObservable
    override val eventEmptyProductObservable: LiveData<Event<String>>
        get() = _eventEmptyProductObservable
    override val eventEmptyVendorObservable: LiveData<Event<String>>
        get() = _eventEmptyVendorObservable
    override val eventEmptyUpcomingEventObservable: LiveData<Event<String>>
        get() = _eventEmptyUpcomingEventObservable
    override val eventEmptyFinishedEventObservable: LiveData<Event<String>>
        get() = _eventEmptyFinishedEventObservable

    //    override val eventEmptySearchResult: LiveData<Event<String>>
//        get() = _eventEmptySearchResult
    override val productListObservable: LiveData<List<Product>>
        get() = _filteredProductListObservable
    override val vendorListObservable: LiveData<List<Vendor>>
        get() = _filteredVendorListObservable
    override val upcomingEventListObservable: LiveData<List<Portfolio>>
        get() = _filteredUpcomingListObservable
    override val finishedEventListObservable: LiveData<List<Portfolio>>
        get() = _filteredFinishedListObservable

    init {
        _filteredProductListObservable.apply {
            addSource(_productListObservable) { data ->
                val list = data ?: emptyList()
                val query = queryObservable.value ?: ""
                postValue(list.filter {
                    it.name.lowercase().contains(query.lowercase(), ignoreCase = true)
                })
            }
            addSource(queryObservable) { data ->
                val list = _productListObservable.value ?: emptyList()
                val query = data ?: ""
                postValue(list.filter {
                    it.name.lowercase().contains(query.lowercase(), ignoreCase = true)
                })
            }
        }
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
        _filteredUpcomingListObservable.apply {
            addSource(_upcomingEventListObservable) { data ->
                val list = data ?: emptyList()
                val query = queryObservable.value ?: ""
                postValue(list.filter {
                    it.name.lowercase().contains(query.lowercase(), ignoreCase = true)
                })
            }
            addSource(queryObservable) { data ->
                val list = _upcomingEventListObservable.value ?: emptyList()
                val query = data ?: ""
                postValue(list.filter {
                    it.name.lowercase().contains(query.lowercase(), ignoreCase = true)
                })
            }
        }
        _filteredFinishedListObservable.apply {
            addSource(_finishedEventListObservable) { data ->
                val list = data ?: emptyList()
                val query = queryObservable.value ?: ""
                postValue(list.filter {
                    it.name.lowercase().contains(query.lowercase(), ignoreCase = true)
                })
            }
            addSource(queryObservable) { data ->
                val list = _finishedEventListObservable.value ?: emptyList()
                val query = data ?: ""
                postValue(list.filter {
                    it.name.lowercase().contains(query.lowercase(), ignoreCase = true)
                })
            }
        }
    }

    override fun loadHomeSearchProductTabPage() {
        viewModelScope.launch {
            _stateLoadingObservable.postValue(true)
            delay(1000)
            val query = queryObservable.value ?: ""
            when (val result = searchDataSource.getProductList(query = query)) {
                is Success -> {
                    val data = result.data ?: emptyList()
                    if (data.isNullOrEmpty()) {
                        _eventEmptyProductObservable.postValue(Event("Pencarian tidak ditemukan"))
                        _stateLoadingObservable.postValue(false)
                    } else {
                        _productListObservable.postValue(data)
                        _stateLoadingObservable.postValue(false)
                    }
//                    _productListObservable.postValue(data)
//                    _stateLoadingObservable.postValue(false)
                }
                else -> {
                    _eventLoadingErrorProductObservable.postValue(
                        Event(
                            result.message ?: "gagal memuat konten"
                        )
                    )
                    _stateLoadingObservable.postValue(false)
                }
            }
        }
    }

    override fun loadHomeSearchVendorTabPage() {
        viewModelScope.launch {
            _stateLoadingObservable.postValue(true)
            delay(1000)
            val query = queryObservable.value ?: ""
            when (val result = searchDataSource.getVendorList(query = query)) {
                is Success -> {
                    val data = result.data ?: emptyList()
                    if (data.isNullOrEmpty()) {
                        _eventEmptyVendorObservable.postValue(Event("Pencarian tidak ditemukan"))
                        _stateLoadingObservable.postValue(false)
                    } else {
                        _vendorListObservable.postValue(data)
                        _stateLoadingObservable.postValue(false)
                    }
//                    _vendorListObservable.postValue(data)
//                    _stateLoadingObservable.postValue(false)
                }
                else -> {
                    _eventLoadingErrorVendorObservable.postValue(
                        Event(
                            result.message ?: "Gagal memuat konten"
                        )
                    )
                    _stateLoadingObservable.postValue(false)
                }
            }
        }
    }

    override fun loadHomeSearchUpcomingEventTab() {
        viewModelScope.launch {
            _stateLoadingObservable.postValue(true)
            delay(2000)
            val query = queryObservable.value ?: ""
            when (val result = searchDataSource.getUpcomingEventList(query = query)) {
                is Success -> {
                    val data = result.data ?: emptyList()
                    if (data.isNullOrEmpty()) {
                        _eventEmptyUpcomingEventObservable.postValue(Event("Pencarian tidak ditemukan"))
                        _stateLoadingObservable.postValue(false)
                    } else {
                        _upcomingEventListObservable.postValue(data)
                        _stateLoadingObservable.postValue(false)
                    }
//                    _upcomingEventListObservable.postValue(data)
//                    _stateLoadingObservable.postValue(false)
                }
                else -> {
                    _eventLoadingErrorUpcomingEventObservable.postValue(
                        Event(
                            result.message ?: "Gagal memuat konten"
                        )
                    )
                    _stateLoadingObservable.postValue(false)
                }
            }
        }
    }

    override fun loadHomeSearchFinishedEventTab() {
        viewModelScope.launch {
            _stateLoadingObservable.postValue(true)
            delay(2000)
            val query = queryObservable.value ?: ""
            when (val result = searchDataSource.getFinishedEventList(query = query)) {
                is Success -> {
                    val data = result.data ?: emptyList()
                    if (data.isNullOrEmpty()) {
                        _eventEmptyFinishedEventObservable.postValue(Event("Pencarian tidak ditemukan"))
                        _stateLoadingObservable.postValue(false)
                    } else {
                        _finishedEventListObservable.postValue(data)
                        _stateLoadingObservable.postValue(false)
                    }
//                    _finishedEventListObservable.postValue(data)
//                    _stateLoadingObservable.postValue(false)
                }
                else -> {
                    _eventLoadingErrorFinishedEventObservable.postValue(Event("Gagal memuat konten"))
                    _stateLoadingObservable.postValue(false)
                }
            }
        }
    }


    override fun loadSearchResult() {
        viewModelScope.launch {
            _stateLoadingObservable.postValue(true)
            delay(1000)
//            if (searchQuery != null) {
//                _searchResultQueryObservable.postValue(searchQuery!!)
//                _stateLoadingObservable.postValue(false)
//            } else {
//                _eventLoadingErrorObservable.postValue(Event("gagal memuat konten"))
//                _stateLoadingObservable.postValue(false)
//            }
        }
    }

    class Factory(private val searchDataSource: SearchDataSource) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SearchViewModel(searchDataSource) as T
        }
    }
}