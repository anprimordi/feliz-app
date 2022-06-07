package com.feliz.apps.presentation.ui.home.search.tab.vendor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.feliz.apps.R
import com.feliz.apps.databinding.FragmentSearchVendorTabBinding
import com.feliz.apps.domain.model.Vendor
import com.feliz.apps.presentation.ui.home.search.SearchFragmentDirections
import com.feliz.apps.presentation.ui.home.search.SearchViewModel
import com.feliz.apps.presentation.util.provider.VMProvider

class SearchVendorTabFragment : Fragment(), SearchVendorTabContract.View {

    private lateinit var binding: FragmentSearchVendorTabBinding
    private val viewModel: SearchViewModel by activityViewModels {
        VMProvider.homeSearch(
            requireContext()
        )
    }

    private val adapter = SearchVendorTabListAdapter {
        openVendorPage(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchVendorTabBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.rvVendor.adapter = adapter

        binding.swipeRefresh.setOnRefreshListener { viewModel.loadHomeSearchVendorTabPage() }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.stateLoadingObservable.observe(viewLifecycleOwner) {
            showLoadingIndicator(it)
        }
        viewModel.eventLoadingErrorVendorObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                showErrorMessageDialog(it)
            }
        }
        viewModel.eventEmptyVendorObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                showEmptyVendorListMessage(it)
            }
        }
        viewModel.vendorListObservable.observe(viewLifecycleOwner) { list ->
            showSearchVendorPage(list)
            viewModel.queryObservable.observe(viewLifecycleOwner) { query ->
                showSearchResult(list, query)
            }
        }
    }

    override fun showSearchVendorPage(list: List<Vendor>) {
        adapter.submitList(list)
    }

    override fun showSearchResult(vendorList: List<Vendor>, query: String) {
        binding.layoutLoadingError.isVisible = false
        binding.layoutMain.isVisible = true

        if (vendorList.isNotEmpty()) adapter.submitList(vendorList)
        else showEmptyMessageDialog(query)
    }

    override fun showLoadingIndicator(isActive: Boolean) {
        binding.swipeRefresh.isRefreshing = isActive
    }

    override fun showEmptyMessageDialog(query: String) {
        binding.layoutMain.visibility = View.GONE
        binding.layoutLoadingError.visibility = View.VISIBLE
        binding.textLoadingError.text = getString(R.string.msg_empty_search, query)
    }

    override fun showEmptyVendorListMessage(message: String) {
        binding.layoutMain.visibility = View.GONE
        binding.layoutLoadingError.visibility = View.VISIBLE
        binding.textLoadingError.text = message
    }

    override fun showErrorMessageDialog(message: String) {
        binding.layoutMain.visibility = View.GONE
        binding.layoutLoadingError.visibility = View.VISIBLE
        binding.textLoadingError.text = message
    }

    override fun openVendorPage(vendor: Vendor) {
        val action =
            SearchFragmentDirections
                .actionHomeSearchFragmentToVendorDetailFragment(vendor.id, vendor.categoryId)
        findNavController().navigate(action)
    }

}