package com.sumcorp.sliceassignment.retrofit

import com.sumcorp.shaadiassignment.data.local.entity.model.ShaadiRequestResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("api/?results=10")
    fun getRequest(): Call<ShaadiRequestResponse>

}