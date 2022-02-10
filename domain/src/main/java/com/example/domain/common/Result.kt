package com.example.domain.common

/**
 * A generic class that holds a value or an error status.
 * @param <T>
 */
sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Loading<out T>(val data: T) : Result<T>()
    data class Error(val msg: String) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Loading<*> -> "Loading[loading=loading]"
            is Error -> "Error[error=$msg]"
        }
    }
}