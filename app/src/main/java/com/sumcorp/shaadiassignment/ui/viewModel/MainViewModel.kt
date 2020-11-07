package com.sumcorp.shaadiassignment.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sumcorp.shaadiassignment.data.local.entity.model.ResultData
import com.sumcorp.shaadiassignment.data.local.entity.model.ShaadiRequestResponse
import com.sumcorp.shaadiassignment.data.repo.ShaadiRepository
import com.sumcorp.sliceassignment.retrofit.NetworkHelper

class MainViewModel(
    private var networkHelper: NetworkHelper,
    private var shaadiRepository: ShaadiRepository
) : ViewModel() {

    private val _shaadiResponse = MutableLiveData<ShaadiRequestResponse>()

    val shaadiRequestListResponse: LiveData<ShaadiRequestResponse>
        get() = _shaadiResponse

    private val _errorResponse = MutableLiveData<String>()

    val errorResponse: LiveData<String>
        get() = _errorResponse

    fun getShaadiRequest() {
        if (networkHelper.isNetworkConnected()) {
            shaadiRepository.getShaadiRequest({
                _shaadiResponse.postValue(it)
            }, {
                _errorResponse.postValue(it)
            })
        } else {
            shaadiRepository.getRequestLocally({
                if (it != null && it.results.isNotEmpty())
                    _shaadiResponse.postValue(it)
                else
                    _errorResponse.postValue("response empty")
            })
        }
    }

    fun updateData(resultData: ResultData) {
        shaadiRepository.updateDataLocally(resultData)
    }

}
