package com.shubham.awarebreath.adapter.createMeditationSession

import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.shubham.awarebreath.model.ColorPickerData


@BindingAdapter(value = ["colorPickerData", "currentColor"], requireAll = false)
fun setColor(cardView: CardView, colorPickerData: ColorPickerData?, currentColor: Int?) {

    if (currentColor == colorPickerData!!.selectedColor) {
        cardView.radius = 35F
        return
    }
    if (currentColor == colorPickerData.unselectedColor) {
        cardView.radius = 10F
        return
    }
}

