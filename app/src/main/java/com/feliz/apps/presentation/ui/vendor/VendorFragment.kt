package com.feliz.apps.presentation.ui.vendor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.feliz.apps.R
import com.feliz.apps.databinding.FragmentVendorBinding
import com.feliz.apps.domain.model.VendorCategory
import com.feliz.apps.presentation.util.provider.VMProvider
import com.google.android.material.appbar.MaterialToolbar

class VendorFragment : Fragment(), VendorContract.View {

    private lateinit var binding: FragmentVendorBinding
    private val viewModel: VendorViewModel by viewModels { VMProvider.vendor(requireContext()) }

    private val vendorAdapter = VendorAdapter {
        openVendorListPage(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVendorBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.swipeRefresh.setOnRefreshListener { viewModel.loadVendorCategoryList() }

        binding.rvVendorCategory.adapter = vendorAdapter
        setupToolbar(binding.toolbar)
        viewModel.loadVendorCategoryList()

        viewModel.vendorCategoryListObservable.observe(viewLifecycleOwner) {
            showVendorCategoryList(it)
        }

        viewModel.stateLoadingObservable.observe(viewLifecycleOwner) {
            showLoadingIndicator(it)
        }

        viewModel.eventLoadingErrorObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                showErrorMessageDialog(it)
            }
        }

        viewModel.eventOpenVendorListPageObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                openVendorListPage(it)
            }
        }

        return binding.root
    }

    override fun showVendorCategoryList(categoryList: List<VendorCategory>) {
        binding.layoutLoadingError.visibility = View.GONE
        binding.layoutMain.visibility = View.VISIBLE

        vendorAdapter.submitList(categoryList)
    }

    override fun showLoadingIndicator(isActive: Boolean) {
        binding.swipeRefresh.isRefreshing = isActive
    }

    override fun showErrorMessageDialog(message: String) {
        binding.layoutMain.visibility = View.GONE
        binding.layoutLoadingError.visibility = View.VISIBLE
        binding.textLoadingError.text = message
    }

    override fun openVendorListPage(category: VendorCategory) {
        val action = VendorFragmentDirections.actionVendorFragmentToVendorListFragment(
            category.id,
            category.name
        )
        findNavController().navigate(action)
    }

    private fun setupToolbar(toolbar: MaterialToolbar) {
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.vendorFragment, R.id.formFragment
            )
        )
        toolbar.setupWithNavController(findNavController(), appBarConfiguration)
        NavigationUI.setupWithNavController(toolbar, findNavController(), appBarConfiguration)
        toolbar.title = null
    }
}