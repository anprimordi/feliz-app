package com.feliz.apps.presentation.ui.form.detail.tab.statement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.feliz.apps.R
import com.feliz.apps.databinding.FragmentFormDetailStatementBinding
import com.feliz.apps.domain.model.form.Form
import com.feliz.apps.domain.model.util.DateTimeUtils
import com.feliz.apps.presentation.ui.form.detail.FormDetailViewModel
import com.feliz.apps.presentation.util.provider.VMProvider

class FormDetailStatementFragment : Fragment(), FormDetailStatementContract.View {

    private lateinit var binding: FragmentFormDetailStatementBinding
    private val viewModel: FormDetailViewModel by activityViewModels {
        VMProvider.formDetail(
            requireContext()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFormDetailStatementBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.formObservable.observe(viewLifecycleOwner) {
            showFormStatement(it)
        }
    }

    override fun showFormStatement(form: Form) {

        val date = DateTimeUtils.formatDate(form.dateOfApproval)

        binding.apply {
            textClientName.text = form.personal.fullname
            textFelizRepresentativeName.text = form.representativeName
            textApprovalDate.text = getString(R.string.text_form_detail_approval_date, date)
        }
    }
}