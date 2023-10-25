package com.example.areeb.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.areeb.data.responses.GithubRepoResponseItem

class GithubRepoDifUtil(
    val oldList: ArrayList<GithubRepoResponseItem>,
    val newList: ArrayList<GithubRepoResponseItem>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (
                oldList[oldItemPosition].id == newList[newItemPosition].id
                        && oldList[oldItemPosition].fullName == newList[newItemPosition].fullName
                )
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }

}