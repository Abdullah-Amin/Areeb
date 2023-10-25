package com.example.areeb.network

import com.example.areeb.data.responses.OwnerResponse

interface OwnerCallback {

    fun getOwnerEndPoint(endPoint: String?)
}