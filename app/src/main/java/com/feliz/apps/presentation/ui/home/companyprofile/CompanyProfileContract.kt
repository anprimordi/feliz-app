package com.feliz.apps.presentation.ui.home.companyprofile

import androidx.lifecycle.LiveData
import com.feliz.apps.domain.model.CompanyProfile
import com.feliz.apps.domain.model.common.Event
import com.google.android.material.appbar.MaterialToolbar

interface CompanyProfileContract {
    interface View {
        val companyProfile: CompanyProfile?
        fun showCompanyProfile(profile: CompanyProfile)
        fun showErrorMessageView(message: String)
        fun showLoadingIndicator(isActive: Boolean)
        fun setupToolbar(toolbar: MaterialToolbar)
        fun openInstagramPage()
        fun openWhatsAppPage(phoneNumber: String)
        fun openYouTubePage(url: String)
        fun openEmailPage(email: String)
//        fun openUrl(url: String)
    }

    interface Presenter {
        val companyProfileObservable: LiveData<CompanyProfile>
        val stateLoadingObservable: LiveData<Boolean>
        val eventLoadProfileErrorObservable: LiveData<Event<String>>
        val eventOpenWhatsappPage: LiveData<Event<String>>
        val eventOpenYouTubePage: LiveData<Event<String>>
        val eventOpenEmailPage: LiveData<Event<String>>

//        val bannerImageObservable: LiveData<String>
//        val visionListObservable: LiveData<List<String>>
//        val missionListObservable: LiveData<List<String>>
//        val teamListObservable: LiveData<List<Team>>

        fun loadCompanyProfile()
        fun triggerEventOpenWhatsappPage()
        fun triggerEventOpenYouTubePage()
        fun triggerEventOpenEmail()
//        fun loadBannerImage()
//        fun loadTeamsList()
//        fun loadVisionList()
//        fun loadMissionList()
    }
}