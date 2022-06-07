package com.feliz.apps.presentation.ui.vendor.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.feliz.apps.R
import com.feliz.apps.databinding.FragmentVendorDetailBinding
import com.feliz.apps.domain.model.Vendor
import com.feliz.apps.presentation.util.provider.VMProvider
import com.zhpan.bannerview.constants.PageStyle
import com.zhpan.indicator.enums.IndicatorSlideMode
import timber.log.Timber

class VendorDetailFragment : Fragment(), VendorDetailContract.View {

    private lateinit var binding: FragmentVendorDetailBinding
    private val viewModel: VendorDetailViewModel by viewModels {
        VMProvider.vendorDetail(
            requireContext()
        )
    }
    private val args: VendorDetailFragmentArgs by navArgs()

    private lateinit var vendorDetailListAdapter: VendorDetailListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val vendorId = args.vendorId
        val categoryId = args.vendorCategoryId

        binding = FragmentVendorDetailBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadDetailVendor(vendorId)
            viewModel.loadMoreVendor(categoryId)
        }

        binding.buttonNavigateUp.setOnClickListener { findNavController().navigateUp() }

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val vendorId = args.vendorId
        val categoryId = args.vendorCategoryId

        viewModel.loadDetailVendor(vendorId)
        viewModel.loadMoreVendor(categoryId)

        viewModel.vendorObservable.observe(viewLifecycleOwner) {
            showVendorDetailPage(it)
            vendorDetailListAdapter =
                VendorDetailListAdapter(categoryName = it.category.name) { vendor ->
                    openMoreVendorPage(vendor)
                }
            binding.rvMoreVendor.adapter = vendorDetailListAdapter

            viewModel.moreVendorObservable.observe(viewLifecycleOwner) {
                showMoreVendorList(it)
            }
        }

        viewModel.stateLoadingObservable.observe(viewLifecycleOwner) {
            showLoadingIndicator(it)
        }
        viewModel.eventLoadingErrorObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                showErrorMessage(it)
            }
        }
        viewModel.eventOpenMoreVendorPageObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                openMoreVendorPage(it)
            }
        }
        viewModel.eventOpenDirectionObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                openDirection(it)
            }
        }

    }

    override fun showVendorDetailPage(vendor: Vendor) {
        binding.layoutLoadingError.isVisible = false
        binding.layoutProduct.isVisible = true
        binding.appbarBottom.isVisible = true
        binding.buttonNavigateUp.isVisible = true
        showVendorDetailBanner(vendor)
        binding.textCategory.text = vendor.category.name
        binding.textName.text = vendor.name
        binding.textAddress.text = vendor.address

        if (vendor.category.name == "Venue") {
            binding.textLocationSpaceTitle.isVisible = true
            binding.textLocationSpace.isVisible = true
            binding.textRoomTitle.isVisible = true
            binding.textRoom.isVisible = true
        }

        binding.textLocationSpace.text =
            getString(R.string.text_location_space, vendor.locationSpace)
        binding.textCapacity.text = getString(R.string.text_capacity, vendor.capacity)
        binding.textFacility.text = vendor.facility
        binding.textRoom.text = getString(R.string.text_room, vendor.room)
        binding.textMoreVendor.text =
            getString(R.string.text_vendor_detail_more_vendor, vendor.category.name)
        binding.textMoreProductCategorySubtitle.text =
            getString(R.string.text_more_vendor_subtitle, vendor.category.name)
    }

    override fun showMoreVendorList(vendorList: List<Vendor>) {
        val list = vendorList.filter { it.id != args.vendorId }
        vendorDetailListAdapter.submitList(list)
    }

    override fun showVendorDetailBanner(vendor: Vendor) {
        val list = arrayListOf<String>()
        vendor.photoUrls.forEach {
            list.add(it.url)
        }

        binding.bvpThumbnails.apply {
            setIndicatorSlideMode(IndicatorSlideMode.WORM)
            setIndicatorSliderColor(
                ContextCompat.getColor(requireContext(), R.color.grey),
                ContextCompat.getColor(requireContext(), R.color.orange)
            )
            setIndicatorSliderRadius(
                resources.getDimensionPixelOffset(R.dimen.banner_slider_normal),
                resources.getDimensionPixelOffset(R.dimen.banner_slider_checked)
            )
            setLifecycleRegistry(lifecycle)
            setAdapter(VendorDetailBannerAdapter())
//            setPageMargin(resources.getDimensionPixelOffset(R.dimen.banner_slider_page_margin))
//            setRevealWidth(resources.getDimensionPixelOffset(R.dimen.banner_reveal_width))
            setPageStyle(PageStyle.MULTI_PAGE_SCALE)

            create(list)
        }
    }

    override fun showLoadingIndicator(isActive: Boolean) {
        binding.swipeRefresh.isRefreshing = isActive
    }

    override fun showErrorMessage(message: String) {
        binding.layoutProduct.visibility = View.GONE
        binding.appbarBottom.visibility = View.GONE
        binding.buttonNavigateUp.visibility = View.GONE
        binding.layoutLoadingError.visibility = View.VISIBLE
        binding.textLoadingError.text = message
    }

    override fun openMoreVendorPage(vendor: Vendor) {
        val action = VendorDetailFragmentDirections.actionVendorDetailFragmentSelf(
            vendor.id,
            vendor.categoryId
        )
        findNavController().navigate(action)
    }

    override fun openDirection(location: Pair<Double, Double>) {
        val latitude = location.first
        val longitude = location.second
        val gmmIntentUri = Uri.parse("geo:$latitude,$longitude")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        Timber.e("$latitude, $longitude")
        mapIntent.setPackage("com.google.android.apps.maps")
        mapIntent.resolveActivity(requireActivity().packageManager)?.let {
            startActivity(mapIntent)
        }
    }
}