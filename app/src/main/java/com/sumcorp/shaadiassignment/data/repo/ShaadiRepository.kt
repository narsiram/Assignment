package com.sumcorp.shaadiassignment.data.repo

import com.sumcorp.shaadiassignment.data.local.entity.model.ResultData
import com.sumcorp.shaadiassignment.data.local.entity.model.ShaadiRequestResponse

interface ShaadiRepository {

    fun getShaadiRequest(onSuccess: (ShaadiRequestResponse) -> Unit, onError: (String) -> Unit)
    fun getRequestLocally(onSuccess: (ShaadiRequestResponse?) -> Unit)

    fun updateDataLocally(resultData: ResultData)

}