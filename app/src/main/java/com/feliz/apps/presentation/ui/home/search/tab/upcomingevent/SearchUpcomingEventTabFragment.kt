package com.feliz.apps.presentation.ui.home.search.tab.upcomingevent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.feliz.apps.R
import com.feliz.apps.databinding.FragmentSearchUpcomingEventTabBinding
import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.presentation.ui.home.search.SearchFragmentDirections
import com.feliz.apps.presentation.ui.home.search.SearchViewModel
import com.feliz.apps.presentation.util.provider.VMProvider

class SearchUpcomingEventTabFragment : Fragment(), SearchUpcomingEventTabContract.View {

    private lateinit var binding: FragmentSearchUpcomingEventTabBinding
    private val viewModel: SearchViewModel by activityViewModels {
        VMProvider.homeSearch(
            requireContext()
        )
    }

    private val adapter = SearchUpcomingEventTabListAdapter {
        openPortfolioPage(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchUpcomingEventTabBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.rvUpcomingEvent.adapter = adapter

        binding.swipeRefresh.setOnRefreshListener { viewModel.loadHomeSearchUpcomingEventTab() }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.stateLoadingObservable.observe(viewLifecycleOwner) {
            showLoadingIndicator(it)
        }
        viewModel.eventLoadingErrorUpcomingEventObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                showErrorMessageDialog(it)
            }
        }
        viewModel.eventEmptyUpcomingEventObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                showEmptyUpcomingEventListMessage(it)
            }
        }
        viewModel.upcomingEventListObservable.observe(viewLifecycleOwner) { list ->
            showSearchUpcomingEvtPage(list)
            viewModel.queryObservable.observe(viewLifecycleOwner) { query ->
                showSearchResult(list, query)
            }
        }
    }

    override fun showSearchUpcomingEvtPage(list: List<Portfolio>) {
        adapter.submitList(list)
    }

    override fun showSearchResult(
        upcomingEventList: List<Portfolio>,
        query: String
    ) {
        binding.layoutLoadingError.isVisible = false
        binding.layoutMain.isVisible = true

        if (upcomingEventList.isNotEmpty()) adapter.submitList(upcomingEventList)
        else showEmptySearchResult(query)
    }

    override fun showLoadingIndicator(isActive: Boolean) {
        binding.swipeRefresh.isRefreshing = isActive
    }

    override fun showErrorMessageDialog(message: String) {
        binding.layoutMain.isVisible = false
        binding.layoutLoadingError.isVisible = true
        binding.textLoadingError.text = message
    }

    override fun showEmptyUpcomingEventListMessage(message: String) {
        binding.layoutMain.isVisible = false
        binding.layoutLoadingError.isVisible = true
        binding.textLoadingError.text = message
    }

    override fun showEmptySearchResult(query: String) {
        binding.layoutMain.isVisible = false
        binding.layoutLoadingError.isVisible = true
        binding.textLoadingError.text = getString(R.string.msg_empty_search, query)
    }

    override fun openPortfolioPage(portfolio: Portfolio) {
        val action =
            SearchFragmentDirections
                .actionHomeSearchFragmentToPortfolioFragment(portfolio.id)
        findNavController().navigate(action)
    }

}