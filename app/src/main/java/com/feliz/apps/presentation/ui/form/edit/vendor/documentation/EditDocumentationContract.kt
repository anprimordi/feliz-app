package com.feliz.apps.presentation.ui.form.edit.vendor.documentation

interface EditDocumentationContract {
    interface View {
        fun showDocumentationList(list: ArrayList<Pair<String, String>>)
        fun showAddDialog()
        fun addElement(data: Pair<String, String>)
        fun deleteElement(data: Pair<String, String>)
    }
}