package com.example.areeb.network

sealed class State<out T>{
    data class Success<T>(val data: T?): State<T>()
    data class Error(val error: String?): State<Nothing>()
}
