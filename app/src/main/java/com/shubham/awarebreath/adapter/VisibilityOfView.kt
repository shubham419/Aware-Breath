package com.shubham.awarebreath.adapter

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("visibilityOfView")
fun ImageView.visibilityOfView(state: Boolean) {
    if (!state) {
        this.visibility = View.GONE
    }
}

@BindingAdapter("visibilityOfView")
fun LinearLayout.visibilityOfView(state: Boolean) {
    if (!state) {
        this.visibility = View.VISIBLE
    }
}

@BindingAdapter("visibilityOfView")
fun TextView.visibilityOfView(state: Boolean) {
    if (!state) {
        this.visibility = View.VISIBLE
    }
}