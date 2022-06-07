package com.feliz.apps.presentation.ui.home.portfolio.list.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.feliz.apps.databinding.FragmentUpcomingEventListBinding
import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.presentation.util.provider.VMProvider
import com.google.android.material.appbar.MaterialToolbar

class UpcomingEventListFragment : Fragment(), UpcomingEventListContract.View {

    private lateinit var binding: FragmentUpcomingEventListBinding
    private val viewModel: UpcomingEventListViewModel by viewModels {
        VMProvider.upcomingEvent(
            requireContext()
        )
    }

    private val adapter = UpcomingEventListAdapter {
        openPortfolioPage(it.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpcomingEventListBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.swipeRefresh.setOnRefreshListener { viewModel.loadPortfolioList() }

        binding.rvUpcomingEvent.adapter = adapter

        setupToolbar(binding.toolbar)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadPortfolioList()

        viewModel.portfolioListObservable.observe(viewLifecycleOwner) {
            showUpcomingEventListPage(it)
        }
        viewModel.eventOpenPortfolioPageObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                openPortfolioPage(it.id)
            }
        }
        viewModel.eventLoadingErrorObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                showErrorMessage(it)
            }
        }
        viewModel.eventShowEmptyPageObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                showEmptyPage(it)
            }
        }
        viewModel.stateLoadingObservable.observe(viewLifecycleOwner) {
            showLoadingIndicator(it)
        }

    }

    override fun showUpcomingEventListPage(list: List<Portfolio>) {
        binding.layoutLoadingError.isVisible = false
        binding.layoutMain.isVisible = true

        adapter.submitList(list)
    }

    override fun showLoadingIndicator(isActive: Boolean) {
        binding.swipeRefresh.isRefreshing = isActive
    }

    override fun showErrorMessage(message: String) {
        binding.layoutMain.isVisible = false
        binding.layoutLoadingError.isVisible = true

        binding.textLoadingError.text = message
    }

    override fun showEmptyPage(message: String) {
        binding.layoutMain.isVisible = false
        binding.layoutLoadingError.isVisible = true
        binding.textSwipeToRefresh.isVisible = false

        binding.textLoadingError.text = message
    }

    override fun openPortfolioPage(id: Long) {
        val action =
            UpcomingEventListFragmentDirections.actionUpcomingEventListFragmentToPortfolioFragment(
                portfolioId = id
            )
        findNavController().navigate(action)
    }

    override fun setupToolbar(toolbar: MaterialToolbar) {
        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        toolbar.setupWithNavController(findNavController(), appBarConfiguration)
        NavigationUI.setupWithNavController(toolbar, findNavController(), appBarConfiguration)
    }

}