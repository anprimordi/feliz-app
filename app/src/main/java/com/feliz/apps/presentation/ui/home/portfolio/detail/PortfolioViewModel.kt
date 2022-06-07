package com.feliz.apps.presentation.ui.home.portfolio.detail

import androidx.lifecycle.*
import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.domain.model.common.Event
import com.feliz.apps.domain.model.common.Success
import com.feliz.apps.domain.source.PortfolioDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PortfolioViewModel private constructor(
    private val portfolioDataSource: PortfolioDataSource
) : ViewModel(), PortfolioContract.Presenter {

    private val _portfolioObservable = MutableLiveData<Portfolio>()
    private val _upcomingEventListObservable = MutableLiveData<List<Portfolio>>()

    private val _stateLoadingObservable = MutableLiveData<Boolean>()

    private val _eventLoadingErrorObservable = MutableLiveData<Event<String>>()
    private val _eventOpenUpcomingEvent = MutableLiveData<Event<Portfolio>>()
    private val _eventOpenLocationDirection = MutableLiveData<Event<String>>()

    override val portfolioObservable: LiveData<Portfolio>
        get() = _portfolioObservable
    override val listPortfolioObservable: LiveData<List<Portfolio>>
        get() = _upcomingEventListObservable
    override val stateLoadingObservable: LiveData<Boolean>
        get() = _stateLoadingObservable
    override val eventLoadingErrorObservable: LiveData<Event<String>>
        get() = _eventLoadingErrorObservable
    override val eventOpenUpcomingEventObservable: LiveData<Event<Portfolio>>
        get() = _eventOpenUpcomingEvent
    override val eventOpenLocationDirectionPage: LiveData<Event<String>>
        get() = _eventOpenLocationDirection

    private var portfolio: Portfolio? = null
    private var upcomingEventList: List<Portfolio>? = emptyList()

    override fun loadPortfolio(id: Long) {
        viewModelScope.launch {
            _stateLoadingObservable.postValue(true)
            delay(1000)
            val result = portfolioDataSource.getPortfolioById(portfolioId = id)
            if (result is Success) {
                val data = result.data ?: Portfolio.DEFAULT
                portfolio = data
                _portfolioObservable.postValue(data)
                _stateLoadingObservable.postValue(false)
            } else {
                _eventLoadingErrorObservable.postValue(Event("gagal memuat konten"))
                _stateLoadingObservable.postValue(false)
            }
        }
    }

    override fun loadUpcomingEvent() {
        viewModelScope.launch {
            val result = portfolioDataSource.getUpcomingEventList()
            if (result is Success) {
                _upcomingEventListObservable.postValue(result.data ?: emptyList())
            } else {
                _eventLoadingErrorObservable.postValue(
                    Event(
                        content = result.message ?: "gagal memuat konten"
                    )
                )
            }
        }
    }

    override fun triggerEventOpenLocationDirection() {
        val location = portfolio?.location
        _eventOpenLocationDirection.value = Event(location ?: Portfolio.DEFAULT.location)
    }

    class Factory(private val portfolioDataSource: PortfolioDataSource) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PortfolioViewModel(portfolioDataSource) as T
        }
    }
}