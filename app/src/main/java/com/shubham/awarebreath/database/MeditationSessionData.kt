package com.shubham.awarebreath.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meditationSessionData")
data class MeditationSessionData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val time: String = "10",
    val displayPicture: Int,
    val color : Int,
    val inhaleT: Int = 4,
    val inhaleH: Int = 2,
    val exhaleT: Int = 6,
    val exhaleH: Int = 2,
    val guidedMode: Int = 4,
    val unguidedMode: Int = 6,
    val unguidedPercentage : Int = 40,
    val guidedPercentage: Int = 60,
    val instructionVoice:Boolean = true,
    val music: Boolean = true,
    val musicInUnguidedMode: Boolean = false
)
