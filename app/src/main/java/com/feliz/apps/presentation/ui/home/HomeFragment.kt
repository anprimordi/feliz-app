package com.feliz.apps.presentation.ui.home

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.feliz.apps.R
import com.feliz.apps.databinding.FragmentHomeBinding
import com.feliz.apps.domain.model.*
import com.feliz.apps.presentation.ui.home.adapter.*
import com.feliz.apps.presentation.util.provider.VMProvider
import com.google.android.material.appbar.MaterialToolbar
import com.zhpan.bannerview.constants.PageStyle
import com.zhpan.indicator.enums.IndicatorSlideMode

class HomeFragment : Fragment(), HomeContract.View {

//    private val upcomingActionClicked = { item: Portfolio ->
//        openDetailEventPage(item)
//    }
//    private val upcomingListAdapter = HomeUpcomingListAdapter(upcomingActionClicked)

//    private val upcomingActionClicked = { item: Portfolio ->
//        openDetailEventPage(item)
//    }

    //    private val upcomingListAdapter = HomeUpcomingListAdapter(
//        actionClicked = {  },
//        actionSwipe = {  }
//    )

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels { VMProvider.home(requireContext()) }


    private val ourProductActionClicked = { item: ProductCategory ->
        openProductCategoryPage(item)
    }

    private val upcomingListAdapter = HomePortfolioListAdapter { item: Portfolio ->
        openPortfolioPage(item)
    }

    private val finishedListAdapter = HomePortfolioListAdapter { item: Portfolio ->
        openPortfolioPage(item)
    }

    private val vendorCategoryListAdapter = HomeVendorCategoryListAdapter {
        openVendorPage(it)
    }


/*    private val decorationListAdapter = HomeVendorListAdapter {
        openVendorPage(it)
    }

    private val photographerListAdapter = HomeVendorListAdapter {
        openVendorPage(it)
    }

    private val lightingListAdapter = HomeVendorListAdapter {
        openVendorPage(it)
    }

    private val soundSystemListAdapter = HomeVendorListAdapter {
        openVendorPage(it)
    }

    private val makeUpListAdapter = HomeVendorListAdapter {
        openVendorPage(it)
    }*/

    private val ourProductAdapter = HomeOurProductListAdapter(ourProductActionClicked)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.layoutMain.isVisible = true
        binding.view = this

