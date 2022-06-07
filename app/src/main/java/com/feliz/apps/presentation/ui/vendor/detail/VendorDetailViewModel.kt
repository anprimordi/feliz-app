package com.feliz.apps.presentation.ui.vendor.detail

import androidx.lifecycle.*
import com.feliz.apps.domain.model.Vendor
import com.feliz.apps.domain.model.common.Event
import com.feliz.apps.domain.model.common.Success
import com.feliz.apps.domain.source.VendorDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class VendorDetailViewModel private constructor(
    private val vendorDataSource: VendorDataSource
) : ViewModel(),
    VendorDetailContract.Presenter {
    private val _vendorObservable = MutableLiveData<Vendor>()
    private val _moreVendorObservable = MutableLiveData<List<Vendor>>()
    private val _stateLoadingObservable = MutableLiveData<Boolean>()
    private val _eventLoadingErrorObservable = MutableLiveData<Event<String>>()
    private val _eventOpenMoreVendorPageObservable = MutableLiveData<Event<Vendor>>()
    private val _eventOpenDirectionObservable = MutableLiveData<Event<Pair<Double, Double>>>()

    override val vendorObservable: LiveData<Vendor>
        get() = _vendorObservable
    override val moreVendorObservable: LiveData<List<Vendor>>
        get() = _moreVendorObservable
    override val stateLoadingObservable: LiveData<Boolean>
        get() = _stateLoadingObservable
    override val eventLoadingErrorObservable: LiveData<Event<String>>
        get() = _eventLoadingErrorObservable
    override val eventOpenMoreVendorPageObservable: LiveData<Event<Vendor>>
        get() = _eventOpenMoreVendorPageObservable
    override val eventOpenDirectionObservable: LiveData<Event<Pair<Double, Double>>>
        get() = _eventOpenDirectionObservable

    private var vendor: Vendor? = null
    private var moreVendorList: List<Vendor> =
        listOf(Vendor.dummy(), Vendor.dummy(), Vendor.dummy())

    override fun loadDetailVendor(vendorId: Long) {
        viewModelScope.launch {
            _stateLoadingObservable.postValue(true)
            val result = vendorDataSource.getVendorById(vendorId = vendorId)
            if (result is Success) {
                val data = result.data ?: Vendor.DEFAULT
                vendor = result.data
                _vendorObservable.postValue(data)
                _stateLoadingObservable.postValue(false)
            } else {
                _eventLoadingErrorObservable.postValue(Event("gagal memuat konten"))
                _stateLoadingObservable.postValue(false)
            }
        }
    }

    override fun loadMoreVendor(categoryId: Long) {
        viewModelScope.launch {
            delay(1000)
            val result = vendorDataSource.getVendorListByCategoryId(categoryId)
            if (result is Success) {
                val data = result.data
                _moreVendorObservable.postValue(data)
            }
        }
    }

    override fun triggerOpenDirection() {
        if (vendor != null) {
            viewModelScope.launch {
                _eventOpenDirectionObservable
                    .postValue(Event(Pair(vendor!!.latitude, vendor!!.longitude)))
            }
        }
    }

    class Factory(
        private val vendorDataSource: VendorDataSource
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return VendorDetailViewModel(vendorDataSource) as T
        }
    }
}