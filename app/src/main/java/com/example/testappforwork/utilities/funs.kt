package com.example.testappforwork.utilities

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.example.testappforwork.models.Result

typealias ResultMutableLiveData<T> = MutableLiveData<Result<T>>
typealias ResultLiveData<T> = LiveData<Result<T>>

fun ImageView.downloadAndSetImage(url: String) {
    url.let {
        if (it != "null" && it.isNotEmpty())
            Glide.with(context)
                .load(url)
//                .placeholder(R.drawable.default_user)
                .override(500, 500)
                .into(this)
    }
}