package com.feliz.apps.presentation.ui.form.detail.tab.personaldata

import com.feliz.apps.domain.model.form.Form

interface FormDetailPersonalDataContract {
    interface View {
        fun showFormPersonalDataPage(form: Form)
    }
}