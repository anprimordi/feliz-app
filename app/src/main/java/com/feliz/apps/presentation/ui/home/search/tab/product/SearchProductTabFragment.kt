package com.feliz.apps.presentation.ui.home.search.tab.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.feliz.apps.R
import com.feliz.apps.databinding.FragmentSearchProductTabBinding
import com.feliz.apps.domain.model.Product
import com.feliz.apps.presentation.ui.home.search.SearchFragmentDirections
import com.feliz.apps.presentation.ui.home.search.SearchViewModel
import com.feliz.apps.presentation.util.provider.VMProvider

class SearchProductTabFragment : Fragment(), SearchProductTabContract.View {

    private lateinit var binding: FragmentSearchProductTabBinding
    private val viewModel: SearchViewModel by activityViewModels {
        VMProvider.homeSearch(
            requireContext()
        )
    }

    private val adapter = SearchProductTabListAdapter {
        openSearchResultItem(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSearchProductTabBinding.inflate(inflater)

        binding.lifecycleOwner = viewLifecycleOwner

        binding.rvProduct.adapter = adapter

        binding.swipeRefresh.setOnRefreshListener { viewModel.loadHomeSearchProductTabPage() }

        // Inflate the layout for this fragment
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
        viewModel.eventEmptyProductObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                showEmptyProductListMessage(it)
            }
        }
        viewModel.productListObservable.observe(viewLifecycleOwner) { list ->
            showSearchProductPage(list)
            viewModel.queryObservable.observe(viewLifecycleOwner) { query ->
                showSearchResult(list, query)
            }
        }
    }

    override fun showSearchProductPage(list: List<Product>) {
        adapter.submitList(list)
    }

    override fun showSearchResult(productList: List<Product>, query: String) {
        binding.layoutLoadingError.isVisible = false
        binding.layoutMain.isVisible = true

        if (productList.isNotEmpty()) adapter.submitList(productList)
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

    override fun showEmptyProductListMessage(message: String) {
        binding.layoutMain.isVisible = false
        binding.layoutLoadingError.isVisible = true

        binding.textLoadingError.text = message
    }

    override fun showEmptySearchResult(query: String) {
        binding.layoutMain.isVisible = false
        binding.layoutLoadingError.isVisible = true
        binding.textLoadingError.text = getString(R.string.msg_empty_search, query)
    }

    override fun openSearchResultItem(product: Product) {
        val action =
            SearchFragmentDirections
                .actionHomeSearchFragmentToProductFragment(
                    product.id,
                    product.categoryId
                )
        findNavController().navigate(action)
    }

}