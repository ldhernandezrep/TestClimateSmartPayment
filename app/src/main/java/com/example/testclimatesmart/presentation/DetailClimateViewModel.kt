package com.example.testclimatesmart.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.testclimatesmart.core.ResultsState
import com.example.testclimatesmart.repository.ForecastClimateLastDaysReposiroty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class DetailClimateViewModel @Inject constructor(
    private val forecastClimateLastDaysReposiroty: ForecastClimateLastDaysReposiroty
) : ViewModel() {

    fun getClimateDetailByDt(
        dt: Long
    ) = liveData(
        viewModelScope.coroutineContext + Dispatchers.IO
    ) {
        emit(ResultsState.Loading())
        try {
            emit(
                ResultsState.Success(
                    forecastClimateLastDaysReposiroty.getClimateDayByDt(dt)
                )
            )
        } catch (ex: Exception) {
            emit(ResultsState.Failure(ex))
        }
    }

}