package com.sumcorp.shaadiassignment.ui.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sumcorp.shaadiassignment.R
import com.sumcorp.shaadiassignment.data.local.ShaadiDatabase
import com.sumcorp.shaadiassignment.data.local.entity.model.ResultData
import com.sumcorp.shaadiassignment.data.repo.ShaadiRepositoryImplementation
import com.sumcorp.shaadiassignment.ui.base.BaseActivity
import com.sumcorp.shaadiassignment.ui.viewModel.MainViewModel
import com.sumcorp.shaadiassignment.ui.viewModel.MainViewModelFactory
import com.sumcorp.sliceassignment.retrofit.NetworkHelper
import com.sumcorp.sliceassignment.retrofit.RetrofitBuilder
import kotlinx.android.synthetic.main.activity_request_list.*

class RequestListActivity : BaseActivity(), RequestListAdapter.OnClickEvent {

    var adapter: RequestListAdapter? = null
    var viewModel: MainViewModel? = null
    var resultData: ArrayList<ResultData>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_list)

        setUpRecyclerView()
        setupViewModel()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel!!.shaadiRequestListResponse.observe(this, Observer {
            setDataToAdapter(it.results)
        })

        viewModel!!.errorResponse.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this, MainViewModelFactory(
                NetworkHelper(this),
                ShaadiRepositoryImplementation(
                    ShaadiDatabase.getInstance(this).movieDao(),
                    RetrofitBuilder.buildService()
                )
            )
        )[MainViewModel::class.java]

        getData()
    }

    private fun setUpRecyclerView() {
        recyclerView.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        adapter = RequestListAdapter(this)
        recyclerView.adapter = adapter

    }

    private fun getData() {
        viewModel!!.getShaadiRequest()
    }

    private fun setDataToAdapter(requestList: ArrayList<ResultData>) {
        resultData = requestList
        adapter!!.setData(requestList)
    }

    override fun onItemClick(pos: Int, view: View) {
        when (view.id) {
            R.id.btnAccept -> resultData!!.get(pos).status = "accept"
            R.id.btnReject -> resultData!!.get(pos).status = "reject"
        }
        viewModel!!.updateData(resultData!![pos])
        adapter!!.notifyItemChanged(pos)
    }

}