package com.shubham.awarebreath.repository

import androidx.lifecycle.LiveData
import com.shubham.awarebreath.database.AnalyticListData
import com.shubham.awarebreath.database.BreathData
import com.shubham.awarebreath.database.BreathDataDao
import com.shubham.awarebreath.database.MeditationSessionData

class AnalyticRepository(private val breathDataDao: BreathDataDao) {

    suspend fun insertBreathData(breathData: BreathData){
        breathDataDao.insertBreathData(breathData)
    }

    fun getBreathData(): LiveData<List<BreathData>> {
        return breathDataDao.getBreathData()
    }

     fun getAnalyticListData(): LiveData<List<AnalyticListData>> {
       return breathDataDao.getAnalyticListData()
    }

}