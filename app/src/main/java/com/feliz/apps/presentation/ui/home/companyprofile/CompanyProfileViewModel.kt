package com.feliz.apps.presentation.ui.home.companyprofile

import androidx.lifecycle.*
import com.feliz.apps.domain.model.CompanyProfile
import com.feliz.apps.domain.model.common.Event
import com.feliz.apps.domain.model.common.Success
import com.feliz.apps.domain.source.CompanyProfileDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CompanyProfileViewModel private constructor(
    private val companyProfileDataSource: CompanyProfileDataSource
) : ViewModel(),
    CompanyProfileContract.Presenter {
    private val _companyProfileObservable = MutableLiveData<CompanyProfile>()
    private val _eventLoadProfileErrorObservable = MutableLiveData<Event<String>>()
    private val _stateLoadingObservable = MutableLiveData<Boolean>()
    private val _eventOpenWhatsappPage = MutableLiveData<Event<String>>()
    private val _eventOpenYouTubePage = MutableLiveData<Event<String>>()
    private val _eventOpenEmailPage = MutableLiveData<Event<String>>()

    override val companyProfileObservable: LiveData<CompanyProfile>
        get() = _companyProfileObservable
    override val eventLoadProfileErrorObservable: LiveData<Event<String>>
        get() = _eventLoadProfileErrorObservable
    override val stateLoadingObservable: LiveData<Boolean>
        get() = _stateLoadingObservable
    override val eventOpenWhatsappPage: LiveData<Event<String>>
        get() = _eventOpenWhatsappPage
    override val eventOpenYouTubePage: LiveData<Event<String>>
        get() = _eventOpenYouTubePage
    override val eventOpenEmailPage: LiveData<Event<String>>
        get() = _eventOpenEmailPage

    private var companyProfile: CompanyProfile? = null

    override fun loadCompanyProfile() {
        viewModelScope.launch {
            _stateLoadingObservable.postValue(true)
            delay(timeMillis = 500)
            val result = companyProfileDataSource.getCompanyProfile()
            if (result is Success) {
                val data = result.data ?: CompanyProfile.DEFAULT
                companyProfile = result.data!!
                _companyProfileObservable.postValue(data)
                _stateLoadingObservable.postValue(false)
            } else {
                _eventLoadProfileErrorObservable.postValue(Event(result.message ?: "gagal memuat"))
                _stateLoadingObservable.postValue(false)
            }
        }
    }

    override fun triggerEventOpenWhatsappPage() {
        val phoneNumber = companyProfile?.whatsapp ?: CompanyProfile.DEFAULT.whatsapp
        if (phoneNumber != null) {
            _eventOpenWhatsappPage.value = Event(phoneNumber)
        }
    }

    override fun triggerEventOpenYouTubePage() {
        val url = companyProfile?.youtube ?: CompanyProfile.DEFAULT.youtube
        if (url != null) {
            _eventOpenYouTubePage.value = Event(url)
        }
    }

    override fun triggerEventOpenEmail() {
        val email = companyProfile?.email ?: CompanyProfile.DEFAULT.email
        if (email != null) {
            _eventOpenEmailPage.value = Event(email)
        }
    }

    class Factory(
        private val companyProfileDataSource: CompanyProfileDataSource
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CompanyProfileViewModel(companyProfileDataSource) as T
        }
    }
}