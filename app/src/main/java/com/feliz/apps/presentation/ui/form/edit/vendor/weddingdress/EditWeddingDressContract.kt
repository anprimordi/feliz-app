package com.feliz.apps.presentation.ui.form.edit.vendor.weddingdress

interface EditWeddingDressContract {
    interface View {
        fun showWeddingDressList(list: ArrayList<Pair<String, String>>)
        fun showAddDialog()
        fun addElement(data: Pair<String, String>)
        fun deleteElement(data: Pair<String, String>)
    }
}