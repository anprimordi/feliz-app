package com.feliz.apps.presentation.ui.form.edit

import androidx.lifecycle.*
import com.feliz.apps.domain.model.common.Event
import com.feliz.apps.domain.model.common.Result
import com.feliz.apps.domain.model.common.Success
import com.feliz.apps.domain.model.form.Form
import com.feliz.apps.domain.model.form.personaldata.Personal
import com.feliz.apps.domain.model.form.supportvendor.*
import com.feliz.apps.domain.model.form.vendors.MarriageOfficer
import com.feliz.apps.domain.model.form.vendors.ReceptionOfficer
import com.feliz.apps.domain.model.form.vendors.Vendors
import com.feliz.apps.domain.source.FormDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class FormEditViewModel private constructor(
    private val formDataSource: FormDataSource
) : ViewModel(), FormEditContract.Presenter {
    private val _formObservable = MutableLiveData<Form>()
    private val _positionObservable = MutableLiveData<Int>()
    private val _stateShowCurrentPageObservable = MutableLiveData<Event<Int>>()

    private val _stateLoadingObservable = MutableLiveData<Boolean>()
    private val _stateEditCompletedObservable = MutableLiveData<Boolean>()
    private val _eventShowErrorMessageObservable = MutableLiveData<Event<String>>()
    private val _eventSubmitForm = MutableLiveData<Event<Result<Unit>>>()
    private val _eventShowConfirmationDialog = MutableLiveData<Event<Unit>>()
    private val _eventShowLoadPageFailedObservable = MutableLiveData<Event<String>>()
    override val formObservable: LiveData<Form>
        get() = _formObservable
    override val positionObservable: LiveData<Int>
        get() = _positionObservable
    override val stateShowCurrentPageObservable: LiveData<Event<Int>>
        get() = _stateShowCurrentPageObservable
    override val eventShowLoadPageFailedObservable: LiveData<Event<String>>
        get() = _eventShowLoadPageFailedObservable
    override val clientFullnameObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val clientDescriptionObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val groomFullnameObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val brideFullnameObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val groomFatherNameObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val groomMotherNameObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val brideFatherNameObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val brideMotherNameObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val groomFamilyNameObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val brideFamilyNameObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val familyCoNameObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val familyCoPhoneObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val usherCoNameObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val usherCoPhoneObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val vipCoNameObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val vipCoPhoneObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val giftCoNameObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val giftCoPhoneObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val souvenirCoNameObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val souvenirCoPhoneObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val souvenirStaffNameObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val usherStaffNameObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val guestBookingStaffNameObservable: MutableLiveData<String> =
        MutableLiveData<String>()
    override val quranReaderObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val groomWitnessObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val brideWitnessObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val mcObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val tausyiahObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val prayerObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val venueObservable: MutableLiveData<String> = MutableLiveData<String>()

    //    override val decorationPositionObservable: MutableLiveData<Int> = MutableLiveData<Int>()
    override val decorationListObservable: MutableLiveData<ArrayList<Pair<String, String>>> =
        MutableLiveData<ArrayList<Pair<String, String>>>()

    //    override val cateringPositionObservable: MutableLiveData<Int> = MutableLiveData<Int>()
    override val cateringListObservable: MutableLiveData<ArrayList<Pair<String, String>>> =
        MutableLiveData<ArrayList<Pair<String, String>>>()

    //    override val makeUpPositionObservable: MutableLiveData<Int> = MutableLiveData<Int>()
    override val makeUpListObservable: MutableLiveData<ArrayList<Pair<String, String>>> =
        MutableLiveData<ArrayList<Pair<String, String>>>()

    //    override val documentationPositionObservable: MutableLiveData<Int> = MutableLiveData<Int>()
    override val documentationListObservable: MutableLiveData<ArrayList<Pair<String, String>>> =
        MutableLiveData<ArrayList<Pair<String, String>>>()

    //    override val entertainPositionObservable: MutableLiveData<Int> = MutableLiveData<Int>()
    override val entertainListObservable: MutableLiveData<ArrayList<Pair<String, String>>> =
        MutableLiveData<ArrayList<Pair<String, String>>>()

    //    override val mcVendorPositionObservable: MutableLiveData<Int> = MutableLiveData<Int>()
    override val mcVendorListObservable: MutableLiveData<ArrayList<Pair<String, String>>> =
        MutableLiveData<ArrayList<Pair<String, String>>>()

    //    override val weddingDressPositionObservable: MutableLiveData<Int> = MutableLiveData<Int>()
    override val weddingDressListObservable: MutableLiveData<ArrayList<Pair<String, String>>> =
        MutableLiveData<ArrayList<Pair<String, String>>>()

    //    override val accessoriesPositionObservable: MutableLiveData<Int> = MutableLiveData<Int>()
    override val accessoriesListObservable: MutableLiveData<ArrayList<Pair<String, String>>> =
        MutableLiveData<ArrayList<Pair<String, String>>>()

    //    override val staffDressPositionObservable: MutableLiveData<Int> = MutableLiveData<Int>()
    override val staffDressListObservable: MutableLiveData<ArrayList<Pair<String, String>>> =
        MutableLiveData<ArrayList<Pair<String, String>>>()
    override val lightingObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val lightingListObservable: MutableLiveData<List<String>> =
        MutableLiveData<List<String>>()
    override val generatorObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val generatorListObservable: MutableLiveData<List<String>> =
        MutableLiveData<List<String>>()
    override val soundSystemObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val soundSystemListObservable: MutableLiveData<List<String>> =
        MutableLiveData<List<String>>()
    override val multimediaObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val multimediaListObservable: MutableLiveData<List<String>> =
        MutableLiveData<List<String>>()
    override val carObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val usherVendorObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val usherVendorListObservable: MutableLiveData<List<String>> =
        MutableLiveData<List<String>>()
    override val invitationAmountObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val courierObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val courierListObservable: MutableLiveData<List<String>> =
        MutableLiveData<List<String>>()
    override val etcObservable: MutableLiveData<String> = MutableLiveData<String>()
    override val stateLoadingObservable: LiveData<Boolean>
        get() = _stateLoadingObservable
    override val eventShowErrorMessageObservable: LiveData<Event<String>>
        get() = _eventShowErrorMessageObservable
    override val eventShowConfirmationDialog: LiveData<Event<Unit>>
        get() = _eventShowConfirmationDialog
    override val eventSubmitForm: LiveData<Event<Result<Unit>>>
        get() = _eventSubmitForm

    init {
        _stateShowCurrentPageObservable.value = Event(0)
    }

    private var form: Form? = null
    private var currentPosition = 0
    private val totalFragment = 20

    private var token: String? = null
    private var clientId: Long = -1

    private val lightings = arrayListOf<Lighting>()
    private val generators = arrayListOf<Generator>()
    private val soundSystems = arrayListOf<SoundSystem>()
    private val multimedias = arrayListOf<Multimedia>()
    private val ushers = arrayListOf<Usher>()
    private val couriers = arrayListOf<Courier>()

    override fun loadFormDetailPage(clientId: Long) {
        currentPosition = 0
        this.clientId = clientId
        _positionObservable.value = currentPosition
//        val form = Form.dummy()
        viewModelScope.launch {
            _stateLoadingObservable.postValue(true)
            _stateEditCompletedObservable.postValue(false)
            val getToken = formDataSource.getClientToken()

            if (getToken is Success) {
                token = getToken.data
                if (token != null) {
                    val result =
                        formDataSource.getFormDetailById(token = token!!, formId = clientId)
                    if (result is Success) {
                        val data = result.data
                        _formObservable.postValue(data!!)
                        form = data

                        val lightingSnapshot = formDataSource.getLightingList(token!!)

                        if (lightingSnapshot is Success) {
                            val lightingList = lightingSnapshot.data
                            lightings.clear()
                            lightings.addAll(lightingList)
                            val list = arrayListOf<String>()

                            lightingList.forEach { list.add(it.name) }
                            lightingListObservable.postValue(list)
                        }

                        val soundSystemSnapshot = formDataSource.getSoundSystemList(token!!)

                        if (soundSystemSnapshot is Success) {
                            val soundSystemList = soundSystemSnapshot.data
                            val list = arrayListOf<String>()

                            soundSystems.clear()
                            soundSystems.addAll(soundSystemList)

                            soundSystemList.forEach { list.add(it.name) }
                            soundSystemListObservable.postValue(soundSystemList.map { it.name })
                        }

                        val generatorSnapshot = formDataSource.getGeneratorList(token!!)

                        if (generatorSnapshot is Success) {
                            val generatorList = generatorSnapshot.data
                            val list = arrayListOf<String>()

                            generators.clear()
                            generators.addAll(generatorList)

                            generatorList.forEach { list.add(it.name) }
                            generatorListObservable.postValue(list)
                        }

                        val multimediaSnapshot = formDataSource.getMultimediaList(token!!)

                        if (multimediaSnapshot is Success) {
                            val multimediaList = multimediaSnapshot.data
                            val list = arrayListOf<String>()

                            multimedias.clear()
                            multimedias.addAll(multimediaList)

                            multimediaList.forEach { list.add(it.name) }
                            multimediaListObservable.postValue(list)
                        }

                        val usherSnapshot = formDataSource.getUsherList(token!!)

                        if (usherSnapshot is Success) {
                            val usherList = usherSnapshot.data
                            val list = arrayListOf<String>()

                            ushers.clear()
                            ushers.addAll(usherList)

                            usherList.forEach { list.add(it.name) }
                            usherVendorListObservable.postValue(list)
                        }

                        val courierSnapshot = formDataSource.getCourierList(token!!)

                        if (courierSnapshot is Success) {
                            val courierList = courierSnapshot.data
                            val list = arrayListOf<String>()

                            couriers.clear()
                            couriers.addAll(courierList)

                            courierList.forEach { list.add(it.name) }
                            courierListObservable.postValue(list)
                        }

                        clientFullnameObservable.postValue(data.personal.fullname)
                        clientDescriptionObservable.postValue(data.personal.description)
                        groomFullnameObservable.postValue(data.personal.groomName)
                        brideFullnameObservable.postValue(data.personal.brideName)
                        groomFatherNameObservable.postValue(data.personal.groomFatherName)
                        groomMotherNameObservable.postValue(data.personal.groomMotherName)
                        brideFatherNameObservable.postValue(data.personal.brideFatherName)
                        brideMotherNameObservable.postValue(data.personal.brideMotherName)
                        groomFamilyNameObservable.postValue(data.personal.groomFamilyName ?: "")
                        brideFamilyNameObservable.postValue(data.personal.brideFamilyName ?: "")
                        familyCoNameObservable.postValue(data.receptionOfficer.familyCoName)
                        familyCoPhoneObservable.postValue(data.receptionOfficer.familyCoPhone.toString())
                        vipCoNameObservable.postValue(data.receptionOfficer.vipGuestCoName ?: "")
                        vipCoPhoneObservable.postValue(
                            data.receptionOfficer.vipGuestCoPhone.toString() ?: ""
                        )
                        giftCoNameObservable.postValue(data.receptionOfficer.giftCoName ?: "")
                        giftCoPhoneObservable.postValue(
                            data.receptionOfficer.giftCoPhone.toString() ?: ""
                        )
                        usherCoNameObservable.postValue(data.receptionOfficer.guestCoName)
                        usherCoPhoneObservable.postValue(data.receptionOfficer.guestCoPhone.toString())
                        souvenirCoNameObservable.postValue(
                            data.receptionOfficer.souvenirCoName ?: ""
                        )
                        souvenirCoPhoneObservable.postValue(data.receptionOfficer.souvenirCoPhone.toString())
                        souvenirStaffNameObservable.postValue(
                            data.receptionOfficer.souvenirOfficer ?: ""
                        )
                        usherStaffNameObservable.postValue(data.receptionOfficer.guestOfficer ?: "")
                        guestBookingStaffNameObservable.postValue(
                            data.receptionOfficer.guestOfficer ?: ""
                        )
                        quranReaderObservable.postValue(data.marriageOfficer.bookReader ?: "")
                        groomWitnessObservable.postValue(data.marriageOfficer.groomWitness ?: "")
                        brideWitnessObservable.postValue(data.marriageOfficer.brideWitness ?: "")
                        mcObservable.postValue(data.marriageOfficer.masterCeremony ?: "")
                        tausyiahObservable.postValue(data.marriageOfficer.tausyiah ?: "")
                        prayerObservable.postValue(data.marriageOfficer.prayerOfficer ?: "")
                        venueObservable.postValue(data.vendors.venueName)
                        decorationListObservable.postValue(data.vendors.decorationList)
                        cateringListObservable.postValue(data.vendors.cateringList)
                        makeUpListObservable.postValue(data.vendors.makeupList)
                        documentationListObservable.postValue(data.vendors.documentationList)
                        entertainListObservable.postValue(data.vendors.entertainList)
                        mcVendorListObservable.postValue(data.vendors.mcShowList)
                        weddingDressListObservable.postValue(data.vendors.weddingDressList)
                        accessoriesListObservable.postValue(data.vendors.accessoriesList)
                        staffDressListObservable.postValue(data.vendors.staffDressList)
                        lightingObservable.postValue(data.supportVendor.lighting?.name ?: "")
                        generatorObservable.postValue(data.supportVendor.generator?.name ?: "")
                        soundSystemObservable.postValue(data.supportVendor.soundSystem?.name ?: "")
                        multimediaObservable.postValue(data.supportVendor.multimedia?.name ?: "")
                        carObservable.postValue(data.supportVendor.weddingCar ?: "")
                        usherVendorObservable.postValue(data.supportVendor.usher?.name ?: "")
                        invitationAmountObservable.postValue(
                            data.supportVendor.invitationAmount.toString() ?: ""
                        )
                        courierObservable.postValue(data.supportVendor.courier?.name ?: "")
                        etcObservable.postValue(data.supportVendor.etc ?: "")

                        delay(1000)
                        _stateLoadingObservable.postValue(false)
                    } else {
                        _eventShowLoadPageFailedObservable.postValue(Event("gagal memuat konten"))
                        delay(1000)
                        _stateLoadingObservable.postValue(false)
                    }
                } else {
                    _eventShowLoadPageFailedObservable.postValue(
                        Event(
                            getToken.message ?: "gagal memuat konten"
                        )
                    )
                    delay(1000)
                    _stateLoadingObservable.postValue(false)
                }
            } else {
                _eventShowLoadPageFailedObservable.postValue(
                    Event(
                        getToken.message ?: "gagal memuat konten"
                    )
                )
                delay(1000)
                _stateLoadingObservable.postValue(false)
            }
        }
    }

    override fun triggerEventShowNextPage() {
        if (currentPosition + 1 < totalFragment) {
            _positionObservable.postValue(++currentPosition)
        } else viewModelScope.launch {
            _eventShowConfirmationDialog.postValue(Event(Unit))
        }
    }

    override fun triggerEventShowPrevPage() {
        if (currentPosition - 1 >= 0) {
            _positionObservable.postValue(--currentPosition)
        }
    }

    override fun submitForm() {
        val form = form!!

        val lightingName = lightingObservable.value
        val selectedLighting = lightings.find { it.name == lightingName }

        val generatorName = generatorObservable.value
        val selectedGenerator = generators.find { it.name == generatorName }

        val soundSystemName = soundSystemObservable.value
        val selectedSoundSystem = soundSystems.find { it.name == soundSystemName }

        val multimediaName = multimediaObservable.value
        val selectedMultimedia = multimedias.find { it.name == multimediaName }

        val usherName = usherVendorObservable.value
        val selectedUsher = ushers.find { it.name == usherName }

        val courierName = courierObservable.value
        val selectedCourier = couriers.find { it.name == courierName }

        when {
            clientFullnameObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nama Lengkap")
            )
            groomFullnameObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nama Lengkap Mempelai Pria")
            )
            brideFullnameObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nama Lengkap Mempelai Wanita")
            )
            groomFatherNameObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nama Ayah Mempelai Pria")
            )
            groomMotherNameObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nama Ibu Mempelai Pria")
            )
            brideFatherNameObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nama Ayah Mempelai Wanita")
            )
            brideMotherNameObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nama Ibu Mempelai Wanita")
            )
            familyCoNameObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nama Koordinator Keluarga")
            )
            familyCoPhoneObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nomor HP Koordinator Keluarga")
            )
            usherCoNameObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nama Koordinator Among Tamu")
            )
            usherCoPhoneObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nomor Koordinator HP Among Tamu")
            )
            vipCoNameObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nama Koordinator Tamu VIP")
            )
            vipCoPhoneObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nomor HP Koordinator Tamu VIP")
            )
            giftCoNameObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nama Koordinator Kunci Pundi dan Kado")
            )
            giftCoPhoneObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nomor HP Koordinator Kunci Pundi dan Kado")
            )
            souvenirCoNameObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nama Koordinator Souvenir")
            )
            souvenirCoPhoneObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nomor HP Koordinator Souvenir")
            )
            usherStaffNameObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nama Petugas Among Tamu")
            )
            guestBookingStaffNameObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nama Petugas Pembukuan Tamu")
            )
            souvenirStaffNameObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nama Petugas Souvenir")
            )
            quranReaderObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nama Pembaca Al-Qur'an")
            )
            groomWitnessObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nama Saksi Mempelai Pria")
            )
            brideWitnessObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nama Saksi Mempelai Wanita")
            )
            mcObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nama MC Akad")
            )
            tausyiahObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nama Pembaca Tausyiah")
            )
            prayerObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nama Pembaca Doa")
            )
            venueObservable.value.isNullOrBlank() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Nama Vendor Venue")
            )
            decorationListObservable.value.isNullOrEmpty() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Vendor Dekorasi")
            )
            cateringListObservable.value.isNullOrEmpty() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Vendor Catering")
            )
            makeUpListObservable.value.isNullOrEmpty() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Vendor Make Up")
            )
            documentationListObservable.value.isNullOrEmpty() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Vendor Dokumentasi")
            )
            entertainListObservable.value.isNullOrEmpty() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Vendor Entertain")
            )
            mcVendorListObservable.value.isNullOrEmpty() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Vendor MC")
            )
            weddingDressListObservable.value.isNullOrEmpty() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Vendor Baju Pengantin")
            )
            accessoriesListObservable.value.isNullOrEmpty() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Vendor Aksesoris Pengantin")
            )
            staffDressListObservable.value.isNullOrEmpty() -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Vendor Baju Panitia dan Keluarga")
            )
            selectedLighting == null -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Lighting")
            )
            selectedCourier == null -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Courier")
            )
            selectedGenerator == null -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Generator")
            )
            selectedSoundSystem == null -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Sound System")
            )
            selectedMultimedia == null -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Multimedia")
            )
            selectedUsher == null -> _eventShowErrorMessageObservable.postValue(
                Event("Mohon isi Usher")
            )
            else -> {
                viewModelScope.launch {
                    val personal = Personal(
                        id = form.personal.id,
                        clientId = form.personal.clientId,
                        fullname = clientFullnameObservable.value ?: form.personal.fullname,
                        description = clientDescriptionObservable.value
                            ?: Personal.DEFAULT.description,
                        groomName = groomFullnameObservable.value ?: form.personal.groomName,
                        brideName = brideFullnameObservable.value ?: form.personal.brideName,
                        groomFatherName = groomFatherNameObservable.value
                            ?: form.personal.groomFatherName,
                        groomMotherName = groomMotherNameObservable.value
                            ?: form.personal.groomMotherName,
                        groomFamilyName = groomFamilyNameObservable.value
                            ?: form.personal.groomFamilyName,
                        brideFatherName = brideFatherNameObservable.value
                            ?: form.personal.brideFatherName,
                        brideMotherName = brideMotherNameObservable.value
                            ?: form.personal.brideMotherName,
                        brideFamilyName = brideFamilyNameObservable.value
                            ?: form.personal.brideFamilyName,
                    )

                    val reception = ReceptionOfficer(
                        id = form.receptionOfficer.id,
                        clientId = form.receptionOfficer.clientId,
                        familyCoName = familyCoNameObservable.value
                            ?: form.receptionOfficer.familyCoName,
                        familyCoPhone = familyCoPhoneObservable.value?.toLong()
                            ?: form.receptionOfficer.familyCoPhone,
                        guestCoName = usherCoNameObservable.value
                            ?: form.receptionOfficer.guestCoName,
                        guestCoPhone = usherCoPhoneObservable.value?.toLong()
                            ?: form.receptionOfficer.guestCoPhone,
                        vipGuestCoName = vipCoNameObservable.value
                            ?: form.receptionOfficer.vipGuestCoName,
                        vipGuestCoPhone = vipCoPhoneObservable.value?.toLong()
                            ?: form.receptionOfficer.vipGuestCoPhone,
                        giftCoName = giftCoNameObservable.value
                            ?: form.receptionOfficer.giftCoName,
                        giftCoPhone = giftCoPhoneObservable.value?.toLong()
                            ?: form.receptionOfficer.guestCoPhone,
                        souvenirCoName = souvenirCoNameObservable.value
                            ?: form.receptionOfficer.souvenirCoName,
                        souvenirCoPhone = souvenirCoPhoneObservable.value?.toLong()
                            ?: form.receptionOfficer.souvenirCoPhone,
                        guestOfficer = usherStaffNameObservable.value
                            ?: form.receptionOfficer.guestOfficer,
                        guestBookOfficer = guestBookingStaffNameObservable.value
                            ?: form.receptionOfficer.guestBookOfficer,
                        souvenirOfficer = souvenirStaffNameObservable.value
                            ?: form.receptionOfficer.souvenirOfficer
                    )

                    val marriage = MarriageOfficer(
                        id = form.marriageOfficer.id,
                        clientId = form.marriageOfficer.clientId,
                        bookReader = quranReaderObservable.value
                            ?: form.marriageOfficer.bookReader,
                        groomWitness = groomWitnessObservable.value
                            ?: form.marriageOfficer.groomWitness,
                        brideWitness = brideWitnessObservable.value
                            ?: form.marriageOfficer.brideWitness,
                        masterCeremony = mcObservable.value
                            ?: form.marriageOfficer.masterCeremony,
                        tausyiah = tausyiahObservable.value ?: form.marriageOfficer.tausyiah,
                        prayerOfficer = prayerObservable.value
                            ?: form.marriageOfficer.prayerOfficer
                    )

                    val vendors = Vendors(
                        id = form.vendors.id,
                        clientId = form.vendors.clientId,
                        venueName = venueObservable.value ?: Vendors.DEFAULT.venueName,
                        decorationList = decorationListObservable.value
                            ?: Vendors.DEFAULT.decorationList,
                        cateringList = cateringListObservable.value
                            ?: Vendors.DEFAULT.cateringList,
                        makeupList = makeUpListObservable.value ?: Vendors.DEFAULT.makeupList,
                        documentationList = documentationListObservable.value
                            ?: Vendors.DEFAULT.documentationList,
                        entertainList = entertainListObservable.value
                            ?: Vendors.DEFAULT.entertainList,
                        mcShowList = mcVendorListObservable.value ?: Vendors.DEFAULT.mcShowList,
                        weddingDressList = weddingDressListObservable.value
                            ?: Vendors.DEFAULT.weddingDressList,
                        accessoriesList = accessoriesListObservable.value
                            ?: Vendors.DEFAULT.accessoriesList,
                        staffDressList = staffDressListObservable.value
                            ?: Vendors.DEFAULT.staffDressList
                    )

                    val support = SupportVendor(
                        id = form.supportVendor.id,
                        clientId = form.supportVendor.clientId,
                        lightingId = selectedLighting.id,
                        generatorId = selectedGenerator.id,
                        soundSystemId = selectedSoundSystem.id,
                        multimediaId = selectedMultimedia.id,
                        weddingCar = carObservable.value ?: SupportVendor.DEFAULT.weddingCar,
                        usherId = selectedUsher.id,
                        invitationAmount = invitationAmountObservable.value?.toInt()
                            ?: SupportVendor.DEFAULT.invitationAmount,
                        courierId = selectedCourier.id,
                        etc = etcObservable.value ?: SupportVendor.DEFAULT.etc,
                        createdAt = form.supportVendor.createdAt,
                        updatedAt = Date(),
                        lighting = selectedLighting,
                        generator = selectedGenerator,
                        soundSystem = selectedSoundSystem,
                        multimedia = selectedMultimedia,
                        usher = selectedUsher,
                        courier = selectedCourier
                    )

                    val update = formDataSource.updateFormById(
                        token = token!!,
                        formId = form.id,
                        personal = personal,
                        marriageOfficer = marriage,
                        receptionOfficer = reception,
                        vendors = vendors,
                        supportVendor = support
                    )

                    _eventSubmitForm.postValue(Event(update))
                }
            }
        }

    }

    override fun addElementAccessories(data: Pair<String, String>) {
        val currentList = arrayListOf<Pair<String, String>>()
        currentList.addAll(accessoriesListObservable.value ?: emptyList())
        currentList.add(data)
        accessoriesListObservable.postValue(currentList)
    }

    override fun addElementCatering(data: Pair<String, String>) {
        val currentList = arrayListOf<Pair<String, String>>()
        currentList.addAll(cateringListObservable.value ?: emptyList())
        currentList.add(data)
        cateringListObservable.postValue(currentList)
    }

    override fun addElementDocumentation(data: Pair<String, String>) {
        val currentList = arrayListOf<Pair<String, String>>()
        currentList.addAll(documentationListObservable.value ?: emptyList())
        currentList.add(data)
        documentationListObservable.postValue(currentList)
    }

    override fun addElementEntertain(data: Pair<String, String>) {
        val currentList = arrayListOf<Pair<String, String>>()
        currentList.addAll(entertainListObservable.value ?: emptyList())
        currentList.add(data)
        entertainListObservable.postValue(currentList)
    }

    override fun addElementMakeUp(data: Pair<String, String>) {
        val currentList = arrayListOf<Pair<String, String>>()
        currentList.addAll(makeUpListObservable.value ?: emptyList())
        currentList.add(data)
        makeUpListObservable.postValue(currentList)
    }

    override fun addElementMc(data: Pair<String, String>) {
        val currentList = arrayListOf<Pair<String, String>>()
        currentList.addAll(mcVendorListObservable.value ?: emptyList())
        currentList.add(data)
        mcVendorListObservable.postValue(currentList)
    }

    override fun addElementStaffDress(data: Pair<String, String>) {
        val currentList = arrayListOf<Pair<String, String>>()
        currentList.addAll(staffDressListObservable.value ?: emptyList())
        currentList.add(data)
        staffDressListObservable.postValue(currentList)
    }

    override fun addElementDecoration(data: Pair<String, String>) {
        val currentList = arrayListOf<Pair<String, String>>()
        currentList.addAll(decorationListObservable.value ?: emptyList())
        currentList.add(data)
        decorationListObservable.postValue(currentList)
    }

    override fun addElementWeddingDress(data: Pair<String, String>) {
        val currentList = arrayListOf<Pair<String, String>>()
        currentList.addAll(weddingDressListObservable.value ?: emptyList())
        currentList.add(data)
        weddingDressListObservable.postValue(currentList)
    }

    override fun deleteElementAccessories(data: Pair<String, String>) {
        val currentList = accessoriesListObservable.value ?: emptyList()
        val newList = arrayListOf<Pair<String, String>>()
        for (item in currentList) {
            if (item.first != data.first && item.second != data.second) {
                newList.add(item)
            }
        }

        accessoriesListObservable.postValue(newList)
    }

    override fun deleteElementCatering(data: Pair<String, String>) {
        val currentList = cateringListObservable.value ?: emptyList()
        val newList = arrayListOf<Pair<String, String>>()
        for (item in currentList) {
            if (item.first != data.first && item.second != data.second) {
                newList.add(item)
            }
        }
        cateringListObservable.postValue(newList)
    }

    override fun deleteElementDocumentation(data: Pair<String, String>) {
        val currentList = documentationListObservable.value ?: emptyList()
        val newList = arrayListOf<Pair<String, String>>()
        for (item in currentList) {
            if (item.first != data.first && item.second != data.second) {
                newList.add(item)
            }
        }
        documentationListObservable.postValue(newList)
    }

    override fun deleteElementEntertain(data: Pair<String, String>) {
        val currentList = entertainListObservable.value ?: emptyList()
        val newList = arrayListOf<Pair<String, String>>()
        for (item in currentList) {
            if (item.first != data.first && item.second != data.second) {
                newList.add(item)
            }
        }
        entertainListObservable.postValue(newList)
    }

    override fun deleteElementMakeUp(data: Pair<String, String>) {
        val currentList = makeUpListObservable.value ?: emptyList()
        val newList = arrayListOf<Pair<String, String>>()
        for (item in currentList) {
            if (item.first != data.first && item.second != data.second) {
                newList.add(item)
            }
        }
        makeUpListObservable.postValue(newList)
    }

    override fun deleteElementMc(data: Pair<String, String>) {
        val currentList = mcVendorListObservable.value ?: emptyList()
        val newList = arrayListOf<Pair<String, String>>()
        for (item in currentList) {
            if (item.first != data.first && item.second != data.second) {
                newList.add(item)
            }
        }
        mcVendorListObservable.postValue(newList)
    }

    override fun deleteElementStaffDress(data: Pair<String, String>) {
        val currentList = staffDressListObservable.value ?: emptyList()
        val newList = arrayListOf<Pair<String, String>>()
        for (item in currentList) {
            if (item.first != data.first && item.second != data.second) {
                newList.add(item)
            }
        }
        staffDressListObservable.postValue(newList)
    }

    override fun deleteElementDecoration(data: Pair<String, String>) {
        val currentList = decorationListObservable.value ?: emptyList()
        val newList = arrayListOf<Pair<String, String>>()
        for (item in currentList) {
            if (item.first != data.first && item.second != data.second) {
                newList.add(item)
            }
        }
        decorationListObservable.postValue(newList)
    }

    override fun deleteElementWeddingDress(data: Pair<String, String>) {
        val currentList = weddingDressListObservable.value ?: emptyList()
        val newList = arrayListOf<Pair<String, String>>()
        for (item in currentList) {
            if (item.first != data.first && item.second != data.second) {
                newList.add(item)
            }
        }
        weddingDressListObservable.postValue(newList)
    }

    class Factory(private val formDataSource: FormDataSource) :
        ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return FormEditViewModel(formDataSource) as T
        }
    }
}