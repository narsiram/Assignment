package com.sumcorp.shaadiassignment.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sumcorp.shaadiassignment.data.repo.ShaadiRepository
import com.sumcorp.sliceassignment.retrofit.NetworkHelper

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private var networkHelper: NetworkHelper,
    private var shaadiRepository: ShaadiRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(networkHelper, shaadiRepository) as T
    }
}