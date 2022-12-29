package com.shubham.awarebreath.viewModel

import android.graphics.drawable.Drawable
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.shubham.awarebreath.R
import com.shubham.awarebreath.model.AnimationData

class SharedViewModel: ViewModel() {

    private val _transition = MutableLiveData<Boolean>(false)
    val transition : LiveData<Boolean> get() = _transition

    private val _animationData = MutableLiveData<AnimationData>()
    val animationData : LiveData<AnimationData> = _animationData


    fun meditationClicked(inhaleT: Int, inhaleH: Int, exhaleT: Int, exhaleH: Int, guidedTime: Int, unguidedTime:Int, voice: Boolean, music:Boolean, musicInUnguidedMode:Boolean, backPressHandler:Boolean){
        val data = AnimationData(inhaleT,inhaleH,exhaleT,exhaleH,guidedTime,unguidedTime,voice,music,musicInUnguidedMode,backPressHandler)
        _animationData.value = data
        _transition.value = true
        _transition.value = false
    }

}