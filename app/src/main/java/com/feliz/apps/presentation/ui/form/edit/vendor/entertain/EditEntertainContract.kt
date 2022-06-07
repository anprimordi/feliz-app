package com.feliz.apps.presentation.ui.form.edit.vendor.entertain

interface EditEntertainContract {
    interface View {
        fun showEntertainList(list: ArrayList<Pair<String, String>>)
        fun showAddDialog()
        fun addElement(data: Pair<String, String>)
        fun deleteElement(data: Pair<String, String>)
    }
}