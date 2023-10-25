package com.example.areeb.data.responses


import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("url")
    val ownerUrl: String?,
    @SerializedName("login")
    val login: String?
)