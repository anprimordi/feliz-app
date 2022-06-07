package com.feliz.apps.presentation.ui.form.detail

import androidx.lifecycle.*
import com.feliz.apps.domain.model.common.Event
import com.feliz.apps.domain.model.common.Success
import com.feliz.apps.domain.model.form.Form
import com.feliz.apps.domain.source.ClientDataSource
import com.feliz.apps.domain.source.FormDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FormDetailViewModel private constructor(
    private val formDataSource: FormDataSource,
    private val clientDataSource: ClientDataSource
) : ViewModel(), FormDetailContract.Presenter {
    private val _formObservable = MutableLiveData<Form>()
    private val _stateLoadingObservable = MutableLiveData<Boolean>()
    private val _eventShowErrorMessageObservable = MutableLiveData<Event<String>>()
    private val _eventOpenEditFormPageObservable = MutableLiveData<Event<Long>>()
    private val _eventLogoutCurrentUserObservable = MutableLiveData<Event<Unit>>()

    override val formObservable: LiveData<Form>
        get() = _formObservable
    override val stateLoadingObservable: LiveData<Boolean>
        get() = _stateLoadingObservable
    override val eventShowErrorMessageObservable: LiveData<Event<String>>
        get() = _eventShowErrorMessageObservable
    override val eventOpenEditFormPageObservable: LiveData<Event<Long>>
        get() = _eventOpenEditFormPageObservable
    override val eventLogoutCurrentUserObservable: LiveData<Event<Unit>>
        get() = _eventLogoutCurrentUserObservable

    //    var form: Form = Form.dummy()
    private lateinit var form: Form

    override fun loadFormDetailPage(clientId: Long) {
        viewModelScope.launch {
            _stateLoadingObservable.postValue(true)

            val getToken = formDataSource.getClientToken()

            if (getToken is Success) {

                val token = getToken.data

                if (token != null) {

                    val result = formDataSource.getFormDetailById(formId = clientId, token = token)

                    if (result is Success) {

                        val data = result.data ?: Form.DEFAULT
                        form = data
                        _formObservable.postValue(data)
                        _stateLoadingObservable.postValue(false)

                    } else {

                        _eventShowErrorMessageObservable.postValue(
                            Event(
                                result.message ?: "gagal memuat konten"
                            )
                        )
                        delay(1000)
                        _stateLoadingObservable.postValue(false)
                    }
                }
            } else {
                _eventShowErrorMessageObservable.postValue(
                    Event(
                        getToken.message ?: "gagal memuat konten"
                    )
                )
                delay(1000)
                _stateLoadingObservable.postValue(false)
            }
        }
    }

    override fun triggerEventOpenEditFormPage() {
        val clientId = form.id
        _eventOpenEditFormPageObservable.value = Event(clientId)
    }

    override fun triggerEventLogoutCurrentUser() {
        viewModelScope.launch {
            val result = clientDataSource.logout()
            if (result is Success) {
                val data = result.data
                _eventLogoutCurrentUserObservable.postValue(Event(data))
            } else {
                _eventShowErrorMessageObservable.postValue(Event(result.message ?: "operasi gagal"))
            }
        }
    }

    class Factory(
        private val formDataSource: FormDataSource,
        private val clientDataSource: ClientDataSource
    ) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return FormDetailViewModel(formDataSource, clientDataSource) as T
        }
    }
}