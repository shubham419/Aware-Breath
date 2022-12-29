package com.shubham.awarebreath.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BreathDataDao {

    @Insert
    suspend fun insertBreathData(breathData: BreathData)

    @Delete
    suspend fun deleteBreathData(breathData: BreathData)

    @Query("SELECT * FROM breathData")
    fun getBreathData(): LiveData<List<BreathData>>

    @Query("SELECT analyticListData FROM breathData")
    fun getAnalyticListData(): LiveData<List<AnalyticListData>>

}