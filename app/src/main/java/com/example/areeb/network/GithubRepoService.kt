package com.example.areeb.network

import com.example.areeb.data.responses.GithubRepoResponse
import com.example.areeb.data.responses.GithubRepoResponseItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface GithubRepoService {

    @GET("repositories")
    suspend fun getRepos(): Response<ArrayList<GithubRepoResponseItem>>
}