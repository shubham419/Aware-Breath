package com.shubham.awarebreath.adapter.createMeditationSession

import android.widget.ImageView
import androidx.databinding.BindingAdapter


@BindingAdapter("image_set")
fun ImageView.imageSet(str: String?) {
    val id = str?.toIntOrNull()
    if (id != null) {
        this.setImageResource(id)
    }
}

@BindingAdapter("image_set_id")
fun ImageView.imageSetId(id: Int?) {
    if (id != null) {
        this.setImageResource(id)
    }
}
