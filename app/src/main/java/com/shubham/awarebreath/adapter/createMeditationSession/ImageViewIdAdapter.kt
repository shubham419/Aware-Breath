package com.shubham.awarebreath.adapter.createMeditationSession

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener


@BindingAdapter("id_get")
fun ImageView.idSet(data: Int) {

}

@InverseBindingAdapter(attribute = "id_get")
fun ImageView.idGet(): Int {
    return 10
}

@BindingAdapter("id_getAttrChanged")
fun ImageView.idGetListener(listener: InverseBindingListener) {
    this.setOnClickListener {
        listener.onChange()
    }
}
