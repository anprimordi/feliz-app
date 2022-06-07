package com.feliz.apps.presentation.ui.home.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.feliz.apps.R
import com.feliz.apps.databinding.FragmentSearchBinding
import com.feliz.apps.presentation.util.provider.VMProvider
import com.google.android.material.tabs.TabLayoutMediator
import timber.log.Timber

class SearchFragment : Fragment(), SearchContract.View {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by activityViewModels {
        VMProvider.homeSearch(
            requireContext()
        )
    }
    private lateinit var searchViewPager: SearchViewPagerAdapter
    private var searchIsOpened = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.textCancel.setOnClickListener {
            if (searchIsOpened) {
                toggleSoftKeyboard(binding.editSearch)
                findNavController().navigateUp()
            } else findNavController().navigateUp()
        }

        setupAdapter()

//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
//            if (!searchIsOpened) {
//                toogleSoftKeyboard(binding.editSearch)
//            } else {
//                findNavController().navigateUp()
//            }
//        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadSearchResult()
        viewModel.loadHomeSearchProductTabPage()
        viewModel.loadHomeSearchVendorTabPage()
        viewModel.loadHomeSearchUpcomingEventTab()
        viewModel.loadHomeSearchFinishedEventTab()
    }

    override fun showHomeSearchPage(query: String) {
        binding.editSearch.setText(query)
    }

    override fun setupAdapter() {
        searchViewPager = SearchViewPagerAdapter(this)
        binding.vpSearch.adapter = searchViewPager
        TabLayoutMediator(binding.tabs, binding.vpSearch) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.title_product)
                1 -> tab.text = getString(R.string.title_vendor)
                2 -> tab.text = getString(R.string.title_upcoming_event)
                3 -> tab.text = getString(R.string.title_finished_event)
            }
        }.attach()
    }

    override fun toggleSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm =
                view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if (!searchIsOpened) {
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
                searchIsOpened = true
                Timber.e("$searchIsOpened")
            } else {
                imm.hideSoftInputFromWindow(view.windowToken, 0)
                searchIsOpened = false
                Timber.e("$searchIsOpened")
            }
        }
    }

    override fun onResume() {
        super.onResume()

        binding.editSearch.requestFocus()
        toggleSoftKeyboard(binding.editSearch)
    }
}