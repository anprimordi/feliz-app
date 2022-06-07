package com.feliz.apps.presentation.ui.form.detail.tab.vendorlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.feliz.apps.databinding.FragmentFormDetailVendorBinding
import com.feliz.apps.domain.model.form.Form
import com.feliz.apps.presentation.ui.form.detail.FormDetailViewModel
import com.feliz.apps.presentation.util.provider.VMProvider

class FormDetailVendorFragment : Fragment(), FormDetailVendorContract.View {

    private lateinit var binding: FragmentFormDetailVendorBinding
    private val viewModel: FormDetailViewModel by activityViewModels {
        VMProvider.formDetail(
            requireContext()
        )
    }
    private val decorationAdapter = FormDetailVendorListAdapter()
    private val cateringAdapter = FormDetailVendorListAdapter()
    private val makeupAdapter = FormDetailVendorListAdapter()
    private val documentationAdapter = FormDetailVendorListAdapter()
    private val entertainAdapter = FormDetailVendorListAdapter()
    private val mcAdapter = FormDetailVendorListAdapter()
    private val weddingDressAdapter = FormDetailVendorListAdapter()
    private val accessoriesAdapter = FormDetailVendorListAdapter()
    private val staffDressAdapter = FormDetailVendorListAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFormDetailVendorBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.formObservable.observe(viewLifecycleOwner) {
            showFormVendorPage(it)
        }

        binding.rvDecoration.adapter = decorationAdapter
        binding.rvCatering.adapter = cateringAdapter
        binding.rvMakeup.adapter = makeupAdapter
        binding.rvDocumentation.adapter = documentationAdapter
        binding.rvEntertain.adapter = entertainAdapter
        binding.rvMc.adapter = mcAdapter
        binding.rvWeddingDress.adapter = weddingDressAdapter
        binding.rvAccessories.adapter = accessoriesAdapter
        binding.rvStaffDress.adapter = staffDressAdapter
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun showFormVendorPage(form: Form) {
//        vendor
        binding.textVenueName.text = form.vendors.venueName
        decorationAdapter.submitList(form.vendors.decorationList)
        cateringAdapter.submitList(form.vendors.cateringList)
        makeupAdapter.submitList(form.vendors.makeupList)
        documentationAdapter.submitList(form.vendors.documentationList)
        entertainAdapter.submitList(form.vendors.entertainList)
        mcAdapter.submitList(form.vendors.mcShowList)
        weddingDressAdapter.submitList(form.vendors.weddingDressList)
        accessoriesAdapter.submitList(form.vendors.accessoriesList)
        staffDressAdapter.submitList(form.vendors.staffDressList)

//        support vendor
        binding.textLightingPlan.text = form.supportVendor.lighting!!.name
        binding.textGeneratorPlan.text = form.supportVendor.generator!!.name
        binding.textSoundSystemPlan.text = form.supportVendor.soundSystem!!.name
        binding.textMultimediaPlan.text = form.supportVendor.multimedia!!.name
        binding.textWeddingCarPlan.text = form.supportVendor.weddingCar
        binding.textUsherServicePlan.text = form.supportVendor.usher!!.name
        binding.textInvitationTypingPlan.text = form.supportVendor.invitationAmount.toString()
        binding.textCourierPlan.text = form.supportVendor.courier!!.name
        binding.textOtherNote.text = form.supportVendor.etc
    }
}