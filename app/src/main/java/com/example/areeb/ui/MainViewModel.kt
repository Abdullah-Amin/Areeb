package com.example.areeb.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.areeb.data.repo.GithubRepo
import com.example.areeb.data.responses.GithubRepoResponseItem
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    val repo = GithubRepo()

    val githubRepo = MutableLiveData<ArrayList<GithubRepoResponseItem>>()

    init {
        getGithubRepoItem()
    }

    private fun getGithubRepoItem() {
        viewModelScope.launch {
            repo.getRepos().collect{
                githubRepo.postValue(it)
            }
        }
    }
}