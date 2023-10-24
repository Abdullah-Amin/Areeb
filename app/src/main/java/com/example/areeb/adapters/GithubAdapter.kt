package com.example.areeb.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.areeb.R
import com.example.areeb.data.responses.GithubRepoResponseItem
import com.example.areeb.databinding.RepoItemBinding
import com.squareup.picasso.Picasso

class GithubAdapter(
    val githubList: ArrayList<GithubRepoResponseItem>
) : RecyclerView.Adapter<GithubAdapter.GithubHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        GithubHolder(RepoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = githubList.size

    override fun onBindViewHolder(holder: GithubHolder, position: Int) {
        Picasso.get()
            .load(githubList[position].owner?.avatarUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.binding.imageView)

        holder.binding.nameTV.text = githubList[position].owner?.login
        holder.binding.repositoryTV.text = githubList[position].name
    }

    class GithubHolder(val binding: RepoItemBinding) : RecyclerView.ViewHolder(binding.root)
}