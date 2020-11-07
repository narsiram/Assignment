package com.sumcorp.shaadiassignment.data.local.entity.model

import com.google.gson.annotations.SerializedName

data class ImageData(
    @SerializedName("large")
    var profileUrl: String
)