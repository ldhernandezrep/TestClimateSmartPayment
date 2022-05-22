package com.example.testclimatesmart.core

import java.lang.Exception

sealed class ResultsState<out T> {
    class Loading<out T> : ResultsState<T>()
    data class Success<out T>(val data: T) : ResultsState<T>()
    data class Failure(val exception: Exception) : ResultsState<Nothing>()

}