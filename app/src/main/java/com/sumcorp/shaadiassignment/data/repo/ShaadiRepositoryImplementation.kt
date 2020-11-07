package com.sumcorp.shaadiassignment.data.repo

import com.sumcorp.shaadiassignment.data.local.dao.ShaadiDao
import com.sumcorp.shaadiassignment.data.local.entity.model.ResultData
import com.sumcorp.shaadiassignment.data.local.entity.model.ShaadiRequestResponse
import com.sumcorp.sliceassignment.retrofit.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShaadiRepositoryImplementation(
    private val dao: ShaadiDao,
    private val apiService: ApiService
) : ShaadiRepository {


    override fun getShaadiRequest(
        onSuccess: (ShaadiRequestResponse) -> Unit,
        onError: (String) -> Unit
    ) {
        val call = apiService.getRequest()

        call.enqueue(object : Callback<ShaadiRequestResponse> {
            override fun onResponse(
                call: Call<ShaadiRequestResponse>,
                responseShaadi: Response<ShaadiRequestResponse>
            ) {
                if (responseShaadi.isSuccessful && responseShaadi.body() != null) {
                    val shaadiResponse = responseShaadi.body()


                    CoroutineScope(Dispatchers.IO).launch {

                        onSuccess(shaadiResponse!!)

                        saveData(shaadiResponse.results)
                    }


                } else {
                    onError(responseShaadi.message())
                }
            }

            override fun onFailure(call: Call<ShaadiRequestResponse>, t: Throwable) {
                onError("Please try again after some time")
            }

        })

    }

    override fun getRequestLocally(
        onSuccess: (ShaadiRequestResponse?) -> Unit,
    ) {
        Thread {
            var requestResponse = ShaadiRequestResponse(dao.getRequests() as ArrayList<ResultData>)

            onSuccess(requestResponse)
        }.start()
    }

    private fun saveData(resultData: ArrayList<ResultData>) {
        for (data in resultData)
            dao.insertData(data)
    }


    override fun updateDataLocally(resultData: ResultData) {
        Thread {
            dao.updateData(resultData)
        }.start()
    }
}