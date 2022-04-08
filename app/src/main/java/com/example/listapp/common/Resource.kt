package com.example.listapp.common

/**
 * Created by Berk Ç. on 8.04.2022.
 */
sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    class Success<T>(val data: T) : Resource<T>()
    class Error(val errorMessage: String) : Resource<Nothing>()
}
