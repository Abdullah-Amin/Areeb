package com.example.areeb.data.responses


import com.google.gson.annotations.SerializedName

data class GithubRepoResponseItem(

    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("node_id")
    val nodeId: String?,
    @SerializedName("owner")
    val owner: Owner?,
)