package com.orlik.crypt.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProfileDao {

    @Query("SELECT * FROM profiles")
    fun getAll(): LiveData<List<ProfileEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addProfile(profile: ProfileEntity)

}