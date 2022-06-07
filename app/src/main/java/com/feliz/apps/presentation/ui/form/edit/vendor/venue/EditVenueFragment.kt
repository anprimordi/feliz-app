package com.feliz.apps.presentation.ui.form.edit.vendor.venue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.feliz.apps.R
import com.feliz.apps.databinding.DialogEditAddVendorBinding
import com.feliz.apps.databinding.FragmentEditVenueBinding
import com.feliz.apps.presentation.ui.form.edit.FormEditAdapter
import com.feliz.apps.presentation.ui.form.edit.FormEditViewModel
import com.feliz.apps.presentation.util.provider.VMProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import timber.log.Timber

class EditVenueFragment : Fragment(), EditVenueContract.View {
    private lateinit var binding: FragmentEditVenueBinding
    private val viewModel: FormEditViewModel by activityViewModels {
        VMProvider.formEdit(
            requireContext()
        )
    }
    private val adapter = FormEditAdapter { data -> deleteElement(data) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditVenueBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.rvVendor.adapter = adapter
        binding.buttonAddVendor.setOnClickListener { showAddDialog() }

        viewModel.decorationListObservable.observe(viewLifecycleOwner) { list ->
            showDecorationList(
                list
            )
        }
        return binding.root
    }

    override fun showDecorationList(list: ArrayList<Pair<String, String>>) {
        adapter.submitList(list)
    }

    override fun showAddDialog() {
        val builder = MaterialAlertDialogBuilder(requireContext())
        val itemBinding = DialogEditAddVendorBinding.inflate(LayoutInflater.from(requireContext()))

        var pair: Pair<String, String>

        builder.setView(itemBinding.root)

        builder.setTitle(getString(R.string.title_add_vendor))

        builder.setPositiveButton(getString(R.string.action_save)) { dialog, _ ->
            val name = itemBinding.editName.text.toString()
            val desc = itemBinding.editDesc.text.toString()

            if (name.isNotEmpty() && name.isNotBlank() && desc.isNotEmpty() && desc.isNotBlank()) {
                Timber.e("$name $desc")
                pair = Pair(name, desc)
                addElement(pair)
                dialog.dismiss()
            } else {
                if (name.isBlank() || name == "") {
                    itemBinding.editName.requestFocus()
                    itemBinding.editName.error =
                        getString(R.string.message_vendor_name_must_not_empty)
                    return@setPositiveButton
                }
                if (desc.isBlank() || desc == "") {
                    itemBinding.editDesc.requestFocus()
                    itemBinding.editDesc.error =
                        getString(R.string.message_vendor_desc_must_not_empty)
                    return@setPositiveButton
                }
            }
        }

        builder.setNegativeButton(getString(R.string.action_back)) { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }

    override fun addElement(data: Pair<String, String>) {
        viewModel.addElementDecoration(data)
    }

    override fun deleteElement(data: Pair<String, String>) {
        viewModel.deleteElementDecoration(data)
    }
}