package com.shubham.awarebreath.adapter.createMeditationSession

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import com.shubham.awarebreath.R


@BindingAdapter("set_visibility_of_dp")
fun LinearLayout.setVisibilityOfDp(state: Boolean) {
    if (state) {
        this.visibility = View.GONE
    } else {
        this.visibility = View.VISIBLE
    }
}

@BindingAdapter("set_visibility_of_Cm")
fun LinearLayout.setVisibilityOfCm(state: Boolean) {
    if (state) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

