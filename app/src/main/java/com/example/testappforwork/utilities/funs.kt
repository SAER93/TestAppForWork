package com.example.testappforwork.utilities

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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

fun Fragment.hideKeyboard() {
    val inputMethodManager =
        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(requireActivity().window.decorView.windowToken, 0)
}