package com.feliz.apps.presentation.ui.form.edit.vendor.car

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.feliz.apps.R
import com.feliz.apps.databinding.FragmentEditCarBinding
import com.feliz.apps.presentation.ui.form.edit.FormEditViewModel
import com.feliz.apps.presentation.util.extensions.getRandomString
import com.feliz.apps.presentation.util.provider.VMProvider
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class EditCarFragment : Fragment() {
    private lateinit var binding: FragmentEditCarBinding
    private val viewModel: FormEditViewModel by activityViewModels {
        VMProvider.formEdit(
            requireContext()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditCarBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val items = listOf(getRandomString(), getRandomString(), getRandomString())
        viewModel.usherVendorListObservable.observe(viewLifecycleOwner) {
            binding.editUsherServicePlan.setDropdownMenu(it)
        }
        viewModel.courierListObservable.observe(viewLifecycleOwner) {
            binding.editCourierAmount.setDropdownMenu(it)
        }

        return binding.root
    }

    private fun MaterialAutoCompleteTextView.setDropdownMenu(list: List<String>) {
        val adapter = ArrayAdapter(requireContext(), R.layout.item_list, list)
        this.setAdapter(adapter)
        this.threshold = 1000
    }
}