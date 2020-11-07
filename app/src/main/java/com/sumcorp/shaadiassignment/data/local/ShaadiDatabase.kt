package com.sumcorp.shaadiassignment.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sumcorp.shaadiassignment.data.local.dao.ShaadiDao
import com.sumcorp.shaadiassignment.data.local.entity.model.ResultData
import com.sumcorp.shaadiassignment.data.local.typeConverter.ListConverter

@Database(entities = [ResultData::class], version = 1)
@TypeConverters(ListConverter::class)
abstract class ShaadiDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "shaadi-app"

        @Volatile
        private var INSTANCE: ShaadiDatabase? = null

        fun getInstance(context: Context): ShaadiDatabase {
            synchronized(this) {
                var instace = INSTANCE

                if (instace == null) {
                    instace = Room.databaseBuilder(
                        context.applicationContext, ShaadiDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                }

                INSTANCE = instace
                return instace

            }
        }
    }

    abstract fun movieDao(): ShaadiDao
}