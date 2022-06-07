package com.feliz.apps.presentation.ui.form.edit.vendor.staffdress

interface EditStaffDressContract {
    interface View {
        fun showStaffDressList(list: ArrayList<Pair<String, String>>)
        fun showAddDialog()
        fun addElement(data: Pair<String, String>)
        fun deleteElement(data: Pair<String, String>)
    }
}