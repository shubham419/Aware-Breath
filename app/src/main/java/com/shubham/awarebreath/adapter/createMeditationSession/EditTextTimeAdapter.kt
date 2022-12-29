package com.shubham.awarebreath.adapter.createMeditationSession

import android.widget.EditText
import androidx.databinding.BindingAdapter

@BindingAdapter("check_time")
fun EditText.checkTime(data: String) {
    val i: Int? = data.toIntOrNull()
    if (i != null) {
        if (i > 60) {
            this.setText("60")
        }
    } else if (data == "") {
        return
    } else {
        this.setText("")
    }
}