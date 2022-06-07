package com.feliz.apps.presentation.ui.form.edit.vendor.accessories

interface EditAccessoriesContract {
    interface View {
        fun showAccessoriesList(list: ArrayList<Pair<String, String>>)
        fun showAddDialog()
        fun addElement(data: Pair<String, String>)
        fun deleteElement(data: Pair<String, String>)
    }
}