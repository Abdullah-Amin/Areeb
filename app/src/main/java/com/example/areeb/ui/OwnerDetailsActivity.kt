package com.example.areeb.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.areeb.utils.Constants
import com.example.areeb.R
import com.example.areeb.databinding.ActivityOwnerDetailsBinding
import com.example.areeb.network.State
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class OwnerDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOwnerDetailsBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var endPoint: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOwnerDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        endPoint = intent.getStringExtra(Constants.OWNER_END_POINT).toString()

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.getOwner(endPoint)
            viewModel.githubRepoOwner.observe(this@OwnerDetailsActivity) {
                when (it) {
                    is State.Success -> {
                        Log.i("abdo", "getOwnerUrl observe: ${it.data}")
                        Picasso.get()
                            .load(it.data?.avatarUrl)
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .into(binding.imageView)

                        binding.nameTV.text = it.data?.name
                        binding.repositoryTV.text = it.data?.login
                        binding.dateTV.text = getCreatedAt(it.data?.createdAt!!)
                    }
                    is State.Error -> {
                        Toast.makeText(
                            this@OwnerDetailsActivity,
                            it.error,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun getCreatedAt(date: String): String? {

        val currentDateAndTime = SimpleDateFormat("MM-yyyy").format(Date())
        val nowYear = currentDateAndTime.split("-")[1]
        val nowMonth = currentDateAndTime.split("-")[0]

        val mDate = date.split("T")[0]
        val responseYear = mDate.split("-")[0]
        val responseMonth = mDate.split("-")[1]

        val newFormat = SimpleDateFormat("EEE, MMM dd,yyyy")
        val changedDate = SimpleDateFormat("yyyy-MM-dd").parse(mDate)?.let { newFormat.format(it) }

        if (nowYear.toInt() == responseYear.toInt()) {
            if (nowMonth.toInt() - responseMonth.toInt() >= 6) {
                return changedDate
            } else {
                return "${nowMonth.toInt() - responseMonth.toInt()} months ago"
            }
        } else {
            return changedDate
        }
    }
}