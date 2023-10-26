package com.example.areeb.data.repo

import android.util.Log
import com.example.areeb.data.responses.GithubRepoResponseItem
import com.example.areeb.data.responses.OwnerResponse
import com.example.areeb.network.GithubApiClient
import com.example.areeb.network.GithubRepoService
import com.example.areeb.network.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow

class GithubRepo {

    suspend fun getRepos(): Flow<State<ArrayList<GithubRepoResponseItem>?>> {
        return flow {
            try {
                val result = GithubApiClient.client().getRepos()
                if (result.isSuccessful){
                    Log.i("asd", "getRepos: repo success ${result.errorBody()}")
                    Log.i("abdo", "getRepos: emitting list")
                    Log.i("abdo", "getRepos: $result")
                    emit(State.Success(result.body()))
                }else{
                    Log.i("asd", "getRepos: repo error ${result.errorBody()}")
                    emit(State.Error(result.errorBody()?.string()))
                }
            }catch (e: Exception){
                emit(State.Error(e.localizedMessage))
            }
        }.buffer(10)
    }

    suspend fun getOwner(ownerEndPoint: String): Flow<State<OwnerResponse?>>{
        return flow {
            try {
                val result = GithubApiClient.client().getOwner(ownerEndPoint)
                if (result.isSuccessful){
                    Log.i("asd", "getRepos: owner success ${result.errorBody()}")
                    Log.i("abdo", "getRepos: emitting owner")
                    Log.i("abdo", "getRepos: $result")
                    emit(State.Success(result.body()))
                }else{
                    Log.i("asd", "getRepos: owner error ${result.errorBody()}")
                    emit(State.Error(result.errorBody()?.string()))
                }
            }catch (e: Exception){
                emit(State.Error(e.localizedMessage))
            }
        }
    }
}