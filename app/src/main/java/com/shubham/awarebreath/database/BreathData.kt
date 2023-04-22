package com.shubham.awarebreath.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breathData")
data class BreathData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val analyticListData: AnalyticListData,
    val totalBreath: Int,
    val totalAwareBreath: Int,
    val totalUnawareBreath: Int,
    val totalDuration: Int,
    val CompletedDuration: String,
)
