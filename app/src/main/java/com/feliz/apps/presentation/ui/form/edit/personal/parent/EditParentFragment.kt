package com.feliz.apps.presentation.ui.form.edit.personal.parent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.feliz.apps.databinding.FragmentEditParentBinding
import com.feliz.apps.presentation.ui.form.edit.FormEditViewModel
import com.feliz.apps.presentation.util.provider.VMProvider

class EditParentFragment : Fragment() {

    private lateinit var binding: FragmentEditParentBinding
    private val viewModel: FormEditViewModel by activityViewModels {
        VMProvider.formEdit(
            requireContext()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditParentBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }
}