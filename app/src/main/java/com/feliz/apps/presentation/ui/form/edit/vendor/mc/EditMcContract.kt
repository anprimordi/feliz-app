package com.feliz.apps.presentation.ui.form.edit.vendor.mc

interface EditMcContract {
    interface View {
        fun showMcList(list: ArrayList<Pair<String, String>>)
        fun showAddDialog()
        fun addElement(data: Pair<String, String>)
        fun deleteElement(data: Pair<String, String>)
    }
}