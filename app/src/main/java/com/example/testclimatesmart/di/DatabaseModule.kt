package com.example.testclimatesmart.di

import android.content.Context
import com.example.testclimatesmart.data.local.DataBaseWheater
import com.example.testclimatesmart.data.local.DayClimateDao
import com.example.testclimatesmart.data.local.MainClimateDao
import com.example.testclimatesmart.data.local.WeatherDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): DataBaseWheater {
        return DataBaseWheater.getDatabase(context)
    }

    @Provides
    fun provideDayClimateDao(appDatabase: DataBaseWheater): DayClimateDao {
        return appDatabase.dayClimateDao()
    }

    @Provides
    fun providWeatherDao(appDatabase: DataBaseWheater): WeatherDao {
        return appDatabase.weatherDao()
    }

    @Provides
    fun providMainDao(appDatabase: DataBaseWheater): MainClimateDao {
        return appDatabase.mainDao()
    }

}