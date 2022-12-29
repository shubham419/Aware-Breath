package com.shubham.awarebreath.model

data class AnimationData(
    val inhaleT: Int, // inhaleT i.e inhaleTime
    val inhaleH: Int, // inhaleH i.e inhaleHoldTime
    val exhaleT: Int,
    val exhaleH: Int,
    val guidedTime: Int,
    val unguidedTime: Int,
    val voice: Boolean,
    val music: Boolean,
    val musicInUnguidedMode: Boolean,
    val backPressHandler: Boolean
)
