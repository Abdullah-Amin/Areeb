package com.example.areeb.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.areeb.R
import com.example.areeb.data.responses.GithubRepoResponseItem
import com.example.areeb.databinding.RepoItemBinding
import com.example.areeb.network.OwnerCallback
import com.squareup.picasso.Picasso
import kotlinx.coroutines.DelicateCoroutinesApi

class GithubAdapter(
    private var githubList: ArrayList<GithubRepoResponseItem>?,
    val param: OwnerCallback
) : RecyclerView.Adapter<GithubAdapter.GithubHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GithubHolder(RepoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = githubList!!.size

    override fun onBindViewHolder(holder: GithubHolder, position: Int) {
        Log.i("abdo", "onBindViewHolder: ${githubList?.get(position)}")
        Picasso.get()
            .load(githubList?.get(position)?.owner?.avatarUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.binding.imageView)

        holder.binding.nameTV.text = githubList?.get(position)?.owner?.login

        holder.itemView.setOnClickListener {
            param.getOwnerEndPoint(githubList?.get(position)?.owner?.login)
        }
    }

    class GithubHolder(val binding: RepoItemBinding) : RecyclerView.ViewHolder(binding.root)
}