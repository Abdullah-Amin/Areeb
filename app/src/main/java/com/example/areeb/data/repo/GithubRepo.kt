package com.example.areeb.data.repo

import com.example.areeb.data.responses.GithubRepoResponseItem
import com.example.areeb.network.GithubApiClient
import com.example.areeb.network.GithubRepoService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GithubRepo {

    suspend fun getRepos(): Flow<ArrayList<GithubRepoResponseItem>?> {
        return flow {
            emit(GithubApiClient.client().getRepos().body())
        }
    }
}