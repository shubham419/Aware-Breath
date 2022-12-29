package com.shubham.awarebreath.adapter.createMeditationSession

import android.widget.EditText
import androidx.databinding.BindingAdapter

@BindingAdapter("check_rhythm")
fun EditText.checkRhythm(data: String) {
    val i: Int? = data.toIntOrNull()
    if (i != null) {
        if (i > 15) {
            this.setText("15")
        }
    } else if (data == "") {
        return
    } else {
        this.setText("")
    }
}