package com.feliz.apps.presentation.ui.form.edit.vendor.venue

interface EditVenueContract {
    interface View {
        fun showDecorationList(list: ArrayList<Pair<String, String>>)
        fun showAddDialog()
        fun addElement(data: Pair<String, String>)
        fun deleteElement(data: Pair<String, String>)
    }
}