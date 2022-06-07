package com.feliz.apps.presentation.ui.form.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.feliz.apps.domain.model.common.Event
import com.feliz.apps.domain.model.common.Result
import com.feliz.apps.domain.model.form.Form
import com.google.android.material.appbar.MaterialToolbar

interface FormEditContract {
    interface View {
        fun showFormEditPage()
        fun showLoadingIndicator(isActive: Boolean)
        fun showLoadPageFailed(message: String)
        fun showErrorMessage(message: String)
        fun showCurrentPage(position: Int)
        fun showSubmitDialog()
        fun showCancelDialog()

        //        fun showNavigationButtons(position: Int)
        fun openFormDetailPage(clientId: Long)
        fun setupToolbar(toolbar: MaterialToolbar)

        fun onSubmitFormFinished(result: Result<Unit>)
    }

    interface Presenter {
        val formObservable: LiveData<Form>
        val positionObservable: LiveData<Int>

        //        client
        val clientFullnameObservable: MutableLiveData<String>
        val clientDescriptionObservable: MutableLiveData<String>
        val groomFullnameObservable: MutableLiveData<String>
        val brideFullnameObservable: MutableLiveData<String>

        //        parent
        val groomFatherNameObservable: MutableLiveData<String>
        val groomMotherNameObservable: MutableLiveData<String>
        val brideFatherNameObservable: MutableLiveData<String>
        val brideMotherNameObservable: MutableLiveData<String>

        //        family
        val groomFamilyNameObservable: MutableLiveData<String>
        val brideFamilyNameObservable: MutableLiveData<String>

        //        family co
        val familyCoNameObservable: MutableLiveData<String>
        val familyCoPhoneObservable: MutableLiveData<String>

        //        usher co
        val usherCoNameObservable: MutableLiveData<String>
        val usherCoPhoneObservable: MutableLiveData<String>

        //        vip co
        val vipCoNameObservable: MutableLiveData<String>
        val vipCoPhoneObservable: MutableLiveData<String>

        //        gift co
        val giftCoNameObservable: MutableLiveData<String>
        val giftCoPhoneObservable: MutableLiveData<String>

        //        souvenir co
        val souvenirCoNameObservable: MutableLiveData<String>
        val souvenirCoPhoneObservable: MutableLiveData<String>

        //        souvenir staff
        val souvenirStaffNameObservable: MutableLiveData<String>

        //        usher staff
        val usherStaffNameObservable: MutableLiveData<String>

        //        guest booking staff
        val guestBookingStaffNameObservable: MutableLiveData<String>

        //        quran reader
        val quranReaderObservable: MutableLiveData<String>

        //        best man
        val groomWitnessObservable: MutableLiveData<String>

        //        maid of honor
        val brideWitnessObservable: MutableLiveData<String>

        //        master of ceremony
        val mcObservable: MutableLiveData<String>

        //        tausyiah
        val tausyiahObservable: MutableLiveData<String>

        //        prayer
        val prayerObservable: MutableLiveData<String>

//        vendors

        //        venue
        val venueObservable: MutableLiveData<String>

        //        decoration
//        val decorationPositionObservable: MutableLiveData<Int>
        val decorationListObservable: MutableLiveData<ArrayList<Pair<String, String>>>

        //        catering
//        val cateringPositionObservable: MutableLiveData<Int>
        val cateringListObservable: MutableLiveData<ArrayList<Pair<String, String>>>

        //        makeup
//        val makeUpPositionObservable: MutableLiveData<Int>
        val makeUpListObservable: MutableLiveData<ArrayList<Pair<String, String>>>

        //        documentation
//        val documentationPositionObservable: MutableLiveData<Int>
        val documentationListObservable: MutableLiveData<ArrayList<Pair<String, String>>>

        //        entertain
//        val entertainPositionObservable: MutableLiveData<Int>
        val entertainListObservable: MutableLiveData<ArrayList<Pair<String, String>>>

        //        mc vendor
//        val mcVendorPositionObservable: MutableLiveData<Int>
        val mcVendorListObservable: MutableLiveData<ArrayList<Pair<String, String>>>

        //        wedding dress
//        val weddingDressPositionObservable: MutableLiveData<Int>
        val weddingDressListObservable: MutableLiveData<ArrayList<Pair<String, String>>>

        //        wedding accessories
//        val accessoriesPositionObservable: MutableLiveData<Int>
        val accessoriesListObservable: MutableLiveData<ArrayList<Pair<String, String>>>

        //        staff and family dress
//        val staffDressPositionObservable: MutableLiveData<Int>
        val staffDressListObservable: MutableLiveData<ArrayList<Pair<String, String>>>

//        support vendor

        val lightingObservable: MutableLiveData<String>
        val lightingListObservable: MutableLiveData<List<String>>
        val generatorObservable: MutableLiveData<String>
        val generatorListObservable: MutableLiveData<List<String>>
        val soundSystemObservable: MutableLiveData<String>
        val soundSystemListObservable: MutableLiveData<List<String>>
        val multimediaObservable: MutableLiveData<String>
        val multimediaListObservable: MutableLiveData<List<String>>
        val carObservable: MutableLiveData<String>

        val usherVendorObservable: MutableLiveData<String>
        val usherVendorListObservable: MutableLiveData<List<String>>
        val invitationAmountObservable: MutableLiveData<String>

        val courierObservable: MutableLiveData<String>
        val courierListObservable: MutableLiveData<List<String>>
        val etcObservable: MutableLiveData<String>

        val stateLoadingObservable: LiveData<Boolean>
        val stateShowCurrentPageObservable: LiveData<Event<Int>>
        val eventShowLoadPageFailedObservable: LiveData<Event<String>>
        val eventShowErrorMessageObservable: LiveData<Event<String>>
        val eventShowConfirmationDialog: LiveData<Event<Unit>>
        val eventSubmitForm: LiveData<Event<Result<Unit>>>

        fun loadFormDetailPage(clientId: Long)

        fun triggerEventShowNextPage()
        fun triggerEventShowPrevPage()

        fun submitForm()

        fun addElementAccessories(data: Pair<String, String>)
        fun addElementCatering(data: Pair<String, String>)
        fun addElementDocumentation(data: Pair<String, String>)
        fun addElementEntertain(data: Pair<String, String>)
        fun addElementMakeUp(data: Pair<String, String>)
        fun addElementMc(data: Pair<String, String>)
        fun addElementStaffDress(data: Pair<String, String>)
        fun addElementDecoration(data: Pair<String, String>)
        fun addElementWeddingDress(data: Pair<String, String>)

        fun deleteElementAccessories(data: Pair<String, String>)
        fun deleteElementCatering(data: Pair<String, String>)
        fun deleteElementDocumentation(data: Pair<String, String>)
        fun deleteElementEntertain(data: Pair<String, String>)
        fun deleteElementMakeUp(data: Pair<String, String>)
        fun deleteElementMc(data: Pair<String, String>)
        fun deleteElementStaffDress(data: Pair<String, String>)
        fun deleteElementDecoration(data: Pair<String, String>)
        fun deleteElementWeddingDress(data: Pair<String, String>)
    }
}