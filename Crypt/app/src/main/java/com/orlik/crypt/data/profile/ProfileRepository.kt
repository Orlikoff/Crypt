package com.orlik.crypt.data.profile

import com.orlik.crypt.data.profile.ProfileDao
import com.orlik.crypt.data.profile.ProfileEntity

class ProfileRepository(private val profileDao: ProfileDao) {

    fun getAllProfiles(): List<ProfileEntity> = profileDao.getAllProfiles()

    suspend fun addProfile(profile: ProfileEntity){
        profileDao.insertProfile(profile)
    }

    suspend fun removeProfile(profile: ProfileEntity){
        profileDao.removeProfile(profile)
    }

}