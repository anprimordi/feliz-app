package com.feliz.apps.presentation.ui.form.detail.tab.personaldata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.feliz.apps.databinding.FragmentFormDetailPersonalDataBinding
import com.feliz.apps.domain.model.form.Form
import com.feliz.apps.presentation.ui.form.detail.FormDetailViewModel
import com.feliz.apps.presentation.util.provider.VMProvider
import timber.log.Timber

class FormDetailPersonalDataFragment : Fragment(), FormDetailPersonalDataContract.View {

    private lateinit var binding: FragmentFormDetailPersonalDataBinding
    private val viewModel: FormDetailViewModel by activityViewModels {
        VMProvider.formDetail(
            requireContext()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFormDetailPersonalDataBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Timber.e("abcdef")

        viewModel.formObservable.observe(viewLifecycleOwner) {
            showFormPersonalDataPage(it)
            Timber.e(it.personal.fullname.uppercase())
        }
    }

    override fun showFormPersonalDataPage(form: Form) {
//      client info
        Timber.e(form.personal.fullname)

        binding.root.isVisible = true
        binding.textClientFullname.text = form.personal.fullname
        binding.textClientNote.text = form.personal.description

//      spouse personal info
        binding.textGroomFullname.text = form.personal.groomName
        binding.textBrideFullname.text = form.personal.brideName
        binding.textGroomFatherFullname.text = form.personal.groomFatherName
        binding.textGroomMotherFullname.text = form.personal.groomMotherName
        binding.textBrideFatherFullname.text = form.personal.brideFatherName
        binding.textBrideMotherFullname.text = form.personal.brideMotherName
        binding.textGroomFamilyMemberFullname.text = form.personal.groomFamilyName
        binding.textBrideFamilyMemberFullname.text = form.personal.brideFamilyName

//        wedding reception coordinator
        binding.textFamilyCoordinatorName.text = form.receptionOfficer.familyCoName
        binding.textFamilyCoordinatorPhoneNumber.text =
            form.receptionOfficer.familyCoPhone.formatPhoneToString()
        binding.textUsherCoordinatorName.text = form.receptionOfficer.guestCoName
        binding.textUsherCoordinatorPhoneNumber.text =
            form.receptionOfficer.guestCoPhone.formatPhoneToString()
        binding.textVipCoordinatorName.text = form.receptionOfficer.vipGuestCoName
        binding.textVipCoordinatorPhoneNumber.text =
            form.receptionOfficer.vipGuestCoPhone.formatPhoneToString()
        binding.textPresentCoordinatorName.text = form.receptionOfficer.giftCoName
        binding.textPresentCoordinatorPhoneNumber.text =
            form.receptionOfficer.giftCoPhone.formatPhoneToString()
        binding.textSouvenirCoordinatorName.text = form.receptionOfficer.souvenirCoName
        binding.textSouvenirCoordinatorPhoneNumber.text =
            form.receptionOfficer.souvenirCoPhone.formatPhoneToString()

//        wedding reception staff member
        binding.textUsherStaffName.text = form.receptionOfficer.guestOfficer
        binding.textGuestBookingStaffName.text = form.receptionOfficer.guestBookOfficer
        binding.textSouvenirStaffName.text = form.receptionOfficer.souvenirOfficer

//        wedding ceremony staff
        binding.textQuranReaderName.text = form.marriageOfficer.bookReader
        binding.textBestManName.text = form.marriageOfficer.groomWitness
        binding.textMaidOfHonorName.text = form.marriageOfficer.brideWitness
        binding.textMcName.text = form.marriageOfficer.masterCeremony
        binding.textTausyiahName.text = form.marriageOfficer.tausyiah
        binding.textPrayerName.text = form.marriageOfficer.prayerOfficer
    }

    private fun Long?.formatPhoneToString(): String {
        return if (this != null) {
            val builder = StringBuilder()
            val prefix = "0"
            builder.append(prefix)
            builder.append(this)
            builder.toString()
        } else {
            ""
        }
    }
}