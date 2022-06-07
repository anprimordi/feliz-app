package com.feliz.apps.presentation.ui.form.detail

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.feliz.apps.R
import com.feliz.apps.databinding.FragmentFormDetailBinding
import com.feliz.apps.domain.model.form.Form
import com.feliz.apps.presentation.util.provider.VMProvider
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayoutMediator
import timber.log.Timber

class FormDetailFragment : Fragment(), FormDetailContract.View {

    private lateinit var binding: FragmentFormDetailBinding
    private val viewModel: FormDetailViewModel by activityViewModels {
        VMProvider.formDetail(
            requireContext()
        )
    }
    private var form: Form? = null

    private val args: FormDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val clientId = args.clientId

        binding = FragmentFormDetailBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        setupToolbar(binding.toolbar)

        binding.swipeRefresh.setOnRefreshListener { viewModel.loadFormDetailPage(clientId) }

        viewModel.loadFormDetailPage(clientId)

        viewModel.formObservable.observe(viewLifecycleOwner) {
            showFormDetailPage()
            form = it
        }
        viewModel.stateLoadingObservable.observe(viewLifecycleOwner) {
            showLoadingIndicator(it)
        }
        viewModel.eventShowErrorMessageObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                showErrorMessage(it)
            }
        }
        viewModel.eventLogoutCurrentUserObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                logoutCurrentUser()
            }
        }
        return binding.root
    }

    override fun showFormDetailPage() {
        binding.layoutLoadingError.isVisible = false
//        binding.layoutMain.visibility = View.VISIBLE
        binding.vpFormDetail.isVisible = true
        val formViewPagerAdapter = FormDetailViewPagerAdapter(this)
        binding.vpFormDetail.adapter = formViewPagerAdapter
        TabLayoutMediator(binding.tabs, binding.vpFormDetail) { tab, position ->
            when (position) {
                0 -> tab.text = getString(R.string.title_personal_data)
                1 -> tab.text = getString(R.string.title_vendor_list)
                2 -> tab.text = getString(R.string.title_statement)
            }
        }.attach()
    }

    override fun showLoadingIndicator(isActive: Boolean) {
        binding.swipeRefresh.isRefreshing = isActive
    }

    override fun showErrorMessage(message: String) {
        binding.vpFormDetail.isVisible = false
//        binding.layoutMain.visibility = View.GONE
        binding.layoutLoadingError.visibility = View.VISIBLE
        binding.textLoadingError.text = message
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        Timber.e(message)
    }

    override fun openEditFormPage(clientId: Long) {
        val action =
            FormDetailFragmentDirections.actionFormDetailFragmentToFormEditFragment(clientId)
        findNavController().navigate(action)
    }

    override fun logoutCurrentUser() {
        findNavController().navigate(R.id.action_formDetailFragment_to_formFragment)
    }

    private fun setupToolbar(toolbar: MaterialToolbar) {
        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        toolbar.setupWithNavController(findNavController(), appBarConfiguration)
        NavigationUI.setupWithNavController(toolbar, findNavController(), appBarConfiguration)
        setHasOptionsMenu(true)
        toolbar.title = getString(R.string.title_form)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_edit -> openEditFormPage(form!!.id)
                R.id.menu_logout -> viewModel.triggerEventLogoutCurrentUser()
            }
            return@setOnMenuItemClickListener super.onOptionsItemSelected(it)
        }
    }
}