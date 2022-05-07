package com.orlik.crypt.data.profile

import androidx.room.*

@Dao
interface ProfileDao {

    @Query("SELECT * from profiles")
    fun getAllProfiles(): List<ProfileEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProfile(profile: ProfileEntity)

    @Delete
    suspend fun removeProfile(profile: ProfileEntity)

}