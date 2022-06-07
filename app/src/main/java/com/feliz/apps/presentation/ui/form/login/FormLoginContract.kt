package com.feliz.apps.presentation.ui.form.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.feliz.apps.domain.model.common.Event
import com.feliz.apps.domain.model.form.personaldata.Client

interface FormLoginContract {
    interface View {
        fun showLoginPage()
        fun showErrorMessage(message: String)
        fun showLoadingIndicator(isActive: Boolean)
        fun showLoginSuccessMessage(message: String)
        fun showLoginFailedMessage(message: String)
        fun openFormDetailPage(clientId: Long)
    }

    interface Presenter {
        val usernameObservable: MutableLiveData<String>
        val passwordObservable: MutableLiveData<String>
        val stateLoadingObservable: LiveData<Boolean>
        val eventLoadingErrorObservable: LiveData<Event<String>>
        val eventShowLoginSuccessMessage: LiveData<Event<String>>
        val eventShowLoginFailedMessage: LiveData<Event<String>>
        val eventLogin: LiveData<Event<Client>>
        val eventOpenFormDetailPage: LiveData<Event<Long>>

        fun loadLoginPage()
        fun triggerEventLogin()
    }
}