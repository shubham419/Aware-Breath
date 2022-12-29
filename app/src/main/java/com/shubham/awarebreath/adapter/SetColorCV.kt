package com.shubham.awarebreath.adapter

import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.shubham.awarebreath.R

@BindingAdapter("set_color_cv")
fun CardView.setColorCV(color: Int?) {
    if (color != null) {
        when (color) {
            1 -> {
                this.setCardBackgroundColor(resources.getColor(R.color.c1))
            }
            2 -> {
                this.setCardBackgroundColor(resources.getColor(R.color.c2))
            }
            3 -> {
                this.setCardBackgroundColor(resources.getColor(R.color.c3))
            }
            4 -> {
                this.setCardBackgroundColor(resources.getColor(R.color.c4))
            }
            5 -> {
                this.setCardBackgroundColor(resources.getColor(R.color.c5))
            }
            6 -> {
                this.setCardBackgroundColor(resources.getColor(R.color.c6))
            }
            7 -> {
                this.setCardBackgroundColor(resources.getColor(R.color.c7))
            }
            8 -> {
                this.setCardBackgroundColor(resources.getColor(R.color.c8))
            }
            9 -> {
                this.setCardBackgroundColor(resources.getColor(R.color.c9))
            }
            10 -> {
                this.setCardBackgroundColor(resources.getColor(R.color.c10))
            }
            11 -> {
                this.setCardBackgroundColor(resources.getColor(R.color.c11))
            }
            12 -> {
                this.setCardBackgroundColor(resources.getColor(R.color.c12))
            }
        }

    }
}