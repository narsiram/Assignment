package com.sumcorp.shaadiassignment.data.local.entity.model

import com.google.gson.annotations.SerializedName

data class DataOfBirth(
    @SerializedName("age")
    var age: Int
)