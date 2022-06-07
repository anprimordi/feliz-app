package com.feliz.apps.presentation.ui.form.detail

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.feliz.apps.presentation.ui.form.detail.tab.personaldata.FormDetailPersonalDataFragment
import com.feliz.apps.presentation.ui.form.detail.tab.statement.FormDetailStatementFragment
import com.feliz.apps.presentation.ui.form.detail.tab.vendorlist.FormDetailVendorFragment

class FormDetailViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private lateinit var fragment: Fragment

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> fragment = FormDetailPersonalDataFragment()
            1 -> fragment = FormDetailVendorFragment()
            2 -> fragment = FormDetailStatementFragment()
        }
        return fragment
    }
}