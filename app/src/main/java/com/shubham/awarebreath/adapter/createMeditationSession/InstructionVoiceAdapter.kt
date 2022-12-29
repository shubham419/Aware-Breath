package com.shubham.awarebreath.adapter.createMeditationSession

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.shubham.awarebreath.R

@BindingAdapter("set_visibility_tv")
fun TextView.setVisibility(state : Boolean){
    if(state){
        this.visibility = View.VISIBLE
    }
    else{
        this.visibility = View.GONE
    }
}

@BindingAdapter("set_visibility_l")
fun LinearLayout.setVisibility(state: Boolean){
    if(state){
        this.visibility = View.GONE
    }
    else{
        this.visibility = View.VISIBLE
    }
}

@BindingAdapter("img_src")
fun ImageView.src(state: Boolean){
    if(state){
        this.setImageResource(R.drawable.ic_add)
    }else{
        this.setImageResource(R.drawable.ic_close)
    }
}