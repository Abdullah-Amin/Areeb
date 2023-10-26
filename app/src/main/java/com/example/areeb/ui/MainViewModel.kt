package com.example.areeb.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.areeb.data.repo.GithubRepo
import com.example.areeb.data.responses.GithubRepoResponseItem
import com.example.areeb.data.responses.OwnerResponse
import com.example.areeb.network.State
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repo = GithubRepo()

    init {
        getGithubRepoItem()
    }

    private val _githubRepo = MutableLiveData<State<ArrayList<GithubRepoResponseItem>?>>()
    val githubRepo = _githubRepo

    private val _githubRepoOwner = MutableLiveData<State<OwnerResponse?>>()
    val githubRepoOwner = _githubRepoOwner

    private fun getGithubRepoItem() {
        viewModelScope.launch {
            repo.getRepos().collect {
                githubRepo.postValue(it)
                Log.i("abdo", "getGithubRepoOwner: $it.")
            }
        }
    }

    fun getOwner(url: String) {
        getGithubRepoOwner(url)
    }

    private fun getGithubRepoOwner(ownerEndPoint: String) {
        viewModelScope.launch {
            repo.getOwner(ownerEndPoint).collect {
                _githubRepoOwner.postValue(it)
                Log.i("abdo", "getGithubRepoOwner: $it")
            }
        }
    }
}