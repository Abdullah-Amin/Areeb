package com.example.areeb.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.areeb.data.repo.GithubRepo
import com.example.areeb.data.responses.GithubRepoResponseItem
import com.example.areeb.data.responses.OwnerResponse
import com.example.areeb.network.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repo = GithubRepo()

//    private val url: String = ""

    init {
        getGithubRepoItem()
    }

    private val _githubRepo = MutableLiveData<State<ArrayList<GithubRepoResponseItem>?>>()
    val githubRepo = _githubRepo

    private val _githubRepoOwner = MutableLiveData<State<OwnerResponse?>>()
    val githubRepoOwner = _githubRepoOwner

    private fun getGithubRepoItem() {
//        _githubRepo.value = State.Loading("Loading...")
        viewModelScope.launch {
            repo.getRepos().collect {
//                _githubRepo.value = it
                Log.i("abdo", "getGithubRepoOwner: $it")

                when(it){
                    is State.Error -> {
                        _githubRepo.value = State.Error(it.error)
                        Log.i("abdo", "getGithubRepoItem: collecting List ")
                        Log.i("abdo", "getGithubRepoItem: ${it.error?.get(0)}")
                        _githubRepo.postValue(it)
                    }
                    is State.Loading -> {
//                        _githubRepo.value = State.Loading()
                        Log.i("abdo", "getGithubRepoItem: collecting List ")
                        Log.i("abdo", "getGithubRepoItem: $it)")
//                        _githubRepo.postValue(it)
                    }
                    is State.Success -> {
                        _githubRepo.value = State.Success(it.data)
                        Log.i("abdo", "getGithubRepoItem: collecting List ")
                        Log.i("abdo", "getGithubRepoItem: ${it.data?.get(0)}")
                        _githubRepo.postValue(it)
                    }
                    else -> {}
                }
            }
        }
    }

    fun getOwner(url: String) {
        getGithubRepoOwner(url)
    }

    private fun getGithubRepoOwner(ownerEndPoint: String) {
//        _githubRepoOwner.value = State.Loading("Loading...")
        viewModelScope.launch {
            repo.getOwner(ownerEndPoint).collect {
//                _githubRepoOwner.value = it
                Log.i("abdo", "getGithubRepoOwner: $it")
                when(it){
                    is State.Error -> {
                        _githubRepoOwner.value = State.Error(it.error)
                        Log.i("abdo", "getGithubRepoItem: collecting List ")
                        Log.i("abdo", "getGithubRepoItem: ${it.error?.get(0)}")
                        _githubRepoOwner.postValue(it)
                    }
                    is State.Loading -> {
//                        _githubRepoOwner.value = State.Loading()
                        Log.i("abdo", "getGithubRepoItem: collecting List ")
                        Log.i("abdo", "getGithubRepoItem: $it)")
//                        _githubRepoOwner.postValue(it)
                    }
                    is State.Success -> {
                        _githubRepoOwner.value = State.Success(it.data)
                        Log.i("abdo", "getGithubRepoItem: collecting List ")
                        Log.i("abdo", "getGithubRepoItem: ${it.data?.name}")
                        _githubRepoOwner.postValue(it)
                    }
                    else -> {}
                }
            }
        }
    }
}