package com.example.areeb.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.areeb.utils.Constants
import com.example.areeb.adapters.GithubAdapter
import com.example.areeb.databinding.ActivityMainBinding
import com.example.areeb.network.OwnerCallback
import com.example.areeb.network.State
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        binding.lifecycleOwner = this

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.githubRepo.observe(this@MainActivity) {
                Log.i("abdo", "onCreate: $it")
                when (it) {
                    is State.Success -> {
                        binding.progressbar.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                        Log.i("abdo", "onCreate: ${it.data?.get(0)}")
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
                    is State.Error -> {
                        binding.errorImage.visibility = View.VISIBLE
                        binding.progressbar.visibility = View.GONE
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}