package com.sumcorp.shaadiassignment.data.local.entity.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.sumcorp.shaadiassignment.data.local.typeConverter.ListConverter

@Entity(tableName = "tbl_shaadi_data")
data class ResultData(
    @PrimaryKey(autoGenerate = true)
    val primaryKey: Int,

    @ColumnInfo(name = "name")
    @TypeConverters(ListConverter::class)
    @SerializedName("name")
    var name: NameData,

    @ColumnInfo(name = "gender")

    @SerializedName("gender")
    var gender: String,

    @ColumnInfo(name = "dob")

    @SerializedName("dob")
    var dob: DataOfBirth,

    @ColumnInfo(name = "picture")

    @SerializedName("picture")
    var imageData: ImageData,

    @ColumnInfo(name = "login")

    @SerializedName("login")
    var loginData: LoginData,

    @ColumnInfo(name = "status")

    var status: String? = null

)