package com.shubham.awarebreath.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MeditationSessionDataDao {

    @Insert
    suspend fun createMeditationSession(meditationSessionData: MeditationSessionData)

    @Delete
    suspend fun deleteMeditationSession(meditationSessionData: MeditationSessionData)

    @Query("SELECT * FROM meditationSessionData")
    fun getMeditationSessionData(): LiveData<List<MeditationSessionData>>

    @Query("DELETE FROM meditationSessionData WHERE id = :id")
    fun deleteById(id: Int)

}