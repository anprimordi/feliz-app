package com.feliz.apps.presentation.ui.form.detail.tab.vendorlist

import com.feliz.apps.domain.model.form.Form

interface FormDetailVendorContract {
    interface View {
        fun showFormVendorPage(form: Form)
    }
}