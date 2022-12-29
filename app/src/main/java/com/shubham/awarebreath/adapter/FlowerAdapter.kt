package com.shubham.awarebreath.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.shubham.awarebreath.model.FlowerAdapterModel

var inhaleT: Long = 4000
var exhaleT: Long = 4000
var rotationC: Boolean = false

@BindingAdapter("data")
fun ImageView.data(flowerAdapterModel: FlowerAdapterModel) {
    inhaleT = flowerAdapterModel.inhaleT
    exhaleT = flowerAdapterModel.exhaleT
    rotationC = flowerAdapterModel.rotation
}

@BindingAdapter("animate")
fun ImageView.animate(state: Boolean) {
    if (state) {
        this.animate()
            .rotation(-180F)
            .scaleXBy(1F)
            .scaleYBy(1F)
            .setDuration(inhaleT)
            .start()
    }
    else {               //change
        this.animate()
            .rotation(180F)
            .scaleX(1F)
            .scaleY(1F)
            .setDuration(exhaleT)
            .start()
    }
}