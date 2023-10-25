package com.example.areeb.network

import com.example.areeb.data.responses.GithubRepoResponseItem

sealed class State<out T>{
    data class Success<T>(val data: T?): State<T>()
    object Loading: State<Nothing>()
    data class Error<T>(val error: String?): State<T>()
}
