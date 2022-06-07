package com.feliz.apps.presentation.ui.vendor.list

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
import com.feliz.apps.R
import com.feliz.apps.databinding.FragmentVendorListBinding
import com.feliz.apps.domain.model.Vendor
import com.feliz.apps.presentation.util.provider.VMProvider
import com.google.android.material.appbar.MaterialToolbar

class VendorListFragment : Fragment(), VendorListContract.View {

    private lateinit var binding: FragmentVendorListBinding
    private val viewModel: VendorListViewModel by viewModels { VMProvider.vendorList(requireContext()) }
    private val args: VendorListFragmentArgs by navArgs()
    private val vendorListAdapter = VendorListAdapter {
        openVendorDetailPage(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVendorListBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.swipeRefresh.setOnRefreshListener {
            val categoryId = args.vendorCategoryId
            val categoryName = args.vendorCategoryName
            viewModel.loadVendorCategory(categoryName)
            viewModel.loadVendorList(categoryId)
        }

//        binding.rvVendorList.layoutManager =
//            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.rvVendorList.adapter = vendorListAdapter

        setupToolbar(binding.toolbar)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryId = args.vendorCategoryId
        val categoryName = args.vendorCategoryName
        viewModel.loadVendorCategory(categoryName)
        viewModel.loadVendorList(categoryId)

        viewModel.vendorCategoryObservable.observe(viewLifecycleOwner) {
            showVendorCategoryPage(it)
        }

        viewModel.vendorListObservable.observe(viewLifecycleOwner) { list ->
            showVendorListPage(list)
            viewModel.queryObservable.observe(viewLifecycleOwner) { query ->
                showSearchResult(list, query)
            }
        }

        viewModel.stateLoadingObservable.observe(viewLifecycleOwner) {
            showLoadingIndicator(it)
        }

        viewModel.eventShowEmptyMessageObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                showEmptyMessage(it)
            }
        }

        viewModel.eventLoadingErrorObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                showErrorMessage(it)
            }
        }

        viewModel.eventOpenVendorDetailPageObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                openVendorDetailPage(it)
            }
        }
    }

    override fun showVendorCategoryPage(categoryName: String) {
        binding.layoutLoadingError.isVisible = false
        binding.layoutMain.isVisible = true
        binding.textName.text = categoryName
        binding.textCategoryJargon.text = getString(R.string.plain_app_jargon)
//        binding.editSearch.isFocused
    }

    override fun showVendorListPage(list: List<Vendor>) {
        vendorListAdapter.submitList(list)
    }

    override fun showSearchResult(list: List<Vendor>, query: String) {
        binding.layoutLoadingError.isVisible = false
        binding.layoutMain.isVisible = true

        if (list.isNotEmpty()) vendorListAdapter.submitList(list)
        else showEmptyResult(query)
    }

    override fun showEmptyResult(query: String) {
        binding.layoutMain.isVisible = false
        binding.layoutLoadingError.isVisible = true
        binding.textLoadingError.text = getString(R.string.msg_empty_search, query)
    }

    override fun showLoadingIndicator(isActive: Boolean) {
        binding.swipeRefresh.isRefreshing = isActive
    }

    override fun showEmptyMessage(message: String) {
        binding.layoutMain.visibility = View.GONE
        binding.layoutLoadingError.visibility = View.VISIBLE
        binding.textLoadingError.text = message
    }

    override fun showErrorMessage(message: String) {
        binding.layoutMain.visibility = View.GONE
        binding.layoutLoadingError.visibility = View.VISIBLE
        binding.textLoadingError.text = message
    }

    override fun openVendorDetailPage(vendor: Vendor) {
        val action =
            VendorListFragmentDirections.actionVendorListFragmentToVendorDetailFragment(
                vendor.id,
                vendor.categoryId
            )
        findNavController().navigate(action)
    }

    private fun setupToolbar(toolbar: MaterialToolbar) {
        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        toolbar.setupWithNavController(findNavController(), appBarConfiguration)
        NavigationUI.setupWithNavController(toolbar, findNavController(), appBarConfiguration)
    }
}