        setupToolbar(binding.toolbar)
        setupAdapter()

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadBannerImageList()
            viewModel.loadProductCategoryList()
            viewModel.loadUpcomingList()
            viewModel.loadFinishedList()
            viewModel.loadVendorList()
        }

        viewModel.loadBannerImageList()
        viewModel.loadProductCategoryList()
        viewModel.loadUpcomingList()
        viewModel.loadFinishedList()
        viewModel.loadVendorList()

        viewModel.bannerImageListObservable.observe(viewLifecycleOwner) {
            showBannerImageList(it)
        }
        viewModel.productCategoryListObservable.observe(viewLifecycleOwner) {
            showProductCategoryList(it)
        }
        viewModel.upcomingListObservable.observe(viewLifecycleOwner) {
            showUpcomingList(it)
        }
        viewModel.finishedListObservable.observe(viewLifecycleOwner) {
            showFinishedList(it)
        }
        viewModel.vendorCategoryListObservable.observe(viewLifecycleOwner) {
            showVendorCategoryList(it)
        }
        viewModel.stateLoadingObservable.observe(viewLifecycleOwner) {
            showLoadingIndicator(it)
        }
        viewModel.eventOpenProductCategoryPageObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                openProductCategoryPage(it)
            }
        }
        viewModel.eventOpenPortfolioPageObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                openPortfolioPage(it)
            }
        }
        viewModel.eventOpenVendorPageObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                openVendorPage(it)
            }
        }
        return binding.root
    }

    private fun setupAdapter() {
        binding.rvOurProduct.adapter = ourProductAdapter
        binding.rvUpcomingEvent.adapter = upcomingListAdapter
        binding.rvFinishedEvent.adapter = finishedListAdapter
        binding.rvVendorCategory.adapter = vendorCategoryListAdapter
/*        binding.rvDecorationEvent.adapter = decorationListAdapter
        binding.rvPhotographerEvent.adapter = photographerListAdapter
        binding.rvLightingEvent.adapter = lightingListAdapter
        binding.rvSoundEvent.adapter = soundSystemListAdapter
        binding.rvMakeupEvent.adapter = makeUpListAdapter*/
    }

    override fun showBannerImageList(imageUrlList: List<String>) {
        binding.bvpHome.apply {
            setIndicatorSlideMode(IndicatorSlideMode.WORM)
            setIndicatorSliderColor(
                ContextCompat.getColor(requireContext(), R.color.grey),
                ContextCompat.getColor(requireContext(), R.color.orange)
            )
            setIndicatorSliderRadius(
                resources.getDimensionPixelOffset(R.dimen.banner_slider_normal),
                resources.getDimensionPixelOffset(R.dimen.banner_slider_checked)
            )
            setAdapter(HomeBannerViewPagerAdapter())
            setPageMargin(resources.getDimensionPixelOffset(R.dimen.banner_slider_page_margin))
            setRevealWidth(resources.getDimensionPixelOffset(R.dimen.banner_reveal_width))
            setPageStyle(PageStyle.MULTI_PAGE_SCALE)
            create(imageUrlList)
        }
    }

    override fun showProductCategoryList(categoryList: List<ProductCategory>) {
//        val list = categoryList
//        Toast.makeText(requireContext(), list.size.toString(), Toast.LENGTH_SHORT).show()
        ourProductAdapter.submitList(categoryList)
    }

    override fun showUpcomingList(upcomingList: List<Portfolio>) {
        upcomingListAdapter.submitList(upcomingList)
    }

    override fun showFinishedList(finishedList: List<Portfolio>) {
        finishedListAdapter.submitList(finishedList)
    }

    override fun showVendorCategoryList(vendorCategoryList: List<VendorCategory>) {
        vendorCategoryListAdapter.submitList(vendorCategoryList)
    }

    override fun showLoadingIndicator(isActive: Boolean) {
        binding.swipeRefresh.isRefreshing = isActive
    }

    override fun showErrorMessageDialog(message: String) {
        binding.layoutMain.isVisible = false
        binding.layoutLoadingError.isVisible = true
        binding.textLoadingError.text = message
    }

    override fun openSearchPage() {
        val action = HomeFragmentDirections.actionHomeFragmentToHomeSearchFragment()
        findNavController().navigate(action)
    }

    override fun openCompanyProfilePage() {
        findNavController().navigate(R.id.action_homeFragment_to_companyProfileFragment)
    }

    override fun openUpcomingEventPage() {
        findNavController().navigate(R.id.action_homeFragment_to_upcomingEventListFragment)
    }

    override fun openFinishedEventPage() {
        findNavController().navigate(R.id.action_homeFragment_to_finishedEventListFragment)
    }

    override fun openProductCategoryPage(category: ProductCategory) {
        val action = HomeFragmentDirections.actionHomeFragmentToProductFragment(category.id)
        findNavController().navigate(action)
    }

    override fun openPortfolioPage(portfolio: Portfolio) {
        val action = HomeFragmentDirections.actionHomeFragmentToPortfolioFragment(portfolio.id)
        findNavController().navigate(action)
    }

    override fun openVendorPage(vendor: Vendor) {
        val action = HomeFragmentDirections.actionHomeFragmentToVendorDetailFragment(
            vendor.id,
            vendor.categoryId
        )
        findNavController().navigate(action)
    }

    private fun setupToolbar(toolbar: MaterialToolbar) {
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment, R.id.vendorFragment, R.id.formFragment)
        )
        toolbar.setupWithNavController(findNavController(), appBarConfiguration)
        NavigationUI.setupWithNavController(toolbar, findNavController(), appBarConfiguration)
        setHasOptionsMenu(true)
        toolbar.title = null
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_search -> openSearchPage()
            }

            return@setOnMenuItemClickListener super.onOptionsItemSelected(item)
        }
    }
}