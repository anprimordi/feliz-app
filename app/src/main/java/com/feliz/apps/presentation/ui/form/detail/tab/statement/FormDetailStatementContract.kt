package com.feliz.apps.presentation.ui.form.detail.tab.statement

import com.feliz.apps.domain.model.form.Form

interface FormDetailStatementContract {
    interface View {
        fun showFormStatement(form: Form)
    }
}