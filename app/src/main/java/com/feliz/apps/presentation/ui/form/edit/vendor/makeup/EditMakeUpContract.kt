package com.feliz.apps.presentation.ui.form.edit.vendor.makeup

interface EditMakeUpContract {
    interface View {
        fun showMakeUpList(list: ArrayList<Pair<String, String>>)
        fun showAddDialog()
        fun addElement(data: Pair<String, String>)
        fun deleteElement(data: Pair<String, String>)
    }
}