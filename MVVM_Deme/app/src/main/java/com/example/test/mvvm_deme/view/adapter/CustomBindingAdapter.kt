package com.example.test.mvvm_demo.view.adapter

import android.view.View
import androidx.databinding.BindingAdapter

class CustomBindingAdapter{
    @BindingAdapter("visibleGone")

    fun showHide(view:View,show: Boolean){
        view.visibility = if (show) View.VISIBLE else View.GONE
    }
}
