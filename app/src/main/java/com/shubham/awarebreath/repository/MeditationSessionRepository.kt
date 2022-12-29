package com.shubham.awarebreath.repository

import androidx.lifecycle.LiveData
import com.shubham.awarebreath.database.MeditationSessionData
import com.shubham.awarebreath.database.MeditationSessionDataDao

class MeditationSessionRepository(private val meditationSessionDataDao: MeditationSessionDataDao) {

    suspend fun createMeditationSession(meditationSessionData: MeditationSessionData){
            meditationSessionDataDao.createMeditationSession(meditationSessionData)
    }

    suspend fun deleteMeditationSession(meditationSessionData: MeditationSessionData){
         meditationSessionDataDao.deleteMeditationSession(meditationSessionData)
    }

    fun getMeditationSessionData(): LiveData<List<MeditationSessionData>> {
        return meditationSessionDataDao.getMeditationSessionData()
    }

    fun deleteById(id: Int) {
        meditationSessionDataDao.deleteById(id)
    }

}