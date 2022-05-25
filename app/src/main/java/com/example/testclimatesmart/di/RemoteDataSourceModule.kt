package com.example.testclimatesmart.di

import com.example.testclimatesmart.data.remote.RemoteClimateDayCelsiusDataSource
import com.example.testclimatesmart.data.remote.RemoteClimateDayDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RemoteDataSourceModule {
    @Binds
    fun provideRemoteClimateDataSource(repository: RemoteClimateDayCelsiusDataSource): RemoteClimateDayDataSource

}