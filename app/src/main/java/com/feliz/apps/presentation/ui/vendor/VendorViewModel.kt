package com.feliz.apps.presentation.ui.vendor

import androidx.lifecycle.*
import com.feliz.apps.domain.model.VendorCategory
import com.feliz.apps.domain.model.common.Event
import com.feliz.apps.domain.model.common.Success
import com.feliz.apps.domain.source.VendorDataSource
import kotlinx.coroutines.launch

class VendorViewModel private constructor(
    private val vendorDataSource: VendorDataSource
) : ViewModel(), VendorContract.Presenter {
    private val _vendorCategoryListObservable = MutableLiveData<List<VendorCategory>>()
    private val _stateLoadingObservable = MutableLiveData<Boolean>()
    private val _eventLoadingErrorObservable = MutableLiveData<Event<String>>()
    private val _eventOpenVendorListPage = MutableLiveData<Event<VendorCategory>>()

    override val vendorCategoryListObservable: LiveData<List<VendorCategory>>
        get() = _vendorCategoryListObservable
    override val stateLoadingObservable: LiveData<Boolean>
        get() = _stateLoadingObservable
    override val eventLoadingErrorObservable: LiveData<Event<String>>
        get() = _eventLoadingErrorObservable
    override val eventOpenVendorListPageObservable: LiveData<Event<VendorCategory>>
        get() = _eventOpenVendorListPage

    override fun loadVendorCategoryList() {
        viewModelScope.launch {
            _stateLoadingObservable.postValue(true)
            val result = vendorDataSource.getVendorCategoryList()
            if (result is Success) {
                val data = result.data ?: emptyList()
                _vendorCategoryListObservable.postValue(data)
                _stateLoadingObservable.postValue(false)
            } else {
                _eventLoadingErrorObservable.postValue(Event(result.message ?: "gagal memuat"))
                _stateLoadingObservable.postValue(false)
            }
        }
    }

    class Factory(
        private val vendorDataSource: VendorDataSource
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return VendorViewModel(vendorDataSource) as T
        }
    }
}