package com.feliz.apps.presentation.ui.home.search.tab.finishedevent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.feliz.apps.R
import com.feliz.apps.databinding.FragmentSearchFinishedEventTabBinding
import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.presentation.ui.home.search.SearchFragmentDirections
import com.feliz.apps.presentation.ui.home.search.SearchViewModel
import com.feliz.apps.presentation.util.provider.VMProvider

class SearchFinishedEventTabFragment : Fragment(), SearchFinishedEventTabContract.View {

    private lateinit var binding: FragmentSearchFinishedEventTabBinding
    private val viewModel: SearchViewModel by activityViewModels {
        VMProvider.homeSearch(
            requireContext()
        )
    }

    private val adapter = SearchFinishedEventTabListAdapter {
        openPortfolioPage(it)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchFinishedEventTabBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.rvFinishedEvent.adapter = adapter

        binding.swipeRefresh.setOnRefreshListener { viewModel.loadHomeSearchFinishedEventTab() }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.stateLoadingObservable.observe(viewLifecycleOwner) {
            showLoadingIndicator(it)
        }
        viewModel.eventLoadingErrorProductObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                showErrorMessageDialog(it)
            }
        }
        viewModel.eventEmptyFinishedEventObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                showEmptyFinishedEventListMessage(it)
            }
        }
        viewModel.finishedEventListObservable.observe(viewLifecycleOwner) { list ->
            showSearchFinishedEvtPage(list)
            viewModel.queryObservable.observe(viewLifecycleOwner) { query ->
                showSearchResult(list, query)
            }
        }
    }

    override fun showSearchFinishedEvtPage(list: List<Portfolio>) {
        adapter.submitList(list)
    }

    override fun showSearchResult(
        finishedEventList: List<Portfolio>,
        query: String
    ) {
        binding.layoutLoadingError.isVisible = false
        binding.layoutMain.isVisible = true

        if (finishedEventList.isNotEmpty()) adapter.submitList(finishedEventList)
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

    override fun showEmptyFinishedEventListMessage(message: String) {
        binding.layoutMain.isVisible = false
        binding.layoutLoadingError.isVisible = true
        binding.textSwipeToRefresh.isVisible = false

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