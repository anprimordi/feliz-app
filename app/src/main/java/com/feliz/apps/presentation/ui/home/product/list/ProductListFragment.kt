package com.feliz.apps.presentation.ui.home.product.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.feliz.apps.R
import com.feliz.apps.databinding.FragmentProductListBinding
import com.feliz.apps.domain.model.Product
import com.feliz.apps.domain.model.ProductCategory
import com.feliz.apps.presentation.ui.home.adapter.HomeOurProductListAdapter
import com.feliz.apps.presentation.util.provider.VMProvider

class ProductListFragment : Fragment(), ProductListContract.View {

    private lateinit var binding: FragmentProductListBinding
    private val viewModel: ProductListViewModel by viewModels {
        VMProvider.productCategory(
            requireContext()
        )
    }

    private val productPriceListAdapter = ProductListPriceListAdapter { product, name ->
        openProductPage(product, name)
    }

    private val moreProductAdapter = HomeOurProductListAdapter { item ->
        openMoreProductCategory(item)
    }

    private val args: ProductListFragmentArgs by navArgs()

    private var productCategoryId: Long = 0
    private lateinit var productCategoryName: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val categoryId = args.productCategoryId

        binding = FragmentProductListBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.swipeRefresh.setOnRefreshListener { viewModel.loadProductCategory(categoryId) }

        binding.rvProduct.adapter = productPriceListAdapter

        binding.rvMoreProductCategory.adapter = moreProductAdapter

        binding.buttonNavigateUp.setOnClickListener { findNavController().navigateUp() }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryId = args.productCategoryId
        productCategoryId = categoryId

        viewModel.loadProductCategory(categoryId)
        viewModel.loadMoreProductCategoryList()

        viewModel.productCategoryObservable.observe(viewLifecycleOwner) {
            showProductCategory(it)
        }

        viewModel.moreProductCategoryListObservable.observe(viewLifecycleOwner) {
            showMoreProductCategoryList(it)
        }

        viewModel.stateLoadingObservable.observe(viewLifecycleOwner) {
            showLoadingIndicator(it)
        }

        viewModel.eventLoadingErrorObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                showErrorMessageView(it)
            }
        }

        viewModel.eventOpenProductPageObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let { product ->
                viewModel.productCategoryNameObservable.observe(viewLifecycleOwner) { categoryName ->
                    openProductPage(product, categoryName)
                }
            }
        }

        viewModel.eventOpenMoreProductCategoryObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                openMoreProductCategory(it)
            }
        }
    }

    override fun showProductCategory(productCategory: ProductCategory) {
        binding.layoutLoadingError.isVisible = false
        binding.layoutProductCategory.isVisible = true
        binding.buttonNavigateUp.isVisible = true

        Glide.with(requireContext())
            .load(productCategory.photoUrl)
            .into(binding.imageMedia)

        binding.textName.text = productCategory.name
        binding.textDescription.text = productCategory.description
        binding.textPriceListTitle.text =
            getString(R.string.text_price_list_title, productCategory.name)
        binding.textPriceListSubtitle.text =
            getString(R.string.text_price_list_subtitle, productCategory.name)
        productPriceListAdapter.submitList(productCategory.products)

        productCategoryName = productCategory.name
    }

    override fun showMoreProductCategoryList(listProduct: List<ProductCategory>) {
        val list = listProduct.filter { it.id != args.productCategoryId }
        moreProductAdapter.submitList(list)
    }

    override fun showErrorMessageView(message: String) {
        binding.layoutProductCategory.isVisible = false
        binding.buttonNavigateUp.isVisible = false
        binding.layoutLoadingError.isVisible = true
        binding.textLoadingError.text = message
    }

    override fun showLoadingIndicator(isActive: Boolean) {
        binding.swipeRefresh.isRefreshing = isActive
    }

    override fun openProductPage(product: Product, categoryName: String) {
        val action =
            ProductListFragmentDirections.actionProductCategoryFragmentToProductFragment(
                product.id,
                product.categoryId
            )
        findNavController().navigate(action)
    }

    override fun openMoreProductCategory(category: ProductCategory) {
        val action =
            ProductListFragmentDirections.actionProductCategoryFragmentSelf(category.id)
        findNavController().navigate(action)
    }

}