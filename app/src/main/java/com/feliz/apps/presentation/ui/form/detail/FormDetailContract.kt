package com.feliz.apps.presentation.ui.form.detail

import androidx.lifecycle.LiveData
import com.feliz.apps.domain.model.common.Event
import com.feliz.apps.domain.model.form.Form

interface FormDetailContract {
    interface View {
        fun showFormDetailPage()
        fun showLoadingIndicator(isActive: Boolean)
        fun showErrorMessage(message: String)
        fun openEditFormPage(clientId: Long)
        fun logoutCurrentUser()
    }

    interface Presenter {
        val formObservable: LiveData<Form>
        val stateLoadingObservable: LiveData<Boolean>
        val eventShowErrorMessageObservable: LiveData<Event<String>>
        val eventOpenEditFormPageObservable: LiveData<Event<Long>>
        val eventLogoutCurrentUserObservable: LiveData<Event<Unit>>

        fun loadFormDetailPage(clientId: Long)

        fun triggerEventOpenEditFormPage()
        fun triggerEventLogoutCurrentUser()
    }
}