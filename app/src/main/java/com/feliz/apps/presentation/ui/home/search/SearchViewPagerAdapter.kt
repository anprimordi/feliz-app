package com.feliz.apps.presentation.ui.home.search

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.feliz.apps.presentation.ui.home.search.tab.finishedevent.SearchFinishedEventTabFragment
import com.feliz.apps.presentation.ui.home.search.tab.product.SearchProductTabFragment
import com.feliz.apps.presentation.ui.home.search.tab.upcomingevent.SearchUpcomingEventTabFragment
import com.feliz.apps.presentation.ui.home.search.tab.vendor.SearchVendorTabFragment

class SearchViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    private val fragments = arrayListOf(
        SearchProductTabFragment(),
        SearchVendorTabFragment(),
        SearchUpcomingEventTabFragment(),
        SearchFinishedEventTabFragment()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}