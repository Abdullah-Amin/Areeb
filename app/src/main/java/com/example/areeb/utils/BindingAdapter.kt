package com.example.areeb

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.areeb.network.State

@BindingAdapter(value = ["app:showWhenError"])
fun <T> showWhenError(view: View, state: State<T>?){
    if (state is State.Error){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}