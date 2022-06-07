package com.feliz.apps.presentation.ui.form.edit.staff.ceremony

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.feliz.apps.databinding.FragmentEditCeremonyBinding
import com.feliz.apps.presentation.ui.form.edit.FormEditViewModel
import com.feliz.apps.presentation.util.provider.VMProvider

class EditCeremonyFragment : Fragment() {
    private lateinit var binding: FragmentEditCeremonyBinding
    private val viewModel: FormEditViewModel by activityViewModels {
        VMProvider.formEdit(
            requireContext()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditCeremonyBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        // Inflate the layout for this fragment
        return binding.root
    }
}