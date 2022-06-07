package com.feliz.apps.presentation.ui.form.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.feliz.apps.R
import com.feliz.apps.databinding.FragmentFormLoginBinding
import com.feliz.apps.presentation.util.provider.VMProvider
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.snackbar.Snackbar

class FormLoginFragment : Fragment(), FormLoginContract.View {
    private lateinit var binding: FragmentFormLoginBinding
    private val viewModel: FormLoginViewModel by viewModels { VMProvider.formLogin(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFormLoginBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadLoginPage()
        }
        viewModel.loadLoginPage()

        setupToolbar(binding.toolbar)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.stateLoadingObservable.observe(viewLifecycleOwner) {
            showLoadingIndicator(it)
        }
        viewModel.eventOpenFormDetailPage.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                openFormDetailPage(it)
            }
        }
        viewModel.eventShowLoginSuccessMessage.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let { message ->
                showLoginSuccessMessage(message)
            }
        }
        viewModel.eventShowLoginFailedMessage.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let { message ->
                showLoginFailedMessage(message)
            }

        }
    }

    override fun showLoginPage() {
//        findNavController().navigate(R.id.action_formFragment_to_formDetailFragment)
    }

    override fun showErrorMessage(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
    }

    override fun showLoadingIndicator(isActive: Boolean) {
        binding.swipeRefresh.isRefreshing = isActive
    }

    override fun showLoginSuccessMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoginFailedMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun openFormDetailPage(clientId: Long) {
        val action = FormLoginFragmentDirections.actionFormFragmentToFormDetailFragment(clientId)
        findNavController().navigate(action)
    }

    private fun setupToolbar(toolbar: MaterialToolbar) {
        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.homeFragment, R.id.vendorFragment, R.id.formFragment))
        toolbar.setupWithNavController(findNavController(), appBarConfiguration)
        NavigationUI.setupWithNavController(toolbar, findNavController(), appBarConfiguration)
        toolbar.title = null
    }
}