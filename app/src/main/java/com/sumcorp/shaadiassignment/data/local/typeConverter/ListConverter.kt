package com.sumcorp.shaadiassignment.data.local.typeConverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sumcorp.shaadiassignment.data.local.entity.model.*
import java.lang.reflect.Type


object ListConverter {

    @TypeConverter
    @JvmStatic
    fun fromName(value: NameData) = Gson().toJson(value)

    @TypeConverter
    @JvmStatic
    fun fromName(value: LoginData) = Gson().toJson(value)

    @TypeConverter
    @JvmStatic
    fun fromName(value: DataOfBirth) = Gson().toJson(value)

    @TypeConverter
    @JvmStatic
    fun fromName(value: ImageData) = Gson().toJson(value)

    @TypeConverter
    @JvmStatic
    fun toName(value: String): NameData {
        val listType: Type = object : TypeToken<NameData>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun toDOB(value: String): DataOfBirth {
        val listType: Type = object : TypeToken<DataOfBirth>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun toImage(value: String): ImageData {
        val listType: Type = object : TypeToken<ImageData>() {}.type
        return Gson().fromJson(value, listType)
    }


    @TypeConverter
    @JvmStatic
    fun toLogin(value: String): LoginData {
        val listType: Type = object : TypeToken<LoginData>() {}.type
        return Gson().fromJson(value, listType)
    }


}