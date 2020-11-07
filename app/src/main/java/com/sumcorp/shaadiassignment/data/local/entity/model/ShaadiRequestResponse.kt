package com.sumcorp.shaadiassignment.data.local.entity.model

import com.google.gson.annotations.SerializedName


data class ShaadiRequestResponse(

    @SerializedName("results")
    var results: ArrayList<ResultData>

)