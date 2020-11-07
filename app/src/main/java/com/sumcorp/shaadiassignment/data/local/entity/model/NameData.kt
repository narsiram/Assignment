package com.sumcorp.shaadiassignment.data.local.entity.model

import com.google.gson.annotations.SerializedName

data class NameData(

    @SerializedName("title")
    var title: String,
    @SerializedName("first")
    var firstName: String,
    @SerializedName("last")
    var lastName: String
)