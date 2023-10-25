package com.example.areeb.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.areeb.Constants
import com.example.areeb.adapters.GithubAdapter
import com.example.areeb.data.responses.GithubRepoResponseItem
import com.example.areeb.data.responses.OwnerResponse
import com.example.areeb.databinding.ActivityMainBinding
import com.example.areeb.network.OwnerCallback
import com.example.areeb.network.State
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private var githubList: ArrayList<GithubRepoResponseItem>? = null

    var ownerResponse: OwnerResponse? = null
    var ownerUrl: String? = null

    @SuppressLint("NotifyDataSetChanged")
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        GlobalScope.launch(Dispatchers.Main) {
            viewModel.githubRepo.observe(this@MainActivity) {
                Log.i("abdo", "onCreate: $it")
                when (it) {
                    is State.Success -> {
                        Log.i("abdo", "onCreate: ${it.data?.get(0)}")
//                            adapter!!.setData(it.data!!)
                        binding.recyclerView.adapter =
                            GithubAdapter(it.data, object : OwnerCallback {
                                override fun getOwnerEndPoint(endPoint: String?) {
                                    Log.i("abdo", "getOwnerUrl from adapter: $endPoint")
                                    val intent =
                                        Intent(this@MainActivity, OwnerDetailsActivity::class.java)
                                    intent.putExtra(Constants.OWNER_END_POINT, endPoint)
                                    startActivity(intent)
                                }
                            })

                    }
                    is State.Loading -> {
                        binding.progressbar.visibility = View.VISIBLE
                        Toast.makeText(this@MainActivity, "Loading...", Toast.LENGTH_LONG)
                            .show()
                    }
                    is State.Error -> {
                        binding.progressbar.visibility = View.VISIBLE
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}