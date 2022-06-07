package com.feliz.apps.presentation.ui.form.login

import androidx.lifecycle.*
import com.feliz.apps.domain.model.common.Event
import com.feliz.apps.domain.model.common.Success
import com.feliz.apps.domain.model.form.personaldata.Client
import com.feliz.apps.domain.source.ClientDataSource
import kotlinx.coroutines.launch

class FormLoginViewModel private constructor(
    private val clientDataSource: ClientDataSource
) : ViewModel(), FormLoginContract.Presenter {
    private val _usernameObservable = MutableLiveData<String>()
    private val _passwordObservable = MutableLiveData<String>()
    private val _stateLoadingObservable = MutableLiveData<Boolean>()
    private val _eventLoadingErrorObservable = MutableLiveData<Event<String>>()
    private val _eventShowLoginSuccessMessage = MutableLiveData<Event<String>>()
    private val _eventShowLoginFailedMessage = MutableLiveData<Event<String>>()
    private val _eventLogin = MutableLiveData<Event<Client>>()
    private val _eventOpenFormDetailPage = MutableLiveData<Event<Long>>()

    override val usernameObservable: MutableLiveData<String>
        get() = _usernameObservable
    override val passwordObservable: MutableLiveData<String>
        get() = _passwordObservable
    override val stateLoadingObservable: LiveData<Boolean>
        get() = _stateLoadingObservable
    override val eventLoadingErrorObservable: LiveData<Event<String>>
        get() = _eventLoadingErrorObservable
    override val eventShowLoginSuccessMessage: LiveData<Event<String>>
        get() = _eventShowLoginSuccessMessage
    override val eventShowLoginFailedMessage: LiveData<Event<String>>
        get() = _eventShowLoginFailedMessage
    override val eventLogin: LiveData<Event<Client>>
        get() = _eventLogin
    override val eventOpenFormDetailPage: LiveData<Event<Long>>
        get() = _eventOpenFormDetailPage

//    private var listClient = listOf(
//        Client.dummy(), Client.dummy(), Client.dummy()
//    )
//    private var client: Client = Client.dummy()

    override fun loadLoginPage() {
        viewModelScope.launch {
            _stateLoadingObservable.postValue(true)

            val client = clientDataSource.getClientDetailData()

            if (client is Success) {
                val clientId = client.data?.id
                if (clientId != null && !clientId.equals(-1)) {
                    _stateLoadingObservable.postValue(false)
                    _eventOpenFormDetailPage.postValue(Event(clientId))
                } else {
                    _stateLoadingObservable.postValue(false)
                }
            } else {
                _stateLoadingObservable.postValue(false)
            }
        }
    }

    override fun triggerEventLogin() {
        val inputUsername = usernameObservable.value
        val inputPassword = passwordObservable.value

        viewModelScope.launch {
            _stateLoadingObservable.postValue(true)
            if (!inputUsername.isNullOrEmpty() && !inputPassword.isNullOrEmpty()) {
                val login = clientDataSource.login(inputUsername, inputPassword)

                if (login is Success) {
                    val clientId = login.data?.id
                    if (clientId != null && !clientId.equals(-1)) {
                        val saveData = clientDataSource.saveClientData(login.data)
                        val setToken = clientDataSource.setClientToken(login.data.token)
                        if (saveData is Success && setToken is Success) {
                            _stateLoadingObservable.postValue(false)
                            _eventOpenFormDetailPage.postValue(Event(clientId))
                        } else {
                            _stateLoadingObservable.postValue(false)
                            _eventShowLoginFailedMessage.postValue(
                                Event(
                                    saveData.message ?: "login gagal"
                                )
                            )
                        }
                    } else {
                        _stateLoadingObservable.postValue(false)
                        _eventShowLoginFailedMessage.postValue(
                            Event(login.message ?: "login gagal")
                        )
                    }
                }
            } else {
                _stateLoadingObservable.postValue(false)
                _eventShowLoginFailedMessage.postValue(Event("Masukkan username dan password!"))
            }
        }
    }

    class Factory(private val clientDataSource: ClientDataSource) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return FormLoginViewModel(clientDataSource) as T
        }
    }
}