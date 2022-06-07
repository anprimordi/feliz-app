package com.feliz.apps.data.remote.model.response.mapper.form

import com.feliz.apps.data.remote.model.response.FormResponse
import com.feliz.apps.domain.model.common.Mapper
import com.feliz.apps.domain.model.form.vendors.Vendors

object MapperResponseFormVendor : Mapper<Vendors, FormResponse.Vendors> {
    override fun toDomain(model: FormResponse.Vendors): Vendors {
        return Vendors(
            id = model.id ?: Vendors.DEFAULT.id,
            clientId = model.clientId ?: Vendors.DEFAULT.clientId,
            venueName = model.venueName ?: Vendors.DEFAULT.venueName,
            decorationList = parseVendor(Pair(model.decorationName!!, model.decorationDesc!!))
                ?: Vendors.DEFAULT.decorationList,
            cateringList = parseVendor(Pair(model.cateringName!!, model.cateringDesc!!))
                ?: Vendors.DEFAULT.cateringList,
            makeupList = parseVendor(Pair(model.makeupName!!, model.makeupDesc!!))
                ?: Vendors.DEFAULT.makeupList,
            documentationList = parseVendor(
                Pair(
                    model.documentationName!!,
                    model.documentationDesc!!
                )
            )
                ?: Vendors.DEFAULT.documentationList,
            entertainList = parseVendor(Pair(model.entertainName!!, model.entertainDesc!!))
                ?: Vendors.DEFAULT.entertainList,
            mcShowList = parseVendor(Pair(model.mcShowName!!, model.mcShowDesc!!))
                ?: Vendors.DEFAULT.mcShowList,
            weddingDressList = parseVendor(Pair(model.weddingDressName!!, model.weddingDressDesc!!))
                ?: Vendors.DEFAULT.weddingDressList,
            accessoriesList = parseVendor(Pair(model.accessoriesName!!, model.accessoriesDesc!!))
                ?: Vendors.DEFAULT.accessoriesList,
            staffDressList = parseVendor(Pair(model.familyDressName!!, model.familyDressDesc!!))
                ?: Vendors.DEFAULT.staffDressList
        )
    }

    override fun fromDomain(model: Vendors): FormResponse.Vendors {
        throw UnsupportedOperationException()
    }

    private fun parseVendor(pair: Pair<List<String>, List<String>>): ArrayList<Pair<String, String>> {
        val list = arrayListOf<Pair<String, String>>()

        val x = listOf(1, 2, 3, 4, 5)
        val y = listOf(1, 2, 3)
        val z = arrayListOf<Pair<Int, Int>>()

        for ((index, value) in x.withIndex()) {
            val valueY = y.getOrNull(index)
            z.add(Pair(value, valueY ?: -1))
        }


        for ((index, value) in pair.first.withIndex()) {
            val valueSecond = pair.second.getOrNull(index)
            list.add(Pair(value, valueSecond ?: ""))
        }

        return list
    }
}