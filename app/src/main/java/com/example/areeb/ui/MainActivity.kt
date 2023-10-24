package com.example.areeb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import com.example.areeb.adapters.GithubAdapter
import com.example.areeb.data.responses.GithubRepoResponseItem
import com.example.areeb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var githubList: ArrayList<GithubRepoResponseItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        viewModel.githubRepo.observe(this){response ->
            Log.i("abdo", "onCreate: ${response[0]}")
            githubList = response
        }
        binding.recyclerView.adapter = GithubAdapter(githubList)
    }
}