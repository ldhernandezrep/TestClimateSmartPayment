package com.example.testclimatesmart.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [DayClimateEntity::class, WeatherEntity::class],
    version = 2
)
abstract class DataBaseWheater : RoomDatabase() {

    abstract fun dayClimateDao(): DayClimateDao
    abstract fun weatherDao(): WeatherDao

    companion object {

        private var INSTANCE: DataBaseWheater? = null

        fun getDatabase(context: Context): DataBaseWheater {
            INSTANCE = INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                DataBaseWheater::class.java,
                "wheater_db"
            ).fallbackToDestructiveMigration().build()
            return INSTANCE!!
        }

        fun destroyDatabase() {
            INSTANCE = null
        }

    }

}