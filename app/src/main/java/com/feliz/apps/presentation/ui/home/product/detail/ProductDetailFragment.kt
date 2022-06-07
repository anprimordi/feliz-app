package com.feliz.apps.presentation.ui.home.product.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.feliz.apps.R
import com.feliz.apps.databinding.FragmentProductDetailBinding
import com.feliz.apps.domain.model.Portfolio
import com.feliz.apps.domain.model.Product
import com.feliz.apps.domain.model.util.CurrencyUtils
import com.feliz.apps.presentation.util.provider.VMProvider
import com.zhpan.bannerview.constants.PageStyle
import com.zhpan.indicator.enums.IndicatorSlideMode


class ProductDetailFragment : Fragment(), ProductDetailContract.View {

    private lateinit var binding: FragmentProductDetailBinding
    private val viewModel: ProductDetailViewModel by viewModels { VMProvider.product(requireContext()) }

    private val args: ProductDetailFragmentArgs by navArgs()

//    private val productId = args.productId
//    private val productCategoryId = args.productCategoryId
//    private val productCategoryName = args.productCategoryName

/*    private val productPortfolioAdapter = ProductDetailPortfolioListAdapter() {
        openPortfolioPage(it)
    }*/

    private lateinit var productPortfolioAdapter: ProductDetailPortfolioListAdapter

    private val moreProductAdapter = ProductDetailMoreProduct { product ->
        openMoreProductPage(product)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val productId = args.productId
        val productCategoryId = args.productCategoryId



        binding = FragmentProductDetailBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadProduct(productId)
            viewModel.loadMoreProductList(productCategoryId)
        }

        viewModel.loadProduct(productId)
        viewModel.loadMoreProductList(productCategoryId)

        viewModel.productCategoryName.observe(viewLifecycleOwner) { categoryName ->
            productPortfolioAdapter = ProductDetailPortfolioListAdapter(categoryName) { item ->
                openPortfolioPage(item)
            }

            binding.rvPortfolio.adapter = productPortfolioAdapter

            binding.rvMoreProduct.adapter = moreProductAdapter

            viewModel.productObservable.observe(viewLifecycleOwner) { product ->
                showProduct(product, categoryName)
            }
            viewModel.eventOpenMoreProductPageObservable.observe(viewLifecycleOwner) { event ->
                event.getAvailableEvent()?.let {
                    openMoreProductPage(it)
                }
            }
        }


        viewModel.moreProductListObservable.observe(viewLifecycleOwner) {
            showMoreProductList(it)
        }
        viewModel.stateLoadingObservable.observe(viewLifecycleOwner) {
            showLoadingIndicator(it)
        }

        viewModel.eventLoadingErrorObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                showErrorMessage(it)
            }
        }
        viewModel.eventOpenPortfolioPageObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                openPortfolioPage(it)
            }
        }



        binding.buttonNavigateUp.setOnClickListener { findNavController().navigateUp() }

        return binding.root
    }

    override fun showProduct(product: Product, name: String) {

        binding.layoutLoadingError.isVisible = false
        binding.layoutProduct.isVisible = true

        showBanner(product)
        binding.textCategory.text = name
        binding.textName.text = product.name
        binding.textPrice.text = CurrencyUtils.formatIdr(product.price)
        binding.textFacility.text = product.detail
        binding.textPortfolioSubtitle.text =
            getString(R.string.text_product_portfolio_subtitle, product.name)
        binding.textMoreProductCategorySubtitle.text =
            getString(R.string.text_price_list_subtitle, name)
        productPortfolioAdapter.submitList(product.portfolio)
    }

    override fun showMoreProductList(listProduct: List<Product>) {
        val list = listProduct.filter { it.id != args.productId }
        moreProductAdapter.submitList(list)
    }

    override fun showLoadingIndicator(isActive: Boolean) {
        binding.swipeRefresh.isRefreshing = isActive
    }

    override fun showErrorMessage(message: String) {
        binding.layoutProduct.visibility = View.GONE
        binding.layoutLoadingError.visibility = View.VISIBLE
        binding.textLoadingError.text = message
    }

    override fun openPortfolioPage(portfolio: Portfolio) {
        val action =
            ProductDetailFragmentDirections.actionProductFragmentToPortfolioFragment(portfolio.id)
        findNavController().navigate(action)
    }

    override fun openMoreProductPage(product: Product) {
        val action = ProductDetailFragmentDirections.actionProductFragmentSelf(
            product.id,
            product.categoryId
        )
        findNavController().navigate(action)
    }

    private fun showBanner(product: Product) {
        val photos = arrayListOf<String>()

        product.photoUrls.forEach {
            photos.add(it.photoUrl)
        }

        binding.bvpMedia.apply {
            setIndicatorSlideMode(IndicatorSlideMode.WORM)
            setIndicatorSliderColor(
                getColor(requireContext(), R.color.grey),
                getColor(requireContext(), R.color.orange)
            )
            setIndicatorSliderRadius(
                resources.getDimensionPixelOffset(R.dimen.banner_slider_normal),
                resources.getDimensionPixelOffset(R.dimen.banner_slider_checked)
            )
            setLifecycleRegistry(lifecycle)
            setAdapter(ProductDetailBannerAdapter())
            setPageStyle(PageStyle.MULTI_PAGE_SCALE)
            create(photos)
        }
    }
}