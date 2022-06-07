package com.feliz.apps.presentation.ui.home.portfolio.list.upcoming

import androidx.lifecycle.*
import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.domain.model.common.Event
import com.feliz.apps.domain.model.common.Success
import com.feliz.apps.domain.source.PortfolioDataSource
import kotlinx.coroutines.launch

class UpcomingEventListViewModel private constructor(
    private val portfolioDataSource: PortfolioDataSource
) : ViewModel(), UpcomingEventListContract.Presenter {

    private val _portfolioListObservable = MutableLiveData<List<Portfolio>>()
    private val _stateLoadingObservable = MutableLiveData<Boolean>()
    private val _eventLoadingErrorObservable = MutableLiveData<Event<String>>()
    private val _eventShowEmptyPageObservable = MutableLiveData<Event<String>>()
    private val _eventOpenPortfolioPageObservable = MutableLiveData<Event<Portfolio>>()

    override val portfolioListObservable: LiveData<List<Portfolio>>
        get() = _portfolioListObservable
    override val stateLoadingObservable: LiveData<Boolean>
        get() = _stateLoadingObservable
    override val eventLoadingErrorObservable: LiveData<Event<String>>
        get() = _eventLoadingErrorObservable
    override val eventShowEmptyPageObservable: LiveData<Event<String>>
        get() = _eventShowEmptyPageObservable
    override val eventOpenPortfolioPageObservable: LiveData<Event<Portfolio>>
        get() = _eventOpenPortfolioPageObservable

    override fun loadPortfolioList() {
        viewModelScope.launch {
            _stateLoadingObservable.postValue(true)

            val snapshot = portfolioDataSource.getUpcomingEventList()
            if (snapshot is Success) {
                val data = snapshot.data
                if (data.isNullOrEmpty()) {
                    _eventShowEmptyPageObservable.postValue(Event("Stay tuned for upcoming events!"))
                    _stateLoadingObservable.postValue(false)
                } else {
                    _portfolioListObservable.postValue(data)
                    _stateLoadingObservable.postValue(false)
                }
            } else {
                _eventLoadingErrorObservable.postValue(
                    Event(
                        snapshot.message ?: "gagal memuat konten"
                    )
                )
                _stateLoadingObservable.postValue(false)
            }
        }
    }

    class Factory(private val portfolioDataSource: PortfolioDataSource) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return UpcomingEventListViewModel(portfolioDataSource) as T
        }
    }
}