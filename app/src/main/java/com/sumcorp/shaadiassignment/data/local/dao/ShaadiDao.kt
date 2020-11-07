package com.sumcorp.shaadiassignment.data.local.dao

import androidx.room.*
import com.sumcorp.shaadiassignment.data.local.entity.model.ResultData
import com.sumcorp.shaadiassignment.data.local.entity.model.ShaadiRequestResponse

@Dao
interface ShaadiDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(resultData: ResultData)

    @Query("select * from tbl_shaadi_data")
    fun getRequests(): List<ResultData>

    @Update
    fun updateData(resultData: ResultData)
}