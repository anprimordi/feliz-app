package com.feliz.apps.presentation.ui.home.portfolio.list.finished

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
import com.feliz.apps.databinding.FragmentFinishedEventListBinding
import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.presentation.util.provider.VMProvider
import com.google.android.material.appbar.MaterialToolbar


class FinishedEventListFragment : Fragment(), FinishedEventListContract.View {

    private lateinit var binding: FragmentFinishedEventListBinding
    private val viewModel: FinishedEventListViewModel by viewModels {
        VMProvider.finishedEvent(
            requireContext()
        )
    }

    private val adapter = FinishedEventListAdapter {
        openPortfolioPage(it.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFinishedEventListBinding.inflate(inflater)

        binding.swipeRefresh.setOnRefreshListener { viewModel.loadPortfolioList() }

        binding.rvFinishedEvent.adapter = adapter
        setupToolbar(binding.toolbar)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadPortfolioList()

        viewModel.portfolioListObservable.observe(viewLifecycleOwner) {
            showFinishedEventListPage(it)
        }
        viewModel.stateLoadingObservable.observe(viewLifecycleOwner) {
            showLoadingIndicator(it)
        }
        viewModel.eventLoadingErrorObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                showErrorMessage(it)
            }
        }
        viewModel.eventEmptyPageObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                showEmptyPage(it)
            }
        }
        viewModel.eventOpenPortfolioPageObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                openPortfolioPage(it.id)
            }
        }
    }

    override fun showFinishedEventListPage(list: List<Portfolio>) {
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
            FinishedEventListFragmentDirections.actionFinishedEventListFragmentToPortfolioFragment(
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