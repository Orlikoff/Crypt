package com.orlik.crypt.data.profile

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ProfileEntity::class], version = 1)
abstract class ProfileDatabase: RoomDatabase() {
    abstract fun profileDao(): ProfileDao

    companion object {
        @Volatile
        private var INSTANCE: ProfileDatabase? = null

        fun getDatabase(context: Context): ProfileDatabase {
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context,
                        ProfileDatabase::class.java,
                        "profile_database"
                    ).allowMainThreadQueries().build()
                }
            }

            return INSTANCE!!
        }
    }
}