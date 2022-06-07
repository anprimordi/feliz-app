package com.feliz.apps.presentation.ui.home.companyprofile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.feliz.apps.R
import com.feliz.apps.databinding.FragmentCompanyProfileBinding
import com.feliz.apps.domain.model.CompanyProfile
import com.feliz.apps.presentation.util.provider.VMProvider
import com.google.android.material.appbar.MaterialToolbar

class CompanyProfileFragment : Fragment(), CompanyProfileContract.View {

    private lateinit var binding: FragmentCompanyProfileBinding
    private val viewModel: CompanyProfileViewModel by viewModels {
        VMProvider.companyProfile(
            requireContext()
        )
    }

    private val teamAdapter = CompanyProfileTeamListAdapter()

    override var companyProfile: CompanyProfile? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompanyProfileBinding.inflate(inflater)

        val teamGridLayoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.view = this
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadCompanyProfile()
        }

        binding.rvTeams.layoutManager = teamGridLayoutManager
        binding.rvTeams.adapter = teamAdapter

        viewModel.loadCompanyProfile()

        viewModel.companyProfileObservable.observe(viewLifecycleOwner) {
            companyProfile = it
            showCompanyProfile(it)
        }

        viewModel.eventLoadProfileErrorObservable.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                showErrorMessageView(it)
            }
        }

        viewModel.eventOpenWhatsappPage.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                openWhatsAppPage(it)
            }
        }

        viewModel.eventOpenYouTubePage.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                openYouTubePage(it)
            }
        }

        viewModel.eventOpenEmailPage.observe(viewLifecycleOwner) { event ->
            event.getAvailableEvent()?.let {
                openEmailPage(it)
            }
        }

        viewModel.stateLoadingObservable.observe(viewLifecycleOwner) {
            showLoadingIndicator(it)
        }

        setupToolbar(binding.toolbar)
        return binding.root
    }

    override fun showCompanyProfile(profile: CompanyProfile) {
        val visionList = arrayListOf<String>()
        val missionList = arrayListOf<String>()

        profile.visions.forEach {
            visionList.add(it.description)
        }

        profile.missions.forEach {
            missionList.add(it.description)
        }

        binding.layoutLoadingError.visibility = View.GONE
        binding.layoutCompanyProfile.visibility = View.VISIBLE
        binding.textCompanyName.text = profile.name
        binding.textCompanyDescription.text = profile.description
        binding.textVisionBody.text = visionList.joinToString(separator = "\n")
        binding.textMissionBody.text = missionList.joinToString(separator = "\n")
        teamAdapter.submitList(profile.teams)
    }

    override fun showErrorMessageView(message: String) {
        binding.layoutCompanyProfile.isVisible = false
        binding.layoutLoadingError.isVisible = true
        binding.textLoadingError.text = message
    }

    override fun showLoadingIndicator(isActive: Boolean) {
        binding.swipeRefresh.isRefreshing = isActive
    }

    override fun openInstagramPage() {
        val url = companyProfile?.instagram
        if (url != null) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }

    override fun openWhatsAppPage(phoneNumber: String) {
        try {
            val message = "Halo admin feliz apps"
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT, message)
            intent.putExtra("jid", "$phoneNumber@s.whatsapp.net")
            intent.type = "text/plain"
            intent.setPackage("com.whatsapp")
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Error/n${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun openYouTubePage(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    override fun openEmailPage(email: String) {
        val intent = Intent(Intent.ACTION_SEND)
        val subject = getString(R.string.text_company_email_subject)
        val message = getString(R.string.text_company_email_message)

        intent.putExtra(Intent.EXTRA_EMAIL, listOf(email).toTypedArray())
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, message)
        intent.type = "message/rfc822"
        startActivity(Intent.createChooser(intent, "Choose email client"))
    }

/*    override fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }*/

    override fun setupToolbar(toolbar: MaterialToolbar) {
        val appBarConfiguration = AppBarConfiguration(findNavController().graph)
        toolbar.setupWithNavController(findNavController(), appBarConfiguration)
        NavigationUI.setupWithNavController(toolbar, findNavController(), appBarConfiguration)
        toolbar.title = getString(R.string.title_company_profile)
    }
}