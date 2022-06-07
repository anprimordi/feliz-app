package com.feliz.apps.presentation.ui.form.edit.vendor.catering

interface EditCateringContract {
    interface View {
        fun showCateringList(list: ArrayList<Pair<String, String>>)
        fun showAddDialog()
        fun addElement(data: Pair<String, String>)
        fun deleteElement(data: Pair<String, String>)
    }
}