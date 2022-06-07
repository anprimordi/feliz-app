package com.feliz.apps.presentation.ui.form.edit

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.feliz.apps.presentation.ui.form.edit.personal.client.EditClientFragment
import com.feliz.apps.presentation.ui.form.edit.personal.family.EditFamilyFragment
import com.feliz.apps.presentation.ui.form.edit.personal.parent.EditParentFragment
import com.feliz.apps.presentation.ui.form.edit.staff.ceremony.EditCeremonyFragment
import com.feliz.apps.presentation.ui.form.edit.staff.familyco.EditFamilyCoordinatorFragment
import com.feliz.apps.presentation.ui.form.edit.staff.guestbooking.EditGuestBookingFragment
import com.feliz.apps.presentation.ui.form.edit.staff.prayer.EditPrayerFragment
import com.feliz.apps.presentation.ui.form.edit.staff.souvenir.EditSouvenirCoordinatorFragment
import com.feliz.apps.presentation.ui.form.edit.staff.vipco.EditVipCoordinatorFragment
import com.feliz.apps.presentation.ui.form.edit.vendor.accessories.EditAccessoriesFragment
import com.feliz.apps.presentation.ui.form.edit.vendor.car.EditCarFragment
import com.feliz.apps.presentation.ui.form.edit.vendor.catering.EditCateringFragment
import com.feliz.apps.presentation.ui.form.edit.vendor.documentation.EditDocumentationFragment
import com.feliz.apps.presentation.ui.form.edit.vendor.entertain.EditEntertainFragment
import com.feliz.apps.presentation.ui.form.edit.vendor.lighting.EditLightingFragment
import com.feliz.apps.presentation.ui.form.edit.vendor.makeup.EditMakeUpFragment
import com.feliz.apps.presentation.ui.form.edit.vendor.mc.EditMcFragment
import com.feliz.apps.presentation.ui.form.edit.vendor.staffdress.EditStaffDressFragment
import com.feliz.apps.presentation.ui.form.edit.vendor.venue.EditVenueFragment
import com.feliz.apps.presentation.ui.form.edit.vendor.weddingdress.EditWeddingDressFragment

class FormEditViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val fragments = listOf(
        EditClientFragment(),
        EditParentFragment(),
        EditFamilyFragment(),
        EditFamilyCoordinatorFragment(),
        EditVipCoordinatorFragment(),
        EditSouvenirCoordinatorFragment(),
        EditGuestBookingFragment(),
        EditCeremonyFragment(),
        EditPrayerFragment(),
        EditVenueFragment(),
        EditCateringFragment(),
        EditMakeUpFragment(),
        EditDocumentationFragment(),
        EditEntertainFragment(),
        EditMcFragment(),
        EditWeddingDressFragment(),
        EditAccessoriesFragment(),
        EditStaffDressFragment(),
        EditLightingFragment(),
        EditCarFragment()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}