package com.feliz.apps.presentation.ui.home.portfolio.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.feliz.apps.R
import com.feliz.apps.databinding.FragmentPortfolioBinding
import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.domain.model.util.DateTimeUtils
import com.feliz.apps.presentation.ui.home.adapter.HomePortfolioListAdapter
import com.feliz.apps.presentation.util.provider.VMProvider
import com.google.android.material.appbar.MaterialToolbar

class PortfolioFragment : Fragment(), PortfolioContract.View {

    private lateinit var binding: FragmentPortfolioBinding
    private val viewModel: PortfolioViewModel by viewModels { VMProvider.portfolio(requireContext()) }
    private val args: PortfolioFragmentArgs by navArgs()
    private val upcomingEventListAdapter = HomePortfolioListAdapter { item ->
        openUpcomingEventPage(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val portfolioId = args.portfolioId

        binding = FragmentPortfolioBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadPortfolio(portfolioId)
            viewModel.loadUpcomingEvent()
        }

        binding.rvUpcomingEvent.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.rvUpcomingEvent.adapter = upcomingEventListAdapter

        viewModel.loadPortfolio(portfolioId)
        viewModel.loadUpcomingEvent()

        viewModel.portfolioObservable.observe(viewLifecycleOwner) {
            showPortfolio(it)
            setupToolbar(binding.toolbar, it.name)
        }
        viewModel.listPortfolioObservable.observe(viewLifecycleOwner) {
            showPortfolioList(it)
        }
        viewModel.stateLoadingObservable.observe(viewLifecycleOwner) {
            showLoadingIndicator(it)
        }
        viewModel.eventLoadingErrorObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                showErrorMessage(it)
            }
        }
        viewModel.eventOpenUpcomingEventObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                openUpcomingEventPage(it)
            }
        }

        return binding.root
    }

    override fun showPortfolio(portfolio: Portfolio) {
//        val dateLong = Date(portfolio.dateTime)
//        val formatter = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault())
//        val date = formatter.format(dateLong)

        binding.layoutLoadingError.visibility = View.GONE
        binding.layoutMain.visibility = View.VISIBLE

        Glide.with(requireContext())
            .load(portfolio.photoUrl)
            .centerCrop()
            .into(binding.imageThumbnail)

        binding.textCategory.text = portfolio.product.category.name
        binding.textName.text = portfolio.name
        binding.textDescription.text = portfolio.description
        binding.textProduct.text = portfolio.product.name
        binding.textDate.text = DateTimeUtils.formatDayDate(portfolio.dateTime)
        binding.textLocation.text = portfolio.location
        binding.textTime.text = DateTimeUtils.formatTime(portfolio.dateTime)
        binding.textMoreEventTitle.text = getString(R.string.text_more_portfolio_title)
        binding.textMoreEventDescription.text =
            getString(R.string.text_more_portfolio_desc)
    }

    override fun setupToolbar(toolbar: MaterialToolbar, title: String) {
        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        toolbar.setupWithNavController(findNavController(), appBarConfiguration)
        NavigationUI.setupWithNavController(toolbar, findNavController(), appBarConfiguration)
        toolbar.title = title
    }


    override fun showPortfolioList(list: List<Portfolio>) {
        if (!list.isNullOrEmpty()) {
            binding.textMoreEventTitle.isVisible = true
            binding.textMoreEventDescription.isVisible = true
            val newList = list.filter { it.id != args.portfolioId }
            upcomingEventListAdapter.submitList(newList)
        } else {
            binding.textMoreEventTitle.isVisible = false
            binding.textMoreEventDescription.isVisible = false
        }

    }

    override fun showLoadingIndicator(isActive: Boolean) {
        binding.swipeRefresh.isRefreshing = isActive
    }

    override fun showErrorMessage(message: String) {
        binding.layoutMain.visibility = View.GONE
        binding.layoutLoadingError.visibility = View.VISIBLE
        binding.textLoadingError.text = message
    }

    override fun openUpcomingEventPage(portfolio: Portfolio) {
        val action = PortfolioFragmentDirections.actionPortfolioFragmentSelf(portfolio.id)
        findNavController().navigate(action)
    }
}