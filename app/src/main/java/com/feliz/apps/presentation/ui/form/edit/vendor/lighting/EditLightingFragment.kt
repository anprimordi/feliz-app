package com.feliz.apps.presentation.ui.form.edit.vendor.lighting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.feliz.apps.R
import com.feliz.apps.databinding.FragmentEditLightingBinding
import com.feliz.apps.presentation.ui.form.edit.FormEditViewModel
import com.feliz.apps.presentation.util.provider.VMProvider
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class EditLightingFragment : Fragment() {
    private lateinit var binding: FragmentEditLightingBinding
    private val viewModel: FormEditViewModel by activityViewModels {
        VMProvider.formEdit(
            requireContext()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditLightingBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.lightingListObservable.observe(viewLifecycleOwner) {
            binding.editLightingEffectPlan.setDropdownMenu(it)
        }
        viewModel.generatorListObservable.observe(viewLifecycleOwner) {
            binding.editGeneratorPlan.setDropdownMenu(it)
        }
        viewModel.multimediaListObservable.observe(viewLifecycleOwner) {
            binding.editMultimediaPlan.setDropdownMenu(it)
        }
        viewModel.soundSystemListObservable.observe(viewLifecycleOwner) {
            binding.editSoundSystemPlan.setDropdownMenu(it)
        }
    }

    private fun MaterialAutoCompleteTextView.setDropdownMenu(list: List<String>) {
        val adapter = ArrayAdapter(requireContext(), R.layout.item_list, list)
        this.setAdapter(adapter)
        this.threshold = 1000
    }
}