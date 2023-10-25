package com.example.areeb.network

import com.example.areeb.data.responses.GithubRepoResponse
import com.example.areeb.data.responses.GithubRepoResponseItem
import com.example.areeb.data.responses.OwnerResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubRepoService {

    @GET("repositories")
    suspend fun getRepos(): Response<ArrayList<GithubRepoResponseItem>>

    @GET("users/{path}")
    suspend fun getOwner(@Path("path") ownerUrl: String): Response<OwnerResponse>
}