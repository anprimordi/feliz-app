package com.feliz.apps.presentation.ui.form.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.feliz.apps.R
import com.feliz.apps.databinding.FragmentFormEditBinding
import com.feliz.apps.domain.model.common.Error
import com.feliz.apps.domain.model.common.Loading
import com.feliz.apps.domain.model.common.Result
import com.feliz.apps.domain.model.common.Success
import com.feliz.apps.presentation.util.provider.VMProvider
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import timber.log.Timber

class FormEditFragment : Fragment(), FormEditContract.View {

    private lateinit var binding: FragmentFormEditBinding
    private val viewModel: FormEditViewModel by activityViewModels {
        VMProvider.formEdit(
            requireContext()
        )
    }
    private val args: FormEditFragmentArgs by navArgs()
    private val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            val totalCount = binding.vpEdit.adapter?.itemCount ?: 0
            binding.progressIndicator.progress = ((position + 1) * 100) / totalCount
            binding.buttonNext.text =
                if (position < totalCount - 1) getString(R.string.action_next) else getString(R.string.action_save)
            binding.buttonPrevious.isVisible = position > 0
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val clientId = args.clientId

        binding = FragmentFormEditBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.swipeRefresh.setOnRefreshListener { viewModel.loadFormDetailPage(clientId) }
        binding.vpEdit.isUserInputEnabled = false
        setupToolbar(binding.toolbar)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            showCancelDialog()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val clientId = args.clientId

        showFormEditPage()

        viewModel.loadFormDetailPage(clientId)
        viewModel.positionObservable.observe(viewLifecycleOwner) {
            showCurrentPage(it)
            Timber.e("$it")
//            showNavigationButtons(it)
        }
        viewModel.stateLoadingObservable.observe(viewLifecycleOwner) {
            showLoadingIndicator(it)
        }
        viewModel.eventShowLoadPageFailedObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                showLoadPageFailed(it)
            }
        }
        viewModel.eventShowErrorMessageObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                showErrorMessage(it)
            }
        }
        viewModel.eventShowConfirmationDialog.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                showSubmitDialog()
            }
        }
        viewModel.eventSubmitForm.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                onSubmitFormFinished(it)
            }
        }
    }

    override fun showFormEditPage() {
        binding.layoutLoadingError.isVisible = false
        binding.vpEdit.isVisible = true
        binding.appbarBottom.isVisible = true
        binding.buttonPrevious.isVisible = false
        binding.vpEdit.adapter = FormEditViewPagerAdapter(this)
        binding.vpEdit.registerOnPageChangeCallback(pageChangeCallback)
        binding.vpEdit.currentItem = 0
    }

    override fun showLoadingIndicator(isActive: Boolean) {
        binding.swipeRefresh.isRefreshing = isActive
    }

    override fun showLoadPageFailed(message: String) {
        binding.vpEdit.isVisible = false
        binding.layoutLoadingError.isVisible = true
        binding.textLoadingError.text = message
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun showCurrentPage(position: Int) {
        binding.vpEdit.currentItem = position
    }

    override fun showSubmitDialog() {
        MaterialAlertDialogBuilder(requireContext()).apply {
            setMessage(getString(R.string.title_form_edit_confirm_changes))
            setPositiveButton(getString(R.string.action_save)) { dialog, _ ->
                dialog.dismiss()
                viewModel.submitForm()
            }
            setNegativeButton(getString(R.string.action_back)) { dialog, _ ->
                dialog.dismiss()
            }
        }.show()
    }

    override fun showCancelDialog() {
        MaterialAlertDialogBuilder(requireContext()).apply {
            setMessage(getString(R.string.title_form_edit_back_pressed_dialog))
            setPositiveButton(getString(R.string.action_yes)) { dialog, _ ->
                dialog.dismiss()
                findNavController().navigateUp()
            }
            setNegativeButton(getString(R.string.action_back)) { dialog, _ ->
                dialog.dismiss()
            }
        }.show()
    }

    override fun openFormDetailPage(clientId: Long) {
        val action =
            FormEditFragmentDirections.actionFormEditFragmentToFormDetailFragment(clientId)
        findNavController().navigate(action)
    }

    override fun setupToolbar(toolbar: MaterialToolbar) {
        toolbar.title = getString(R.string.title_edit_form)
        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.menu_save -> showSubmitDialog()
            }
            return@setOnMenuItemClickListener super.onOptionsItemSelected(item)
        }
    }

    override fun onSubmitFormFinished(result: Result<Unit>) {
        when (result) {
            is Loading -> {
            }
            is Success -> {
                val formId = args.clientId
                Toast.makeText(requireContext(), "Update Berhasil", Toast.LENGTH_SHORT)
                    .show()
                openFormDetailPage(formId)
            }
            is Error -> {
                Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        binding.vpEdit.unregisterOnPageChangeCallback(pageChangeCallback)
        super.onDestroyView()
    }
}