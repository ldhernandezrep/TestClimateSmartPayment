package com.example.testclimatesmart.di

import com.example.testclimatesmart.repository.ForecastClimateLastDaysReposiroty
import com.example.testclimatesmart.repository.ForecastClimateLastDaysReposirotyImple
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun provideForecastClimateRepository(repository: ForecastClimateLastDaysReposirotyImple): ForecastClimateLastDaysReposiroty

}