package com.shubham.awarebreath.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [BreathData::class, MeditationSessionData::class], version = 1)
@TypeConverters(Converters::class)
abstract class BreathDataBase : RoomDatabase() {

    abstract fun breathDataDao(): BreathDataDao
    abstract fun meditationSessionDataDao(): MeditationSessionDataDao

    companion object {
        @Volatile
        private var INSTANCE: BreathDataBase? = null

        fun getBreathDatabase(context: Context): BreathDataBase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BreathDataBase::class.java,
                        "breathDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }

}