package com.shubham.awarebreath.adapter.createMeditationSession

import android.widget.TextView
import androidx.databinding.BindingAdapter


@BindingAdapter(value = ["time", "percentage", "flag"], requireAll = false)
fun setTime(textView: TextView, time: String?, percentage: String?, flag : Boolean) {
    val t: Int? = time!!.toIntOrNull()
    val test : Int? = percentage!!.toIntOrNull()
    if(test != null) {
        val p: Int? = if (flag) {
            percentage.toIntOrNull()
        } else {
            100 - percentage.toIntOrNull()!!
        }

        if (t != null && p != null) {
            val result: Float = t * p / 100.toFloat()
            textView.text = "$result Minute"
        } else {
            textView.text = "4 Minute"
        }
    }
 }